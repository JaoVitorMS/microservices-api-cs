package org.zsh.matricula_service.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import org.zsh.matricula_service.client.CursoClient;
import org.zsh.matricula_service.client.EstudanteClient;
import org.zsh.matricula_service.client.dto.EstudanteResponse;
import org.zsh.matricula_service.controller.dto.MatricularRequest;
import org.zsh.matricula_service.domain.Disponivel;
import org.zsh.matricula_service.repository.IMatriculaRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class MatriculaService {

    private final CursoClient cursoClient;
    private final EstudanteClient estudanteClient;
    private final IMatriculaRepository matriculaRepository;

    public MatriculaService(CursoClient cursoClient,
                            EstudanteClient estudanteClient,
                            IMatriculaRepository matriculaRepository) {
        this.cursoClient = cursoClient;
        this.estudanteClient = estudanteClient;
        this.matriculaRepository = matriculaRepository;
    }

    public Disponivel listarDisponiveis() {
        Map<String, List<String>> disciplinas = new LinkedHashMap<>();

        cursoClient.listarDisponiveis().forEach(curso -> {
            if (!StringUtils.hasText(curso.disciplina()) || !StringUtils.hasText(curso.horario())) {
                return;
            }
            disciplinas.computeIfAbsent(curso.disciplina(), chave -> new java.util.ArrayList<>())
                    .add(curso.horario());
        });

        return new Disponivel(disciplinas);
    }

    public void matricular(MatricularRequest request) {
        boolean cursoDisponivel = cursoClient.listarDisponiveis().stream()
                .anyMatch(curso -> request.disciplina().equalsIgnoreCase(curso.disciplina())
                        && request.horario().equalsIgnoreCase(curso.horario()));

        if (!cursoDisponivel) {
            throw new ResponseStatusException(BAD_REQUEST, "Disciplina/horario nao disponivel");
        }

        EstudanteResponse aluno = estudanteClient.buscarPorNome(request.nomeAluno());
        String nomeAluno = aluno.nome();


        matriculaRepository.matricular(request.disciplina(), request.horario(), nomeAluno);
    }
}


package org.zsh.curso_service.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsh.curso_service.dto.CursoDto;
import org.zsh.curso_service.dto.CursoMapper;
import org.zsh.curso_service.repository.CursoRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;

    public void deleteCursoById(Long id) {
        cursoRepository.deletar(id);
    }

    public CursoDto cadastrarCurso( CursoDto curso) {
        var domain = CursoMapper.toDomain(curso);
        cursoRepository.salvar(domain);
        return CursoMapper.toDto(domain);
    }

    public CursoDto encontrarPorHorario(String horario) {
        var response = cursoRepository.encontrarHorario(horario);
        return CursoMapper.toDto(response);
    }

    public List<CursoDto> encontrarPorCodigo(Long codigoId) {
        return cursoRepository.encontrarPorCodigo(codigoId).stream()
                .map(CursoMapper::toDto)
                .toList();
    }

    public List<CursoDto> encontrarPorDisciplina(String disciplina) {
        return cursoRepository.encontrarPorDisciplina(disciplina).stream()
                .map(CursoMapper::toDto)
                .toList();
    }

    public List<CursoDto> encontrarTodos() {
        return cursoRepository.encontrarTodos().stream()
                .map(CursoMapper::toDto)
                .toList();
    }
}

package org.zsh.estudante_service.repository;

import org.zsh.estudante_service.model.Estudante;
import java.util.List;
public interface IEstudanteRepository{
    Estudante criar(Estudante estudante);
    void deletar(Long id);

    Estudante consultarPorMatricula(Long matricula);
    List<Estudante> consultarPorNome(String nome);
}

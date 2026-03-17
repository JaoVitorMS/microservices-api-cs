package org.zsh.curso_service.dto;

import org.zsh.curso_service.model.Curso;

public class CursoMapper {
    public static CursoDto toDto( Curso domain ){
        return new CursoDto(domain.getDisciplina(), domain.getHorario());
    }

    public static Curso toDomain(CursoDto dto){
        return Curso.builder()
                .disciplina(dto.disciplina())
                .horario(dto.horario())
                .build();
    }
}

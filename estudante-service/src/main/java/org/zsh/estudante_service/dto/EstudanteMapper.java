package org.zsh.estudante_service.dto;

import org.springframework.stereotype.Component;
import org.zsh.estudante_service.model.Estudante;

@Component
public class EstudanteMapper {
    public static Estudante toDomain(EstudanteDto dto){
        return Estudante
                .builder()
                .nome(dto.nome())
                .matriculaId(dto.matriculaId())
                .build();
    }

    public static EstudanteDto toDto(Estudante domain){
        return new EstudanteDto(domain.getNome(), domain.getMatriculaId());
    }
}

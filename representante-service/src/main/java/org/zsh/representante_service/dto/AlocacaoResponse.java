package org.zsh.representante_service.dto;

import java.time.Instant;

public record AlocacaoResponse(
        Long id,
        String cpfRepresentante,
        String cpfCliente,
        Long idPeca,
        Instant dataHora
) {
}

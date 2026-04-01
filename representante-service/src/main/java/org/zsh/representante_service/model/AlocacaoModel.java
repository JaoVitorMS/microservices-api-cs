package org.zsh.representante_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "alocacoes")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AlocacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cpfRepresentante;

    @Column(nullable = false)
    private String cpfCliente;

    @Column(nullable = false)
    private Long idPeca;

    @Column(nullable = false)
    private Instant dataHora;

}

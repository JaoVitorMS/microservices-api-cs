package org.zsh.estudante_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estudantes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Estudante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long matriculaId;

}

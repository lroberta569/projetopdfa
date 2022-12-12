package com.larissa.arphoenix.projetopdfa.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.larissa.arphoenix.projetopdfa.enums.StatusMatricula;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ALUNOS")
public class AlunoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matriculaAluno;
    private String nomeAluno;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String enderecoAluno;
    private String emailAluno;
    private String telefoneAluno;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    private String curso;
    @Enumerated(EnumType.STRING)
    private StatusMatricula statusMatricula;

}

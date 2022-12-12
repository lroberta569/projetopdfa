package com.larissa.arphoenix.projetopdfa.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoDTO {
    @NotBlank
    private String nomeAluno;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotBlank
    private LocalDate dataNascimento;
    @NotBlank
    private String enderecoAluno;
    @NotBlank
    @Email
    private String emailAluno;
    @NotBlank
    private String telefoneAluno;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotBlank
    private LocalDate dataInicio;
    @NotBlank
    private String curso;

}

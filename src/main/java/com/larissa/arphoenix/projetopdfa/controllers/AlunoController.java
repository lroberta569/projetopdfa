package com.larissa.arphoenix.projetopdfa.controllers;

import com.larissa.arphoenix.projetopdfa.dtos.AlunoDTO;
import com.larissa.arphoenix.projetopdfa.models.AlunoModel;
import com.larissa.arphoenix.projetopdfa.services.AlunoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/cadastrarAluno")
    public ResponseEntity<Object> cadastrarAluno(@RequestBody AlunoDTO alunoDTO){
        try{
           return ResponseEntity.status(HttpStatus.OK).body(alunoService.salvarAluno(alunoDTO));
        }catch (Exception e){
            log.error("Ocorreu um erro ao salvar aluno", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PutMapping ("/atualizarAluno/{matricula}")
    public ResponseEntity<Object> atualizarAluno(@PathVariable("matricula") Long matricula){
        try{
            AlunoModel alunoModel = alunoService.findAlunoByMatricula(matricula);
            alunoService.atualizarAluno(alunoModel);
            return ResponseEntity.status(HttpStatus.OK).body("Cadastro atualizado com sucesso");
        }catch (Exception e){
            log.error("Ocorreu um erro ao cadastrar o aluno", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/buscarAlunos")
    public ResponseEntity<List<AlunoModel>> buscarAlunos(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alunoService.findAll());
        }catch (Exception e){
            log.error("Ocorreu um erro ao buscar alunos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/buscarAluno/{matricula}")
    public ResponseEntity<Object> buscarAlunoMatricula(@PathVariable("matricula") Long matriculaAluno){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alunoService.findAlunoByMatricula(matriculaAluno));
        }catch (Exception e){
            log.error("Ocorreu um erro ao buscar o aluno", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/inativarAluno/{id}")
    public ResponseEntity<Object> inativarAluno(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alunoService.inativarAluno(id));
        }catch (Exception e){
            log.error("Ocorreu um erro ao inativar a matrícula do aluno", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}

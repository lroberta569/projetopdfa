package com.larissa.arphoenix.projetopdfa.controllers;

import com.larissa.arphoenix.projetopdfa.models.AlunoModel;
import com.larissa.arphoenix.projetopdfa.services.AlunoService;
import com.larissa.arphoenix.projetopdfa.services.RelatorioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("/relatorios")
@CrossOrigin(origins = "*")
public class RelatorioContoller {

    @Autowired
    private AlunoService alunoService;
    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/relatorioAlunos")
    public ResponseEntity<Object> gerarRelatorioAlunos(){
        try{
            List<AlunoModel> listaAlunos = alunoService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(relatorioService.exportarRelatorio(listaAlunos));
        }catch (Exception e){
            log.error("Ocorreu um erro ao inativar a matrícula do aluno", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

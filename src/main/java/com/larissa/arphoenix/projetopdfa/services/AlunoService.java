package com.larissa.arphoenix.projetopdfa.services;

import com.larissa.arphoenix.projetopdfa.dtos.AlunoDTO;
import com.larissa.arphoenix.projetopdfa.enums.StatusMatricula;
import com.larissa.arphoenix.projetopdfa.models.AlunoModel;
import com.larissa.arphoenix.projetopdfa.repositories.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    /**
     * Retorna uma lista com todos os alunos cadastrados
     */
   public List<AlunoModel> findAll(){
        return alunoRepository.findAll();
    }
    /**
     * Retorna um aluno pelo seu numero de matricula
     */
    public AlunoModel findAlunoByMatricula(Long matricula){
       return alunoRepository.findAlunoByMatriculaAluno(matricula);
    }

    /**
     * Faz a inativação do aluno no banco de dados (não faz a exclusão dele e sim seta ele como inativo)
     */
    public String inativarAluno(Long matricula){
       AlunoModel alunoModel = findAlunoByMatricula(matricula);
       alunoModel.setStatusMatricula(StatusMatricula.INATIVO);
       alunoRepository.save(alunoModel);
       return "Aluno inativado com sucesso";
    }

    /**
     * Salva as informações do aluno passadas para cadastro
     */
    public AlunoModel salvarAluno(AlunoDTO alunoDTO){
       AlunoModel alunoModel = new AlunoModel();
       BeanUtils.copyProperties(alunoDTO, alunoModel);
       alunoModel.setStatusMatricula(StatusMatricula.ATIVO);
       return alunoRepository.save(alunoModel);
    }

    /**
     * Salva as informações de atualização do aluno
     */
    public AlunoModel atualizarAluno(AlunoModel alunoModel){
       AlunoModel aluno = findAlunoByMatricula(alunoModel.getMatriculaAluno());
       BeanUtils.copyProperties(alunoModel, aluno);
       aluno.setStatusMatricula(StatusMatricula.ATIVO);
       return alunoRepository.save(aluno);
       }

    }




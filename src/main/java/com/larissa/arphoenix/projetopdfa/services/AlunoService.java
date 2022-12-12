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

   public List<AlunoModel> findAll(){
        return alunoRepository.findAll();
    }


    public AlunoModel findAlunoByMatricula(Long matricula){
       return alunoRepository.findAlunoByMatriculaAluno(matricula);
    }

    public String inativarAluno(Long matricula){
       AlunoModel alunoModel = findAlunoByMatricula(matricula);
       alunoModel.setStatusMatricula(StatusMatricula.INATIVO);
       alunoRepository.save(alunoModel);
       return "Aluno inativado com sucesso";
    }

    public AlunoModel salvarAluno(AlunoDTO alunoDTO){
       AlunoModel alunoModel = new AlunoModel();
       BeanUtils.copyProperties(alunoDTO, alunoModel);
       alunoModel.setStatusMatricula(StatusMatricula.ATIVO);
       return alunoRepository.save(alunoModel);

    }

    public AlunoModel atualizarAluno(AlunoModel alunoModel){
       AlunoModel aluno = findAlunoByMatricula(alunoModel.getMatriculaAluno());
       BeanUtils.copyProperties(alunoModel, aluno);
       aluno.setStatusMatricula(StatusMatricula.ATIVO);
       return alunoRepository.save(aluno);
       }

    }




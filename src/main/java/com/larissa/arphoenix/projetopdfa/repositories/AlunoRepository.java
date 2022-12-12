package com.larissa.arphoenix.projetopdfa.repositories;

import com.larissa.arphoenix.projetopdfa.models.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {

    AlunoModel findAlunoByMatriculaAluno(Long matricula);

}

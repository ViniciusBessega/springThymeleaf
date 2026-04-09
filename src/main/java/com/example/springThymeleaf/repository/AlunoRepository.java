package com.example.springThymeleaf.repository;

import com.example.springThymeleaf.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}

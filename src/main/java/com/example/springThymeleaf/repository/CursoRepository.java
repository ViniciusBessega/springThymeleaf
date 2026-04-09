package com.example.springThymeleaf.repository;

import com.example.springThymeleaf.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository  extends JpaRepository<Curso, Long> {
}

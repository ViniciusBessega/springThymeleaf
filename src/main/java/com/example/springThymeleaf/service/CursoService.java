package com.example.springThymeleaf.service;

import com.example.springThymeleaf.model.Curso;
import com.example.springThymeleaf.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarTodos(){
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id){
        return cursoRepository.findById(id);
    }

    public Curso salvar(Curso curso){
        return cursoRepository.save(curso);
    }

    public  void deletar(Long id){
        cursoRepository.deleteById(id);
    }

    public Curso atualizar(Long id, Curso novoCurso){
        return cursoRepository.findById(id).map(cursoExistente -> {
            cursoExistente.setNome(novoCurso.getNome());
            return cursoRepository.save(cursoExistente);
        }).orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
    }
}

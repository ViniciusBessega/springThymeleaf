package com.example.springThymeleaf.service;


import com.example.springThymeleaf.model.Aluno;
import com.example.springThymeleaf.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

    public Aluno atualizar(Long id, Aluno novoAluno) {
        return alunoRepository.findById(id).map(alunoExistente ->{
            alunoExistente.setNome(novoAluno.getNome());
            alunoExistente.setEmail(novoAluno.getEmail());
            alunoExistente.setCurso(novoAluno.getCurso());
            return alunoRepository.save(alunoExistente);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

}

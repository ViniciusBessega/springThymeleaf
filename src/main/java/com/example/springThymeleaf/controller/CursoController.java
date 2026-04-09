package com.example.springThymeleaf.controller;


import com.example.springThymeleaf.model.Curso;
import com.example.springThymeleaf.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listaCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "cursos/lista";
    }

    @GetMapping("/nova")
    public String novaCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "cursos/nova";
    }

    @PostMapping("/salvar")
    public String salvarCursos(@ModelAttribute Curso curso) {
        if(curso.getId() != null) {
            cursoService.atualizar(curso.getId(), curso);
        }else {
            cursoService.salvar(curso);
        }
        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    public String editarCursos(@PathVariable Long id, Model model) {
        Curso curso = cursoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Categoria invalida: " + id));
        model.addAttribute("curso", curso);
        return "cursos/editar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCursos(@PathVariable Long id, Model model) {
        cursoService.deletar(id);
        return "redirect:/cursos";
    }


}

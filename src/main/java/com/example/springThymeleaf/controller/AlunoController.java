package com.example.springThymeleaf.controller;



import com.example.springThymeleaf.model.Aluno;
import com.example.springThymeleaf.service.AlunoService;
import com.example.springThymeleaf.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", alunoService.listarTodos());
        return "alunos/lista";
    }

    @GetMapping("/novo")
    public String novoAluno(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("cursos", cursoService.listarTodos());
        return "alunos/form";
    }

    @PostMapping("/salvar")
    public String salvarAluno(@ModelAttribute Aluno aluno) {
        if (aluno.getId() != null) {
            alunoService.atualizar(aluno.getId(), aluno);
        }else {
            alunoService.salvar(aluno);
        }
        return "redirect:/alunos";
    }

    @GetMapping("/editar/{id}")
    public String editarAluno(@PathVariable Long id, Model model) {
        Aluno aluno = alunoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto Invalido: " + id));
        model.addAttribute("aluno", aluno);
        model.addAttribute("cursos", cursoService.listarTodos());
        return "alunos/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable Long id) {
        alunoService.deletar(id);
        return "redirect:/alunos";
    }

}

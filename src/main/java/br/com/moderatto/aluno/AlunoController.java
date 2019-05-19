package br.com.moderatto.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("alunos/list");
        modelAndView.addObject("alunos", this.alunoService.listar());
        return modelAndView;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String incluir(Model model) {
        model.addAttribute("aluno", new AlunoDTO());
        return "alunos/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView criar(@ModelAttribute(name = "aluno") AlunoDTO alunoDTO, Model model) {
        this.alunoService.salvar(alunoDTO);
        return new ModelAndView("redirect:/alunos");
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") long id, Model model) {
        AlunoDTO aluno = alunoService.getById(id);
        model.addAttribute("aluno", aluno);
        return "alunos/update";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") long id, AlunoDTO aluno, Model model) {
        alunoService.salvar(aluno);
        return new ModelAndView("redirect:/alunos");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/alunos");
        alunoService.remove(id);
        return modelAndView;
    }

}

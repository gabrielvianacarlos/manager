package br.com.moderatto.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class AlunoService {


    List<AlunoDTO> alunosDTO = new ArrayList<>();

    @Autowired
    AlunoRepository repository;

    public List<AlunoDTO> listar() {
        alunosDTO = new ArrayList<>();
        Iterable<Aluno> alunos = repository.findAll();
        alunos.forEach(aluno -> {
            AlunoDTO dto = new AlunoDTO.AlunoDTOBuilder()
                    .id(aluno.getId())
                    .nome(aluno.getNome())
                    .email(aluno.getEmail()).build();
            this.alunosDTO.add(dto);
        });
        return alunosDTO;
    }

    public void salvar(AlunoDTO alunoDTO) {

        if (alunoDTO.getId() == null) {
            Aluno aluno = new Aluno.AlunoBuilder()
                    .nome(alunoDTO.getNome())
                    .email(alunoDTO.getEmail())
                    .build();
            repository.save(aluno);
        } else {
            Optional<Aluno> alunoOptional = repository.findById(alunoDTO.getId());
            alunoOptional.get().setNome(alunoDTO.getNome());
            alunoOptional.get().setEmail(alunoDTO.getEmail());
            repository.save(alunoOptional.get());
        }
    }


    public AlunoDTO getById(Long id) {
        Aluno aluno = this.repository.findById(id).get();
        return new AlunoDTO.AlunoDTOBuilder().nome(aluno.getNome()).email(aluno.getEmail()).id(aluno.getId()).build();
    }

    public void remove(long id) {
        Optional<Aluno> alunoOptional = repository.findById(id);
        repository.delete(alunoOptional.get());
    }
}

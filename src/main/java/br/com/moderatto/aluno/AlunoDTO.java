package br.com.moderatto.aluno;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoDTO {

    private Long id;
    private String nome;
    private String email;


}

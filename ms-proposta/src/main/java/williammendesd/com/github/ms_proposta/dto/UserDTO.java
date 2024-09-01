package williammendesd.com.github.ms_proposta.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import williammendesd.com.github.ms_proposta.model.Proposta;
import williammendesd.com.github.ms_proposta.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class UserDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    private String nome;

    @NotBlank(message = "Campo requerido")
    private String sobrenome;

    @NotBlank(message = "Campo requerido")
    private String cpf;

    @NotBlank(message = "Campo requerido")
    private String telefone;

    @NotNull(message = "Campo requerido")
    private BigDecimal renda;


    public UserDTO(User entity) {
        id = entity.getId();
        nome = entity.getNome();
        sobrenome = entity.getSobrenome();
        cpf = entity.getCpf();
        telefone = entity.getTelefone();
        renda = entity.getRenda();
    }
}

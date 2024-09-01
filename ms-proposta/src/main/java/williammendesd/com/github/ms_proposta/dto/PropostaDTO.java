package williammendesd.com.github.ms_proposta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import williammendesd.com.github.ms_proposta.model.Proposta;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class PropostaDTO {

    private Long id;

    @NotNull(message = "Campo requerido")
    private BigDecimal valorSolicitado;
    @NotNull(message = "Campo requerido")
    private Integer prazoParaPagamento;

    private Boolean aprovado;
    private Long user_id;

//    @NotNull(message = "Campo requerido")
//    private User usuario = new User();

    public PropostaDTO(Proposta entity) {
        id = entity.getId();
        valorSolicitado = entity.getValorSolicitado();
        prazoParaPagamento = entity.getPrazoParaPagamento();
        aprovado = entity.getAprovado();
        user_id = entity.getUserId();
//        usuario = entity.getUsuario();
    }
}

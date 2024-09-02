package williammendesd.com.github.ms_proposta.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import williammendesd.com.github.ms_proposta.model.Proposta;
import williammendesd.com.github.ms_proposta.model.User;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PropostaDTO {

    private Long id;

    @NotNull(message = "Campo requerido")
    private BigDecimal valorSolicitado;
    @NotNull(message = "Campo requerido")
    private Integer prazoParaPagamento;
    private Boolean aprovado;
    private Long user_id;

    private User user = new User();

    @JsonIgnore()
    public User getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(User user) {
        this.user = user;
    }

    public PropostaDTO(Proposta entity) {
        id = entity.getId();
        valorSolicitado = entity.getValorSolicitado();
        prazoParaPagamento = entity.getPrazoParaPagamento();
        aprovado = entity.getAprovado();
        user_id = entity.getUserId();
    }

}

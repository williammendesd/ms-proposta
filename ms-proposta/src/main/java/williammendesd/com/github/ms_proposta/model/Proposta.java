package williammendesd.com.github.ms_proposta.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"valorSolicitado", "prazoParaPagamento", "aprovado"})

@Entity
@Table(name = "tb_proposta")


public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valorSolicitado;
    @Column(nullable = false)
    private Integer prazoParaPagamento;
    @Column(nullable = false)
    private Boolean aprovado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="user_id", updatable=false, insertable=false)
    private Long userId;

}

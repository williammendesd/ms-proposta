package williammendesd.com.github.ms_proposta.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import williammendesd.com.github.ms_proposta.model.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta,Long> {
}

package williammendesd.com.github.ms_proposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import williammendesd.com.github.ms_proposta.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
}

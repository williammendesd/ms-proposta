package williammendesd.com.github.ms_proposta.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import williammendesd.com.github.ms_proposta.dto.PropostaDTO;
import williammendesd.com.github.ms_proposta.dto.UserDTO;
import williammendesd.com.github.ms_proposta.model.Proposta;
import williammendesd.com.github.ms_proposta.model.User;
import williammendesd.com.github.ms_proposta.repository.PropostaRepository;
import williammendesd.com.github.ms_proposta.repository.UserRepository;

import java.net.URI;
import java.util.Optional;

@Service
public class PropostaService {
    @Autowired
    private PropostaRepository repository;

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public Page<PropostaDTO> findAll(Pageable pageable){
        Page<Proposta> page = repository.findAll(pageable);

        return page.map(PropostaDTO::new);
    }

    @Transactional
    public PropostaDTO insert(PropostaDTO dto, Long userId){
        Proposta entity = new Proposta();
        copyDtoToEntity(dto, entity);
        entity.setAprovado(false);
//        entity.setUsuario(userRepository.getReferenceById());
        Optional<User> user = userRepository.findById(userId);

        entity.setUser(user.get());
        repository.save(entity);
        return new PropostaDTO(entity);
    }

    private void copyDtoToEntity(PropostaDTO dto, Proposta entity) {
        entity.setAprovado(dto.getAprovado());
        entity.setPrazoParaPagamento(dto.getPrazoParaPagamento());
        entity.setValorSolicitado(dto.getValorSolicitado());
    }


}

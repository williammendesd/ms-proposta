package williammendesd.com.github.ms_proposta.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import williammendesd.com.github.ms_proposta.dto.PropostaDTO;
import williammendesd.com.github.ms_proposta.model.Proposta;
import williammendesd.com.github.ms_proposta.model.User;
import williammendesd.com.github.ms_proposta.repository.PropostaRepository;
import williammendesd.com.github.ms_proposta.repository.UserRepository;
import williammendesd.com.github.ms_proposta.service.exception.ResourceNotFoundException;

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
    public PropostaDTO findById(Long id){
        Proposta entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );
        return new PropostaDTO(entity);
    }

    @Transactional
    public PropostaDTO insert(PropostaDTO dto){
        try {
            Proposta entity = new Proposta();
            User user = dto.getUser();
            user = userRepository.save(user);
            copyDtoToEntity(dto, entity, user);

            entity.setAprovado(false);

            repository.save(entity);

            return new PropostaDTO(entity);

        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + dto.getUser_id());
        }

    }

    private void copyDtoToEntity(PropostaDTO dto, Proposta entity, User user) {
        entity.setAprovado(dto.getAprovado());
        entity.setPrazoParaPagamento(dto.getPrazoParaPagamento());
        entity.setValorSolicitado(dto.getValorSolicitado());
        entity.setUserId(user.getId());
        entity.setUser(user);
    }
}

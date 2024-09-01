package williammendesd.com.github.ms_proposta.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import williammendesd.com.github.ms_proposta.dto.UserDTO;
import williammendesd.com.github.ms_proposta.model.User;
import williammendesd.com.github.ms_proposta.repository.PropostaRepository;
import williammendesd.com.github.ms_proposta.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PropostaRepository propostaRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable){
        Page<User> page = repository.findAll(pageable);
        return page.map(UserDTO::new);
//        return lista.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO findById(Long id){
        User user = repository.findById(id).orElseThrow();
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO insert(UserDTO dto){
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto){
        try {
            User entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e){
            // ARRUMAR
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException();
        }
        try {
            repository.deleteById(id);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Falha de integridade referencial");
        }
    }


    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
        entity.setCpf(dto.getCpf());
        entity.setTelefone(dto.getTelefone());
        entity.setRenda(dto.getRenda());
    }


}

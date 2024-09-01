package williammendesd.com.github.ms_proposta.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import williammendesd.com.github.ms_proposta.dto.UserDTO;
import williammendesd.com.github.ms_proposta.service.UserService;

import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService service;


    @GetMapping()
    public ResponseEntity<Page<UserDTO>> findAll(@PageableDefault(size = 10) Pageable pageable){
        Page<UserDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto)
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable @NotNull Long id,
                                               @RequestBody @Valid UserDTO dto ){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

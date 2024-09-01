package williammendesd.com.github.ms_proposta.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import williammendesd.com.github.ms_proposta.dto.PropostaDTO;
import williammendesd.com.github.ms_proposta.dto.UserDTO;
import williammendesd.com.github.ms_proposta.service.PropostaService;

import java.net.URI;

@RestController
@RequestMapping("/propostas")

public class PropostaController {
    @Autowired
    private PropostaService service;

    @GetMapping()
    public ResponseEntity<Page<PropostaDTO>> findAll(@PageableDefault(size = 10) Pageable pageable){
        Page<PropostaDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<PropostaDTO> insert(@RequestBody @Valid PropostaDTO dto, HttpSession s, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        System.out.println("\n\n\n\nHEYYYYYYYY\n " + userId);
        dto = service.insert(dto, userId);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto)
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }


}

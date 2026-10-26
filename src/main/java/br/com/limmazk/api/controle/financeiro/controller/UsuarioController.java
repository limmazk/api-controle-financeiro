package br.com.limmazk.api.controle.financeiro.controller;

import br.com.limmazk.api.controle.financeiro.dto.UsuarioRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.UsuarioResponseDTO;
import br.com.limmazk.api.controle.financeiro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody @Valid UsuarioRequestDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll(){
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid UsuarioRequestDTO dto){
        return ResponseEntity.ok(usuarioService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.limmazk.api.controle.financeiro.controller;

import br.com.limmazk.api.controle.financeiro.dto.CategoriaRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.CategoriaResponseDTO;
import br.com.limmazk.api.controle.financeiro.dto.UsuarioRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.UsuarioResponseDTO;
import br.com.limmazk.api.controle.financeiro.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> create(@RequestBody @Valid CategoriaRequestDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getAll(){
        return ResponseEntity.ok(categoriaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid CategoriaRequestDTO dto){
        return ResponseEntity.ok(categoriaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

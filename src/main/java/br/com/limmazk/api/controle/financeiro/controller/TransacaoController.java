package br.com.limmazk.api.controle.financeiro.controller;

import br.com.limmazk.api.controle.financeiro.dto.TransacaoRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.TransacaoResponseDTO;
import br.com.limmazk.api.controle.financeiro.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController (TransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> create(@RequestBody @Valid TransacaoRequestDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transacaoService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponseDTO>> getAll(){
        return ResponseEntity.ok(transacaoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> getById(@PathVariable UUID id){
        return ResponseEntity.ok(transacaoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid TransacaoRequestDTO dto){
        return ResponseEntity.ok(transacaoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        transacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/saldo/{usuarioId}")
    public ResponseEntity<BigDecimal> calcularSaldo (@PathVariable UUID usuarioId){
        return ResponseEntity.ok(transacaoService.calcularSaldo(usuarioId));
    }

    @GetMapping("/gastos-por-categoria/{usuarioId}")
    public ResponseEntity<Map<String, BigDecimal>> gastosPorCategoria(@PathVariable UUID usuarioId){
        return ResponseEntity.ok(transacaoService.gastosPorCategoria(usuarioId));
    }

}

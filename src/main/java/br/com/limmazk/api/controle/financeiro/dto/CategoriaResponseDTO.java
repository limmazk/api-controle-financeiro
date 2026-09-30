package br.com.limmazk.api.controle.financeiro.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CategoriaResponseDTO(
        UUID id,
        String nome,
        UUID usuarioId
) {
}

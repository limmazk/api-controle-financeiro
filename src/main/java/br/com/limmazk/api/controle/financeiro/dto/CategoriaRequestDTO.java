package br.com.limmazk.api.controle.financeiro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CategoriaRequestDTO(

        @NotBlank
        String nome,

        @NotNull
        UUID usuarioId
) {
}

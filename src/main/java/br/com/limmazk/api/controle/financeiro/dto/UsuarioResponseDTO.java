package br.com.limmazk.api.controle.financeiro.dto;

import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String name,
        String email
) {
}

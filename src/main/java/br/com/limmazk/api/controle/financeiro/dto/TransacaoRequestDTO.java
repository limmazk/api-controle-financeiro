package br.com.limmazk.api.controle.financeiro.dto;

import br.com.limmazk.api.controle.financeiro.enums.StatusTransacao;
import br.com.limmazk.api.controle.financeiro.enums.TipoTransacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransacaoRequestDTO(
        @NotBlank
        String descricao,

        @NotNull
        BigDecimal valor,

        @NotNull
        TipoTransacao tipo,

        @NotNull
        StatusTransacao status,

        @NotNull
        LocalDate data,

        @NotNull
        UUID usuarioId,

        @NotNull
        UUID categoriaId
) {
}

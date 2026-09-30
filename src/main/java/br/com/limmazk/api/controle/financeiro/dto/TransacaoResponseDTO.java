package br.com.limmazk.api.controle.financeiro.dto;

import br.com.limmazk.api.controle.financeiro.enums.StatusTransacao;
import br.com.limmazk.api.controle.financeiro.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransacaoResponseDTO(
        UUID id,
        String descricao,
        BigDecimal valor,
        TipoTransacao tipo,
        StatusTransacao status,
        LocalDate data,
        String nomeCategoria,
        UUID usuarioId
) {
}


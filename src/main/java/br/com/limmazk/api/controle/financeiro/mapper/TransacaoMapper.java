package br.com.limmazk.api.controle.financeiro.mapper;

import br.com.limmazk.api.controle.financeiro.dto.TransacaoRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.TransacaoResponseDTO;
import br.com.limmazk.api.controle.financeiro.entity.Categoria;
import br.com.limmazk.api.controle.financeiro.entity.Transacao;
import br.com.limmazk.api.controle.financeiro.entity.Usuario;

import java.util.UUID;

public class TransacaoMapper {

    public static Transacao toEntity(TransacaoRequestDTO dto, Usuario usuario, Categoria categoria){
        return new Transacao(
                null,
                dto.descricao(),
                dto.valor(),
                dto.tipo(),
                dto.status(),
                dto.data(),
                usuario,
                categoria
        );
    }

    public static TransacaoResponseDTO toResponseDTO(Transacao transacao){
        return new TransacaoResponseDTO(
                transacao.getId(),
                transacao.getDescricao(),
                transacao.getValor(),
                transacao.getTipo(),
                transacao.getStatus(),
                transacao.getData(),
                transacao.getCategoria().getNome(),
                transacao.getUsuario().getId()
        );
    }

    public static Transacao toEntityUpdate(UUID id, TransacaoRequestDTO dto, Usuario usuario, Categoria categoria) {
        return new Transacao(
                id,
                dto.descricao(),
                dto.valor(),
                dto.tipo(),
                dto.status(),
                dto.data(),
                usuario,
                categoria
        );
    }
}

package br.com.limmazk.api.controle.financeiro.mapper;

import br.com.limmazk.api.controle.financeiro.dto.CategoriaRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.CategoriaResponseDTO;
import br.com.limmazk.api.controle.financeiro.dto.TransacaoRequestDTO;
import br.com.limmazk.api.controle.financeiro.entity.Categoria;
import br.com.limmazk.api.controle.financeiro.entity.Transacao;
import br.com.limmazk.api.controle.financeiro.entity.Usuario;

import java.util.UUID;

public class CategoriaMapper {

    public static Categoria toEntity(CategoriaRequestDTO dto, Usuario usuario) {
        return new Categoria(
                null,
                dto.nome(),
                usuario
        );
    }

    public static CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getUsuario().getId()
        );
    }

    public static Categoria toEntityUpdate(UUID id, CategoriaRequestDTO dto, Usuario usuario) {
        return new Categoria(
                id,
                dto.nome(),
                usuario
        );
    }
}

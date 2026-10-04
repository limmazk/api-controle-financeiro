package br.com.limmazk.api.controle.financeiro.mapper;

import br.com.limmazk.api.controle.financeiro.dto.CategoriaRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.CategoriaResponseDTO;
import br.com.limmazk.api.controle.financeiro.entity.Categoria;
import br.com.limmazk.api.controle.financeiro.entity.Usuario;

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
}

package br.com.limmazk.api.controle.financeiro.mapper;

import br.com.limmazk.api.controle.financeiro.dto.UsuarioRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.UsuarioResponseDTO;
import br.com.limmazk.api.controle.financeiro.entity.Usuario;

import java.util.UUID;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        return new Usuario(
                null,
                dto.name(),
                dto.email(),
                dto.senha()
        );
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    public static Usuario toEntityUpdate(UUID id, UsuarioRequestDTO dto) {
        return new Usuario(id, dto.name(), dto.email(), dto.senha());
    }
}

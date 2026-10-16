package br.com.limmazk.api.controle.financeiro.service;

import br.com.limmazk.api.controle.financeiro.dto.UsuarioRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.UsuarioResponseDTO;
import br.com.limmazk.api.controle.financeiro.entity.Usuario;
import br.com.limmazk.api.controle.financeiro.exception.ResourceNotFoundException;
import br.com.limmazk.api.controle.financeiro.mapper.UsuarioMapper;
import br.com.limmazk.api.controle.financeiro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario usuarioUpdate = usuarioRepository.save(usuario);
        return UsuarioMapper.toResponseDTO(usuarioUpdate);
    }

    public List<UsuarioResponseDTO> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(UsuarioMapper::toResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO findById(UUID id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
        return UsuarioMapper.toResponseDTO(usuario);
    }

    public UsuarioResponseDTO update(UUID id, UsuarioRequestDTO dto){
        usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));

        Usuario usuarioUpdate = UsuarioMapper.toEntityUpdate(id, dto);
        Usuario usuarioSalved = usuarioRepository.save(usuarioUpdate);
        return UsuarioMapper.toResponseDTO(usuarioSalved);
    }

    public void delete(UUID id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
        usuarioRepository.delete(usuario);
    }

}

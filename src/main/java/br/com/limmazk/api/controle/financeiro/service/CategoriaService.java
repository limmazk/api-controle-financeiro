package br.com.limmazk.api.controle.financeiro.service;

import br.com.limmazk.api.controle.financeiro.dto.CategoriaRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.CategoriaResponseDTO;
import br.com.limmazk.api.controle.financeiro.entity.Categoria;
import br.com.limmazk.api.controle.financeiro.entity.Usuario;
import br.com.limmazk.api.controle.financeiro.exception.ResourceNotFoundException;
import br.com.limmazk.api.controle.financeiro.mapper.CategoriaMapper;
import br.com.limmazk.api.controle.financeiro.repository.CategoriaRepository;
import br.com.limmazk.api.controle.financeiro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository){
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaResponseDTO create(CategoriaRequestDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(()-> new ResourceNotFoundException("Usuario not found"));

        Categoria categoria = CategoriaMapper.toEntity(dto, usuario);
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return CategoriaMapper.toResponseDTO(categoriaSalva);
    }

    public List<CategoriaResponseDTO> getAll(){
        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias.stream()
                .map(CategoriaMapper::toResponseDTO)
                .toList();
    }

    public CategoriaResponseDTO findById (UUID id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Id not found!"));

        return CategoriaMapper.toResponseDTO(categoria);
    }

    public void delete(UUID id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Id not found!"));
        categoriaRepository.delete(categoria);
    }

    public CategoriaResponseDTO update (UUID id, CategoriaRequestDTO dto){
        categoriaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Id not found!"));

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found."));

        Categoria categoriaUpdate = CategoriaMapper.toEntityUpdate(id, dto, usuario);
        Categoria categoriaSalva = categoriaRepository.save(categoriaUpdate);

        return CategoriaMapper.toResponseDTO(categoriaSalva);
    }
}

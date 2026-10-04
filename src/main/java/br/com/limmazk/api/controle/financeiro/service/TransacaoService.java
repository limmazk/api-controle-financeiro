package br.com.limmazk.api.controle.financeiro.service;

import br.com.limmazk.api.controle.financeiro.dto.TransacaoRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.TransacaoResponseDTO;
import br.com.limmazk.api.controle.financeiro.entity.Categoria;
import br.com.limmazk.api.controle.financeiro.entity.Transacao;
import br.com.limmazk.api.controle.financeiro.entity.Usuario;
import br.com.limmazk.api.controle.financeiro.exception.ResourceNotFoundException;
import br.com.limmazk.api.controle.financeiro.mapper.TransacaoMapper;
import br.com.limmazk.api.controle.financeiro.repository.CategoriaRepository;
import br.com.limmazk.api.controle.financeiro.repository.TransacaoRepository;
import br.com.limmazk.api.controle.financeiro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, UsuarioRepository usuarioRepository,
                            CategoriaRepository categoriaRepository) {

        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public TransacaoResponseDTO criar (TransacaoRequestDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found."));
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria not found."));

        Transacao transacao = TransacaoMapper.toEntity(dto, usuario, categoria);
        Transacao transacaoSalva = transacaoRepository.save(transacao);
        return TransacaoMapper.toResponseDTO(transacaoSalva);
    }


    public List<TransacaoResponseDTO> getAll() {
        List<Transacao> transacoes = transacaoRepository.findAll();

        return transacoes.stream()
                .map(TransacaoMapper::toResponseDTO)
                .toList();
    }

    public TransacaoResponseDTO findById(UUID id){
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transacao not found."));
        return TransacaoMapper.toResponseDTO(transacao);
    }
}

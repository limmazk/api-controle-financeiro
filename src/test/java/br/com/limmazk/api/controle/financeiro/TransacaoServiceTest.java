package br.com.limmazk.api.controle.financeiro;

import br.com.limmazk.api.controle.financeiro.dto.TransacaoRequestDTO;
import br.com.limmazk.api.controle.financeiro.dto.TransacaoResponseDTO;
import br.com.limmazk.api.controle.financeiro.entity.Categoria;
import br.com.limmazk.api.controle.financeiro.entity.Transacao;
import br.com.limmazk.api.controle.financeiro.entity.Usuario;
import br.com.limmazk.api.controle.financeiro.enums.StatusTransacao;
import br.com.limmazk.api.controle.financeiro.enums.TipoTransacao;
import br.com.limmazk.api.controle.financeiro.exception.ResourceNotFoundException;
import br.com.limmazk.api.controle.financeiro.repository.CategoriaRepository;
import br.com.limmazk.api.controle.financeiro.repository.TransacaoRepository;
import br.com.limmazk.api.controle.financeiro.repository.UsuarioRepository;
import br.com.limmazk.api.controle.financeiro.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @Test
    void deveCriarTransacaoComSucesso() {
        UUID usuarioId = UUID.randomUUID();
        UUID categoriaId = UUID.randomUUID();

        TransacaoRequestDTO dto = new TransacaoRequestDTO(
                "Salário",
                new BigDecimal("5000.00"),
                TipoTransacao.ENTRADA,
                StatusTransacao.PAGA,
                LocalDate.now(),
                usuarioId,
                categoriaId
        );

        Usuario usuarioFake = new Usuario(
                usuarioId,
                "Arthur",
                "arthur@email.com",
                "123456"
        );

        Categoria categoriaFake = new Categoria(
                categoriaId,
                "Salário",
                usuarioFake
        );

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuarioFake));
        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoriaFake));

        UUID transacaoId = UUID.randomUUID();
        Transacao transacaoSalvaFake = new Transacao(
                transacaoId,
                dto.descricao(),
                dto.valor(),
                dto.tipo(),
                dto.status(),
                dto.data(),
                usuarioFake,
                categoriaFake
        );

        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacaoSalvaFake);

        TransacaoResponseDTO resultado = transacaoService.criar(dto);

        assertEquals(dto.descricao(), resultado.descricao());
        assertEquals(dto.valor(), resultado.valor());
        assertEquals(dto.tipo(), resultado.tipo());
        assertEquals(dto.status(), resultado.status());
        assertEquals(dto.usuarioId(), resultado.usuarioId());
        assertEquals(categoriaFake.getNome(), resultado.nomeCategoria());
        assertEquals(transacaoId, resultado.id());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        UUID usuarioId = UUID.randomUUID();
        UUID categoriaId = UUID.randomUUID();

        TransacaoRequestDTO dto = new TransacaoRequestDTO(
                "Salário",
                new BigDecimal("5000.00"),
                TipoTransacao.ENTRADA,
                StatusTransacao.PAGA,
                LocalDate.now(),
                usuarioId,
                categoriaId
        );

        when(usuarioRepository.findById(dto.usuarioId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            transacaoService.criar(dto);
        });
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoEncontrada() {
        UUID usuarioId = UUID.randomUUID();
        UUID categoriaId = UUID.randomUUID();

        TransacaoRequestDTO dto = new TransacaoRequestDTO(
                "Salário",
                new BigDecimal("5000.00"),
                TipoTransacao.ENTRADA,
                StatusTransacao.PAGA,
                LocalDate.now(),
                usuarioId,
                categoriaId
        );

        Usuario usuarioFake = new Usuario(usuarioId, "Arthur", "arthur@email.com", "123456");

        when(usuarioRepository.findById(dto.usuarioId())).thenReturn(Optional.of(usuarioFake));
        when(categoriaRepository.findById(dto.categoriaId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            transacaoService.criar(dto);
        });
    }

    @Test
    void deletarTransacao() {
        UUID id = UUID.randomUUID();

        Transacao transacaoFake = new Transacao(
                id,
                "Salário",
                new BigDecimal("5000.00"),
                TipoTransacao.ENTRADA,
                StatusTransacao.PAGA,
                LocalDate.now(),
                null,
                null
        );

        when(transacaoRepository.findById(id)).thenReturn(Optional.of(transacaoFake));

        transacaoService.delete(id);

        verify(transacaoRepository).delete(transacaoFake);
    }

    @Test
    void deveLancarExcecaoQuandoTransacaoNaoEncontradaParaDeletar() {

        UUID id = UUID.randomUUID();

        when(transacaoRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            transacaoService.delete(id);
        });
    }

}

package br.com.limmazk.api.controle.financeiro.repository;

import br.com.limmazk.api.controle.financeiro.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {

    List<Transacao> findByUsuarioId(UUID usuario);
}

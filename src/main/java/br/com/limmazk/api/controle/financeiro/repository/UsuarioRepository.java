package br.com.limmazk.api.controle.financeiro.repository;

import br.com.limmazk.api.controle.financeiro.entity.Transacao;
import br.com.limmazk.api.controle.financeiro.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}

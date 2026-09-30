package br.com.limmazk.api.controle.financeiro.repository;

import br.com.limmazk.api.controle.financeiro.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
}

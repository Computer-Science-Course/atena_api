package uea.atena_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uea.atena_api.models.Permissao;


@Repository
public interface PermissaoRepository  extends JpaRepository<Permissao, Long> {
	

}
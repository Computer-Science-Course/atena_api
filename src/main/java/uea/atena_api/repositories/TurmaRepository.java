package uea.atena_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uea.atena_api.models.Turma;
import uea.atena_api.repositories.turma.TurmaRepositoryQuery;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long>, TurmaRepositoryQuery{

}

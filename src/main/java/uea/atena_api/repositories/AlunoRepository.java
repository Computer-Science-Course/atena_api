package uea.atena_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uea.atena_api.models.Aluno;
import uea.atena_api.repositories.aluno.AlunoRepositoryQuery;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>, AlunoRepositoryQuery {
}

package uea.atena_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uea.atena_api.models.ProvaAluno;

@Repository
public interface ProvaAlunoRepository extends JpaRepository<ProvaAluno, Long> {

}

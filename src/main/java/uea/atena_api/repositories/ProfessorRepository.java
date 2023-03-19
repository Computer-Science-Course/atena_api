package uea.atena_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uea.atena_api.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}

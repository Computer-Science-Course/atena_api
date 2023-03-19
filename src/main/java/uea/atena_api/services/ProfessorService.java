package uea.atena_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.models.Professor;
import uea.atena_api.repositories.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	
	public Professor criar(Professor professor) {
		return professorRepository.save(professor);
	}
	
	public List<Professor> listar(){
		return professorRepository.findAll();
	}
}

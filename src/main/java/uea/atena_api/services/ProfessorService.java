package uea.atena_api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
	
	public Professor buscarPorId(Long codigo) {
		Professor professor = professorRepository.findById(codigo).orElseThrow();
		return professor;
	}
	
	public void deletar(Long codigo) {
		professorRepository.deleteById(codigo);
	}
	
	public Professor atualizar(Long codigo, Professor professor) {
		Professor professorSalva = professorRepository.findById(codigo).orElseThrow();
		BeanUtils.copyProperties(professor, professorSalva, "codigo");
		return professorRepository.save(professorSalva);
	}
}











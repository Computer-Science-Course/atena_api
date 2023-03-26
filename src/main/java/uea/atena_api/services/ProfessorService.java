package uea.atena_api.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.models.Professor;
import uea.atena_api.models.SpecialOperation;
import uea.atena_api.models.enums.SpecialOperations;
import uea.atena_api.repositories.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	
	public Professor criar(Professor professor) {
		Optional<Professor> professorExistente = professorRepository.findById(professor.getCodigo());
	    if(professorExistente.isPresent()) {
	        throw new RuntimeException("Já existe um Professor com o código fornecido.");
	    }
		return professorRepository.save(professor);
	}
	
	public List<Professor> listar(){
		return professorRepository.findAll();
	}
	
	public Professor buscarPorId(Long codigo) {
		Professor professor = professorRepository.findById(codigo).orElseThrow();
		return professor;
	}
	
	public void deletar(Long codigo, SpecialOperation specialOperation) {
		if(Objects.isNull(specialOperation.getSpecialOperation())) {
			throw new RuntimeException("Não é possível apagar Entidade porque possui relações.");			
		}
		if(specialOperation.getSpecialOperation() == SpecialOperations.DELETE_RELETED) {
			professorRepository.deleteById(codigo);
		}
		throw new RuntimeException("Operação especial incorreta.");
	}
	
	public Professor atualizar(Long codigo, Professor professor) {
		Professor professorSalva = professorRepository.findById(codigo).orElseThrow();
		BeanUtils.copyProperties(professor, professorSalva, "codigo");
		return professorRepository.save(professorSalva);
	}
}











package uea.atena_api.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.dto.ResumoTurmaDto;
import uea.atena_api.models.Professor;
import uea.atena_api.models.SpecialOperation;
import uea.atena_api.models.Turma;
import uea.atena_api.models.enums.SpecialOperations;
import uea.atena_api.repositories.ProfessorRepository;
import uea.atena_api.repositories.TurmaRepository;
import uea.atena_api.repositories.filters.TurmaFilter;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private ProfessorRepository professorRepository;
	
	public Turma criar(Turma turma) {
		Optional<Turma> turmaExistente = turmaRepository.findById(turma.getCodigo());
		if (turmaExistente.isPresent()) {
			throw new RuntimeException("Já existe uma Turma com o código fornecido.");
		}
		Professor professor = professorRepository.findById(turma.getProfessor().getCodigo()).orElseThrow();
		return turmaRepository.save(turma);
		}	
	
	public List<ResumoTurmaDto> resumir(TurmaFilter turmaFilter){
		return turmaRepository.filtrar(turmaFilter);
	}

	public Turma buscarPorId(Long codigo) {
		Turma turma = turmaRepository.findById(codigo).orElseThrow();
		return turma;
	}

	public void excluir(Long codigo, SpecialOperation specialOperation) {
		if(Objects.isNull(specialOperation.getSpecialOperation())) {
			throw new RuntimeException("Não é possível apagar Entidade porque possui relações.");			
		}
		if(specialOperation.getSpecialOperation() == SpecialOperations.DELETE_RELETED) {
			turmaRepository.deleteById(codigo);
		}
		throw new RuntimeException("Operação especial incorreta.");
	}

	public Turma atualizar(Long codigo, Turma turma) {
		Turma turmaSalvo = turmaRepository.findById(codigo).orElseThrow();

		Professor professor = professorRepository.findById(turma.getProfessor().getCodigo()).orElseThrow();
		BeanUtils.copyProperties(turma, turmaSalvo, "codigo");
		return turmaRepository.save(turmaSalvo);
	}
}

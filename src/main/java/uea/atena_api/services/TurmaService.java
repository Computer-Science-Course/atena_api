package uea.atena_api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.models.Professor;
import uea.atena_api.models.Turma;
import uea.atena_api.repositories.ProfessorRepository;
import uea.atena_api.repositories.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public Turma criar(Turma turma) {
		Professor professor = professorRepository.findById(
				turma.getProfessor().getCodigo()).orElseThrow();
		return turmaRepository.save(turma);
	}
	
	public List<Turma> listar(){
		return turmaRepository.findAll();
	}
	
	public Turma buscarPorId(Long codigo) {
		Turma turma = turmaRepository.findById(codigo).orElseThrow();
		return turma;
	}
	
	public void excluir(Long codigo) {
		turmaRepository.deleteById(codigo);
	}
	
	public Turma atualizar(Long codigo, Turma turma) {
		Turma turmaSalvo = turmaRepository.findById(codigo).orElseThrow();
		
		Professor professor = professorRepository.findById(
				turma.getProfessor().getCodigo()).orElseThrow();
		BeanUtils.copyProperties(turma, turmaSalvo, "codigo");
		return turmaRepository.save(turmaSalvo);
	}
}

package uea.atena_api.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.models.Aluno;
import uea.atena_api.models.SpecialOperation;
import uea.atena_api.models.Turma;
import uea.atena_api.models.TurmaAluno;
import uea.atena_api.models.enums.SpecialOperations;
import uea.atena_api.repositories.AlunoRepository;
import uea.atena_api.repositories.TurmaAlunoRepository;
import uea.atena_api.repositories.TurmaRepository;

@Service
public class TurmaAlunoService {

	@Autowired
	private TurmaAlunoRepository turmaAlunoRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	public List<TurmaAluno> listar() {
		return turmaAlunoRepository.findAll();
	}

	public TurmaAluno criar(TurmaAluno matricula) {
		Optional<TurmaAluno> alunoExistente = turmaAlunoRepository.findById(matricula.getCodigo());
		if (alunoExistente.isPresent()) {
			throw new RuntimeException("Já existe uma Matricula com o código fornecido.");
		}

		Turma turma = turmaRepository.findById(matricula.getTurma().getCodigo()).orElseThrow();
		Aluno aluno = alunoRepository.findById(matricula.getAluno().getCodigo()).orElseThrow();

		return turmaAlunoRepository.save(matricula);

	}

	public TurmaAluno buscarPorId(Long codigo) {
		TurmaAluno matricula = turmaAlunoRepository.findById(codigo).orElseThrow();
		return matricula;
	}

	public void excluir(Long codigo, SpecialOperation specialOperation) {
		if(Objects.isNull(specialOperation.getSpecialOperation())) {
			throw new RuntimeException("Não é possível apagar Entidade porque possui relações.");			
		}
		if(specialOperation.getSpecialOperation() == SpecialOperations.DELETE_RELETED) {
			turmaAlunoRepository.deleteById(codigo);
		}
		throw new RuntimeException("Operação especial incorreta.");
	}

	public TurmaAluno atualizar(Long codigo, TurmaAluno turmaAluno) {
		TurmaAluno turmaAlunoSalva = turmaAlunoRepository.findById(codigo).orElseThrow();
		BeanUtils.copyProperties(turmaAluno, turmaAlunoSalva, "codigo");
		return turmaAlunoRepository.save(turmaAlunoSalva);

	}

}

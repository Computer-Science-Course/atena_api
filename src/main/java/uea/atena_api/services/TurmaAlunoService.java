package uea.atena_api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.models.Aluno;
import uea.atena_api.models.Turma;
import uea.atena_api.models.TurmaAluno;
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

	public TurmaAluno criar(TurmaAluno turmaAluno) {
		Turma turma = turmaRepository.findById(turmaAluno.getTurma().getCodigo()).orElseThrow();
		Aluno aluno = alunoRepository.findById(turmaAluno.getAluno().getCodigo()).orElseThrow();

		return turmaAlunoRepository.save(turmaAluno);
	}

	public TurmaAluno buscarPorId(Long codigo) {
		TurmaAluno turmaAluno = turmaAlunoRepository.findById(codigo).orElseThrow();
		return turmaAluno;
	}

	public void excluir(Long codigo) {
		turmaAlunoRepository.deleteById(codigo);
	}

	public TurmaAluno atualizar(Long codigo, TurmaAluno turmaAluno) {
		TurmaAluno turmaAlunoSalva = turmaAlunoRepository.findById(codigo).orElseThrow();
		BeanUtils.copyProperties(turmaAluno, turmaAlunoSalva, "codigo");
		return turmaAlunoRepository.save(turmaAlunoSalva);

	}

}

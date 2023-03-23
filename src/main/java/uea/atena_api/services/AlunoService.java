package uea.atena_api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.models.Aluno;
import uea.atena_api.repositories.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public Aluno criar(Aluno aluno) {
		return alunoRepository.save(aluno);
	}

	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}

	public void deletar(Long codigo) {
		alunoRepository.deleteById(codigo);
	}

	public Aluno buscarPorId(Long codigo) {
		Aluno aluno = alunoRepository.findById(codigo).orElseThrow();
		return aluno;
	}

	public Aluno atualizar(Long codigo, Aluno aluno) {
		Aluno alunoSalva = alunoRepository.findById(codigo).orElseThrow();
		BeanUtils.copyProperties(aluno, alunoSalva, "codigo");
		return alunoRepository.save(alunoSalva);
	}
}

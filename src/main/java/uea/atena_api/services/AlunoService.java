package uea.atena_api.services;

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
	
}

package uea.atena_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.repositories.TurmaAlunoRepository;

@Service
public class TurmaAluno {
	
	@Autowired
	private TurmaAlunoRepository turmaAlunoRepository;
	
}

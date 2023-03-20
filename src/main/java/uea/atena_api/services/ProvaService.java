package uea.atena_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.models.Prova;
import uea.atena_api.models.Turma;
import uea.atena_api.repositories.ProvaRepository;
import uea.atena_api.repositories.TurmaRepository;

@Service
public class ProvaService {
	
	@Autowired
	private ProvaRepository provaRepository;
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	public Prova criar(Prova prova) {
		Turma turma = turmaRepository.findById(
				prova.getTurma().getCodigo()).orElseThrow();
		return provaRepository.save(prova);
	}

	public List<Prova> listar() {
		return provaRepository.findAll();
	}
}

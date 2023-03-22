package uea.atena_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.models.ProvaAluno;
import uea.atena_api.repositories.ProvaAlunoRepository;

@Service
public class ProvaAlunoService {

	@Autowired
	private ProvaAlunoRepository provaAlunoRepository;

	public List<ProvaAluno> listar() {
		return provaAlunoRepository.findAll();
	}

	public void deletar(Long codigo) {
		provaAlunoRepository.deleteById(codigo);
	}
}

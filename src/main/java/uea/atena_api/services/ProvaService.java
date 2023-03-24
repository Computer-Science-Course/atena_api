package uea.atena_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
		Optional<Prova> provaExistente = provaRepository.findById(prova.getCodigo());
	    if(provaExistente.isPresent()) {
	        throw new RuntimeException("Já existe uma Prova com o código fornecido.");
	    }
		
		Turma turma = turmaRepository.findById(prova.getTurma().getCodigo()).orElseThrow();
		return provaRepository.save(prova);
	}

	public List<Prova> listar() {
		return provaRepository.findAll();
	}

	public Prova buscarPorCodigo(Long codigo) {
		Prova prova = provaRepository.findById(codigo).orElseThrow();
		return prova;
	}

	public Prova atualizar(Long codigo, Prova prova) {
		Prova provaSalva = provaRepository.findById(codigo).orElseThrow();
		BeanUtils.copyProperties(prova, provaSalva, "codigo");
		return provaRepository.save(provaSalva);
	}

	public void excluir(Long codigo) {
		provaRepository.deleteById(codigo);
	}
}

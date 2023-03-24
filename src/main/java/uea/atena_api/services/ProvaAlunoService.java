package uea.atena_api.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.models.ProvaAluno;
import uea.atena_api.models.SpecialOperation;
import uea.atena_api.models.enums.SpecialOperations;
import uea.atena_api.repositories.ProvaAlunoRepository;

@Service
public class ProvaAlunoService {

	@Autowired
	private ProvaAlunoRepository provaAlunoRepository;

	public List<ProvaAluno> listar() {
		return provaAlunoRepository.findAll();
	}

	public ProvaAluno criar(ProvaAluno correcao) {
		Optional<ProvaAluno> correcaoExistente = provaAlunoRepository.findById(correcao.getCodigo());
	    if(correcaoExistente.isPresent()) {
	        throw new RuntimeException("Já existe uma Correção com o código fornecido.");
	    }
		return provaAlunoRepository.save(correcao);
	}

	public void deletar(Long codigo, SpecialOperation specialOperation) {
		if(Objects.isNull(specialOperation.getSpecialOperation())) {
			throw new RuntimeException("Não é possível apagar Entidade porque possui relações.");			
		}
		if(specialOperation.getSpecialOperation() == SpecialOperations.DELETE_RELETED) {
			provaAlunoRepository.deleteById(codigo);
		}
		throw new RuntimeException("Operação especial incorreta.");
	}

	public ProvaAluno buscarPorId(Long codigo) {
		ProvaAluno provaAluno = provaAlunoRepository.findById(codigo).orElseThrow();
		return provaAluno;
	}

	public ProvaAluno atualizar(Long codigo, ProvaAluno provaAluno) {
		ProvaAluno provaAlunoSalva = provaAlunoRepository.findById(codigo).orElseThrow();
		BeanUtils.copyProperties(provaAluno, provaAlunoSalva, "codigo");
		return provaAlunoRepository.save(provaAlunoSalva);

	}
}

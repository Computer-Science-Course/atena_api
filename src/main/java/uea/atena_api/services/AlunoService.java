package uea.atena_api.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uea.atena_api.dto.ResumoAlunoDto;
import uea.atena_api.models.Aluno;
import uea.atena_api.models.SpecialOperation;
import uea.atena_api.models.enums.SpecialOperations;
import uea.atena_api.repositories.AlunoRepository;
import uea.atena_api.repositories.filters.AlunoFilter;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public Page<ResumoAlunoDto> resumir(AlunoFilter alunoFilter, Pageable pageable){
		return alunoRepository.filtrar(alunoFilter, pageable);
	}
	
	public Aluno criar(Aluno aluno) {
		Optional<Aluno> alunoExistente = alunoRepository.findById(aluno.getCodigo());
	    if(alunoExistente.isPresent()) {
	        throw new RuntimeException("Já existe um Aluno com o código fornecido.");
	    }
		return alunoRepository.save(aluno);
	}

	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}

	public void excluir(Long codigo) {
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

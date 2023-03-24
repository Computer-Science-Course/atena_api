package uea.atena_api.repositories.aluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uea.atena_api.dto.ResumoAlunoDto;
import uea.atena_api.repositories.filters.AlunoFilter;

public interface AlunoRepositoryQuery {
	
	public Page<ResumoAlunoDto> filtrar(AlunoFilter alunoFilter, Pageable pageable);

}

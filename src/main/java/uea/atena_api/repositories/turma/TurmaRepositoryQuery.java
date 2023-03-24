package uea.atena_api.repositories.turma;

import java.util.List;

import uea.atena_api.dto.ResumoTurmaDto;
import uea.atena_api.repositories.filters.TurmaFilter;

public interface TurmaRepositoryQuery {
	
	public List<ResumoTurmaDto> filtrar(TurmaFilter turmaFilter);

}

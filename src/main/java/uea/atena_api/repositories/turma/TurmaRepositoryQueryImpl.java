package uea.atena_api.repositories.turma;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ObjectUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import uea.atena_api.dto.ResumoTurmaDto;
import uea.atena_api.models.Turma;
import uea.atena_api.repositories.filters.TurmaFilter;

public class TurmaRepositoryQueryImpl implements TurmaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<ResumoTurmaDto> filtrar(TurmaFilter turmaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<ResumoTurmaDto> criteria = builder.createQuery(ResumoTurmaDto.class);
		Root<Turma> root = criteria.from(Turma.class);

		criteria.select(builder.construct(ResumoTurmaDto.class, root.get("codigo"), root.get("nome"),
				root.get("professor").get("nome")));

		Predicate[] predicates = criarRestricoes(turmaFilter, builder, root);
		if (predicates.length > 0) {
			criteria.where(predicates);
		}

		List<ResumoTurmaDto> returnList = manager.createQuery(criteria).getResultList();

		return returnList;
	}

	private Predicate[] criarRestricoes(TurmaFilter turmaFilter, CriteriaBuilder builder, Root<Turma> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(turmaFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + turmaFilter.getNome().toLowerCase() + "%"));
		}
		
		if (!ObjectUtils.isEmpty(turmaFilter.getProfessor())) {
			predicates.add(builder.like(builder.lower(root.get("professor").get("nome")),
					"%" + turmaFilter.getProfessor().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}



}
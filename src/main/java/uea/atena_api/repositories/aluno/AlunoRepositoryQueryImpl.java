package uea.atena_api.repositories.aluno;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import uea.atena_api.dto.ResumoAlunoDto;
import uea.atena_api.models.Aluno;
import uea.atena_api.repositories.filters.AlunoFilter;

public class AlunoRepositoryQueryImpl implements AlunoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ResumoAlunoDto> filtrar(AlunoFilter alunoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<ResumoAlunoDto> criteria = builder.createQuery(ResumoAlunoDto.class);
		Root<Aluno> root = criteria.from(Aluno.class);

		criteria.select(builder.construct(ResumoAlunoDto.class, root.get("codigo"), root.get("nome"),
				root.get("matricula")));

		Predicate[] predicates = criarRestricoes(alunoFilter, builder, root);
		if (predicates.length > 0) {
			criteria.where(predicates);
		}

		TypedQuery<ResumoAlunoDto> query = manager.createQuery(criteria);

		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(alunoFilter));
	}

	private Long total(AlunoFilter alunoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Aluno> root = criteria.from(Aluno.class);

		Predicate[] predicates = criarRestricoes(alunoFilter, builder, root);
		if (predicates.length > 0) {
			criteria.where(predicates);
		}

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<ResumoAlunoDto> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalDeRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalDeRegistroPorPagina);
		
	}


	private Predicate[] criarRestricoes(AlunoFilter alunoFilter, CriteriaBuilder builder,
			Root<Aluno> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(alunoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + alunoFilter.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
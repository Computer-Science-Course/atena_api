package uea.atena_api.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProvaAluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "prova_aluno_seq_generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "prova_aluno_seq_generator", initialValue = 51, sequenceName = "SEQ_PROVA_ALUNO", allocationSize = 1)
	private Long codigo;

	@NotNull(message = "Nota obrigátoria")
	private BigDecimal pontuacao;

	@JsonIgnoreProperties({"data_aplicacao", "turma"})
	@ManyToOne
	@JoinColumn(name = "codigo_prova")
	@NotNull(message = "Prova obrigátoria")
	private Prova prova;

	@JsonIgnoreProperties({"matricula"})
	@ManyToOne
	@JoinColumn(name = "codigo_aluno")
	@NotNull(message = "Aluno obrigátoria")
	private Aluno aluno;

	public ProvaAluno() {
	}

	public ProvaAluno(Long codigo, BigDecimal pontuacao, Prova prova, Aluno aluno) {
		super();
		this.codigo = codigo;
		this.pontuacao = pontuacao;
		this.prova = prova;
		this.aluno = aluno;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(BigDecimal pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProvaAluno other = (ProvaAluno) obj;
		return Objects.equals(codigo, other.codigo);
	}
}

package uea.atena_api.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ProvaAluno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "Nota obrigátoria")
	private BigDecimal pontuacao;
	
	@ManyToOne
	@JoinColumn(name="codigo_prova")
	private Prova prova;
	 
	@ManyToOne
	@JoinColumn(name="codigo_aluno")
	private Aluno aluno;
	
	public ProvaAluno() {
	}
	
	public ProvaAluno(Long codigo, BigDecimal pontuacao) {
		super();
		this.codigo = codigo;
		this.pontuacao = pontuacao;
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

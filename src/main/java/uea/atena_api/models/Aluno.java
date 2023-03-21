package uea.atena_api.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "aluno_seq_generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "aluno_seq_generator", initialValue = 51, sequenceName = "SEQ_ALUNO", allocationSize = 1)
	private Long codigo;

	@NotBlank(message = "Nome é obrigátorio")
	private String nome;

	@NotNull(message = "Matricula obrigátorio")
	private Long matricula;

	public Aluno() {
	}

	public Aluno(Long codigo, String nome, Long matricula) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.matricula = matricula;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
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
		Aluno other = (Aluno) obj;
		return Objects.equals(codigo, other.codigo);
	}

}

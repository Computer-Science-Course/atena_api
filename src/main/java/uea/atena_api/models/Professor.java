package uea.atena_api.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "professor_seq_generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "professor_seq_generator", initialValue = 51, sequenceName = "SEQ_PROFESSOR", allocationSize = 1)
	private Long codigo;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	public Professor() {
	}

	public Professor(Long codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
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
		Professor other = (Professor) obj;
		return Objects.equals(codigo, other.codigo);
	}

}

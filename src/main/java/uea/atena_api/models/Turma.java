package uea.atena_api.models;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "turma_seq_generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "turma_seq_generator", initialValue = 51, sequenceName = "SEQ_TURMA", allocationSize = 1)
	private Long codigo;

	@NotBlank(message = "Nome é obrigátorio")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "codigo_professor", referencedColumnName = "codigo")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Professor professor;

	public Turma() {
	}

	public Turma(Long codigo, @NotBlank(message = "Nome é obrigátorio") String nome, Professor professor) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.professor = professor;
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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
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
		Turma other = (Turma) obj;
		return Objects.equals(codigo, other.codigo);
	}

}

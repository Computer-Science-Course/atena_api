package uea.atena_api.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
public class TurmaAluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "turma_aluno_seq_generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "turma_aluno_seq_generator", initialValue = 51, sequenceName = "SEQ_TURMA_ALUNO", allocationSize = 1)
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "codigo_turma")
	@NotNull(message = "Turma é obrigatório")
	private Turma turma;

	@ManyToOne
	@JoinColumn(name = "codigo_aluno")
	@NotNull(message = "Aluno é obrigatório")
	private Aluno aluno;

	public TurmaAluno() {

	}

	public TurmaAluno(Long codigo, Turma turma, Aluno aluno) {
		super();
		this.codigo = codigo;
		this.turma = turma;
		this.aluno = aluno;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
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
		TurmaAluno other = (TurmaAluno) obj;
		return Objects.equals(codigo, other.codigo);
	}

}

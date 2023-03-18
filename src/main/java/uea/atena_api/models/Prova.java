package uea.atena_api.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Prova implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message="Titulo é obrigatório!")
	private String titulo;
	
	@NotNull(message="Data de aplicacao é obrigatório!")
	private LocalDate data_aplicacao;
	
	@NotNull(message = "Turma é obrigatória")
	@ManyToOne
	private Turma turma;

	public Prova() {
	}

	public Prova(Long codigo, String titulo, LocalDate data_aplicacao, Turma turma) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.data_aplicacao = data_aplicacao;
		this.turma = turma;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getData_aplicacao() {
		return data_aplicacao;
	}

	public void setData_aplicacao(LocalDate data_aplicacao) {
		this.data_aplicacao = data_aplicacao;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
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
		Prova other = (Prova) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	

}

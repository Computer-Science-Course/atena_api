package uea.atena_api.dto;

public class ResumoAlunoDto {

	private Long cogigo;
	private String nome;
	private Long matricula;

	public ResumoAlunoDto() {
		super();
	}

	public ResumoAlunoDto(Long cogigo, String nome, Long matricula) {
		super();
		this.cogigo = cogigo;
		this.nome = nome;
		this.matricula = matricula;
	}

	public Long getCogigo() {
		return cogigo;
	}

	public void setCogigo(Long cogigo) {
		this.cogigo = cogigo;
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

}

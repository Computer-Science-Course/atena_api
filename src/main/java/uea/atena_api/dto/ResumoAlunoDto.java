package uea.atena_api.dto;

public class ResumoAlunoDto {

	private Long codigo;
	private String nome;
	private Long matricula;

	public ResumoAlunoDto() {
		super();
	}

	public ResumoAlunoDto(Long codigo, String nome, Long matricula) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.matricula = matricula;
	}

	public Long getcodigo() {
		return codigo;
	}

	public void setcodigo(Long codigo) {
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

}

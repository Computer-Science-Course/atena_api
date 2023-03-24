package uea.atena_api.dto;

public class ResumoTurmaDto {

	private Long codigo;
	private String nome;
	private String professor;

	public ResumoTurmaDto() {
		super();

	}
	
	public ResumoTurmaDto(Long codigo, String nome, String professor) {
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

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	

}

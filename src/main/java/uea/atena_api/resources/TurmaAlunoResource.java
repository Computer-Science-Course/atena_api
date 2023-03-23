package uea.atena_api.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import uea.atena_api.models.TurmaAluno;
import uea.atena_api.services.TurmaAlunoService;

@RestController
@RequestMapping("/matricula")
public class TurmaAlunoResource {

	@Autowired
	private TurmaAlunoService turmaAlunoService;

	@GetMapping
	public ResponseEntity<List<TurmaAluno>> listar() {
		List<TurmaAluno> matriculas = turmaAlunoService.listar();
		return ResponseEntity.ok().body(matriculas);
	}

	@PostMapping
	public ResponseEntity<TurmaAluno> criar(@Valid @RequestBody TurmaAluno matricula) {
		System.out.println("<<" + matricula.getAluno().getNome() + ">>");
		TurmaAluno matriculaSalva = turmaAlunoService.criar(matricula);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(matriculaSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(matriculaSalva);
	}

}

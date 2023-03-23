package uea.atena_api.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@PostMapping
	public ResponseEntity<TurmaAluno> criar(@Valid @RequestBody TurmaAluno matricula) {
		System.out.println("<<" + matricula.getAluno().getNome() + ">>");
		TurmaAluno matriculaSalva = turmaAlunoService.criar(matricula);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(matriculaSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(matriculaSalva);
	}

}

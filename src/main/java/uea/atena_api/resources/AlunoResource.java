package uea.atena_api.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import uea.atena_api.models.Aluno;
import uea.atena_api.services.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoResource {

	@Autowired
	private AlunoService alunoService;

	@PostMapping
	public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
		Aluno alunoSalva = alunoService.criar(aluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(alunoSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(alunoSalva);
	}

	@GetMapping
	public ResponseEntity<List<Aluno>> listar() {
		List<Aluno> aluno = alunoService.listar();
		return ResponseEntity.ok().body(aluno);
	}

}

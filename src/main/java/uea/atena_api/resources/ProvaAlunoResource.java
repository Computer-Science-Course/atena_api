package uea.atena_api.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import uea.atena_api.models.ProvaAluno;
import uea.atena_api.models.SpecialOperation;
import uea.atena_api.services.ProvaAlunoService;

@RestController
@RequestMapping("/correcoes")
public class ProvaAlunoResource {

	@Autowired
	private ProvaAlunoService provaAlunoService;

	@PostMapping
	public ResponseEntity<ProvaAluno> criar(@Valid @RequestBody ProvaAluno correcao) {
		ProvaAluno correcaoSalva = provaAlunoService.criar(correcao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(correcaoSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(correcaoSalva);
	}

	@GetMapping
	public ResponseEntity<List<ProvaAluno>> listar() {
		List<ProvaAluno> correcoes = provaAlunoService.listar();
		return ResponseEntity.ok().body(correcoes);
	}

	@Operation(summary = "Delete a ProvaAluno by its id")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo, SpecialOperation specialOperation) {
		provaAlunoService.deletar(codigo, specialOperation);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<ProvaAluno> buscarPorId(@PathVariable Long codigo) {
		ProvaAluno correcao = provaAlunoService.buscarPorId(codigo);
		return ResponseEntity.ok().body(correcao);
	}

	@PutMapping(value = "/{codigo}")
	public ResponseEntity<ProvaAluno> atualizar(@PathVariable Long codigo, @Valid @RequestBody ProvaAluno correcao) {
		ProvaAluno correcaoSalva = provaAlunoService.atualizar(codigo, correcao);
		return ResponseEntity.ok().body(correcaoSalva);
	}
}

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import uea.atena_api.models.Aluno;
import uea.atena_api.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

	@Autowired
	private AlunoService alunoService;

	@PostMapping
	public ResponseEntity<Aluno> criar(@Valid @RequestBody Aluno aluno) {
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
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Aluno> buscarPorId(@PathVariable Long codigo){
		Aluno aluno = alunoService.buscarPorId(codigo);
		return ResponseEntity.ok().body(aluno);
	}
	
	@Operation(summary = "Delete a student by its id")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo){
		alunoService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Aluno>atualizar(@PathVariable Long codigo,@Valid@RequestBody Aluno aluno){
		Aluno alunoSalva = alunoService.atualizar(codigo, aluno);
		return ResponseEntity.ok().body(alunoSalva);
	}

}

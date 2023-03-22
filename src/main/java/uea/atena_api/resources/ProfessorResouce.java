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

import jakarta.validation.Valid;
import uea.atena_api.models.Professor;
import uea.atena_api.services.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorResouce {

	@Autowired
	private ProfessorService professorService;

	@PostMapping
	public ResponseEntity<Professor> criar(@Valid @RequestBody Professor professor) {
		Professor professorSalva = professorService.criar(professor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(professorSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(professorSalva);
	}

	@GetMapping
	public ResponseEntity<List<Professor>> listar() {
		List<Professor> professor = professorService.listar();
		return ResponseEntity.ok().body(professor);
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Professor> buscarPorId(@PathVariable Long codigo) {
		Professor professor = professorService.buscarPorId(codigo);
		return ResponseEntity.ok().body(professor);
	}

	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		professorService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Professor> atualizar(@PathVariable Long codigo, @Valid @RequestBody Professor professor) {
		Professor professorSalva = professorService.atualizar(codigo, professor);
		return ResponseEntity.ok().body(professorSalva);
	}

}

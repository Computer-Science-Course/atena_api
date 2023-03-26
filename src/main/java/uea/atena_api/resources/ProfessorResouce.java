package uea.atena_api.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import uea.atena_api.models.Professor;
import uea.atena_api.models.SpecialOperation;
import uea.atena_api.services.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorResouce {

	@Autowired
	private ProfessorService professorService;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PROFESSOR') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Professor> criar(@Valid @RequestBody Professor professor) {
		Professor professorSalva = professorService.criar(professor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(professorSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(professorSalva);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PROFESSOR') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<List<Professor>> listar() {
		List<Professor> professor = professorService.listar();
		return ResponseEntity.ok().body(professor);
	}

	@GetMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PROFESSOR') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Professor> buscarPorId(@PathVariable Long codigo) {
		Professor professor = professorService.buscarPorId(codigo);
		return ResponseEntity.ok().body(professor);
	}

	@Operation(summary = "Delete a teacher by their id")
	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PROFESSOR') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo, SpecialOperation specialOperation) {
		professorService.deletar(codigo, specialOperation);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_PROFESSOR') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Professor> atualizar(@PathVariable Long codigo, @Valid @RequestBody Professor professor) {
		Professor professorSalva = professorService.atualizar(codigo, professor);
		return ResponseEntity.ok().body(professorSalva);
	}

}

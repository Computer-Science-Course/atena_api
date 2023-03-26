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

import jakarta.validation.Valid;
import uea.atena_api.models.SpecialOperation;
import uea.atena_api.models.TurmaAluno;
import uea.atena_api.services.TurmaAlunoService;

@RestController
@RequestMapping("/matriculas")
public class TurmaAlunoResource {

	@Autowired
	private TurmaAlunoService turmaAlunoService;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TURMAALUNO') and hasAuthority('SCOPE_write')")
	public ResponseEntity<TurmaAluno> criar(@Valid @RequestBody TurmaAluno matricula) {
		System.out.println("<<" + matricula.getAluno().getNome() + ">>");
		TurmaAluno matriculaSalva = turmaAlunoService.criar(matricula);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(matriculaSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(matriculaSalva);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TURMAALUNO') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<List<TurmaAluno>> listar() {
		List<TurmaAluno> matriculas = turmaAlunoService.listar();
		return ResponseEntity.ok().body(matriculas);
	}

	@GetMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TURMAALUNO') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<TurmaAluno> buscarPorId(@PathVariable Long codigo) {
		TurmaAluno turmaAluno = turmaAlunoService.buscarPorId(codigo);
		return ResponseEntity.ok().body(turmaAluno);
	}

	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_TURMAALUNO') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Void> excluir(@PathVariable Long codigo, SpecialOperation specialOperation) {
		turmaAlunoService.excluir(codigo, specialOperation);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_TURMAALUNO') and hasAuthority('SCOPE_write')")
	public ResponseEntity<TurmaAluno> atualizar(@PathVariable Long codigo, @Valid @RequestBody TurmaAluno matricula) {
		TurmaAluno matriculaSalva = turmaAlunoService.atualizar(codigo, matricula);
		return ResponseEntity.ok().body(matriculaSalva);
	}

}

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
import uea.atena_api.dto.ResumoTurmaDto;
import uea.atena_api.models.SpecialOperation;
import uea.atena_api.models.Turma;
import uea.atena_api.repositories.filters.TurmaFilter;
import uea.atena_api.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {
	
	@Autowired
	private TurmaService turmaService;
	
	@PostMapping
	public ResponseEntity<Turma> criar(@Valid @RequestBody Turma turma){
		Turma turmaSalva = turmaService.criar(turma);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(turmaSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(turmaSalva);
	}
	
	@GetMapping	
	public ResponseEntity<List<ResumoTurmaDto>> resumir(TurmaFilter turmaFilter) {
		List<ResumoTurmaDto> resumos = turmaService.resumir(turmaFilter);
		return ResponseEntity.ok().body(resumos);
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Turma> buscarPorId(@PathVariable Long codigo) {
		Turma turma = turmaService.buscarPorId(codigo);
		return ResponseEntity.ok().body(turma);
	}
	
	@Operation(summary = "Delete a class by its id")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> excluir(@PathVariable Long codigo, SpecialOperation specialOperation) {
		turmaService.excluir(codigo, specialOperation);
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Turma> atualizar(@PathVariable Long codigo, @Valid @RequestBody Turma turma) {
		Turma turmaSalva = turmaService.atualizar(codigo, turma);
		return ResponseEntity.ok().body(turmaSalva);

	}

}

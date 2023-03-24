package uea.atena_api.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import uea.atena_api.dto.ResumoAlunoDto;
import uea.atena_api.models.Aluno;
import uea.atena_api.models.SpecialOperation;
import uea.atena_api.repositories.filters.AlunoFilter;
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
	public ResponseEntity<Page<ResumoAlunoDto>> resumir(AlunoFilter alunoFilter, Pageable pageable) {
		Page<ResumoAlunoDto> resumos = alunoService.resumir(alunoFilter, pageable);
		return ResponseEntity.ok().body(resumos);
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Aluno> buscarPorId(@PathVariable Long codigo){
		Aluno aluno = alunoService.buscarPorId(codigo);
		return ResponseEntity.ok().body(aluno);
	}
	
	@Operation(summary = "Delete a student by its id")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo, @RequestBody SpecialOperation specialOperation){
		alunoService.deletar(codigo, specialOperation);
		return ResponseEntity.noContent().build();
	}
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Aluno>atualizar(@PathVariable Long codigo,@Valid@RequestBody Aluno aluno){
		Aluno alunoSalva = alunoService.atualizar(codigo, aluno);
		return ResponseEntity.ok().body(alunoSalva);
	}

}

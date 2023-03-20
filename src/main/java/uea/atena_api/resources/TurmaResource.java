package uea.atena_api.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import uea.atena_api.models.Turma;
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
	public ResponseEntity<List<Turma>> listar(){
		List<Turma> turma = turmaService.listar();
		return ResponseEntity.ok().body(turma);
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Turma> buscarPorId(@PathVariable Long codigo) {
		Turma turma = turmaService.buscarPorId(codigo);
		return ResponseEntity.ok().body(turma);
	}

}

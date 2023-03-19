package uea.atena_api.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import uea.atena_api.models.Professor;
import uea.atena_api.services.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorResouce {
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping
	public ResponseEntity<Professor> criar(@RequestBody Professor professor){
		Professor professorSalva = professorService.criar(professor);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(professorSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(professorSalva);
	} 
	
	@GetMapping
	public ResponseEntity<List<Professor>> listar(){
		List<Professor> professor = professorService.listar();
		return ResponseEntity.ok().body(professor);
	}

}

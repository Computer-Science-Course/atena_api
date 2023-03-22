package uea.atena_api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uea.atena_api.models.ProvaAluno;
import uea.atena_api.services.ProvaAlunoService;

@RestController
@RequestMapping("/correcoes")
public class ProvaAlunoResource {

	@Autowired
	private ProvaAlunoService provaAlunoService;

	@GetMapping
	public ResponseEntity<List<ProvaAluno>> listar() {
		List<ProvaAluno> provaAluno = provaAlunoService.listar();
		return ResponseEntity.ok().body(provaAluno);
	}

	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		provaAlunoService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<ProvaAluno> buscarPorId(@PathVariable Long codigo) {
		ProvaAluno provaAluno = provaAlunoService.buscarPorId(codigo);
		return ResponseEntity.ok().body(provaAluno);
	}
}

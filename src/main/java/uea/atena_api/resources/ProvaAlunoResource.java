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

import jakarta.validation.Valid;
import uea.atena_api.models.ProvaAluno;
import uea.atena_api.services.ProvaAlunoService;

@RestController
@RequestMapping("/correcoes")
public class ProvaAlunoResource {

	@Autowired
	private ProvaAlunoService provaAlunoService;
	
	@PostMapping
	public ResponseEntity<ProvaAluno> criar(@Valid @RequestBody ProvaAluno correcao) {
		System.out.println("<<" + correcao.getPontuacao() + ">>");
		ProvaAluno correcaoSalva = provaAlunoService.criar(correcao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(correcaoSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(correcaoSalva);
	}

	@GetMapping
	public ResponseEntity<List<ProvaAluno>> listar() {
		List<ProvaAluno> provaAluno = provaAlunoService.listar();
		return ResponseEntity.ok().body(provaAluno);
	}
}

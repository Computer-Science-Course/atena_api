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
import uea.atena_api.models.Prova;
import uea.atena_api.services.ProvaService;

@RestController
@RequestMapping("/provas")
public class ProvaResource {

	@Autowired
	private ProvaService provaService;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PROVA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Prova> criar(@Valid @RequestBody Prova prova) {
		Prova provaSalva = provaService.criar(prova);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(provaSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(provaSalva);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PROVA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<List<Prova>> listar() {
		List<Prova> prova = provaService.listar();
		return ResponseEntity.ok().body(prova);
	}

	@GetMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PROVA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Prova> buscarPorCodigo(@PathVariable Long codigo) {
		Prova prova = provaService.buscarPorCodigo(codigo);
		return ResponseEntity.ok().body(prova);
	}

	@PutMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_PROVA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Prova> atualizar(@PathVariable Long codigo, @Valid @RequestBody Prova prova) {
		Prova provaSalva = provaService.atualizar(codigo, prova);
		return ResponseEntity.ok().body(provaSalva);
	}
	
	@Operation(summary = "Delete a proof by its id")
	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PROVA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Void> excluir(@PathVariable Long codigo) {
		provaService.excluir(codigo);
		return ResponseEntity.noContent().build();
	}
}
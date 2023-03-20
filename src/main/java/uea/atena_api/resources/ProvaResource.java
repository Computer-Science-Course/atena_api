package uea.atena_api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uea.atena_api.models.Prova;
import uea.atena_api.services.ProvaService;

@RestController
@RequestMapping("/provas")
public class ProvaResource {

	@Autowired
	private ProvaService provaService;
	
	@GetMapping
	public ResponseEntity<List<Prova>> listar(){
		List<Prova> prova = provaService.listar();
		return ResponseEntity.ok().body(prova);
}
}
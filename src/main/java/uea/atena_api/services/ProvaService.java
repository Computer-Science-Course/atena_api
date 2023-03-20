package uea.atena_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.atena_api.models.Prova;
import uea.atena_api.repositories.ProvaRepository;

@Service
public class ProvaService {
	
	@Autowired
	private ProvaRepository provaRepository;

	public List<Prova> listar() {
		return provaRepository.findAll();
	}
}

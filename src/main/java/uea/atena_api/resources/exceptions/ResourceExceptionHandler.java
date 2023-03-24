package uea.atena_api.resources.exceptions;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice  // Para personalizar os erros de tratamento de excessões
public class ResourceExceptionHandler {
	
	@Autowired // objeto instanciado contendo a mensagem personalizada
	private MessageSource messageSource;
	
	
	
	@ExceptionHandler(NoSuchElementException.class) //Fica esperando identificar o erro especifico
	public ResponseEntity<StandardError> noSuchElementException(NoSuchElementException e, HttpServletRequest request){
		List<String> erros = Arrays.asList(messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale()));
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError err = new StandardError(Instant.now(), status.value(), erros, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class) //Fica esperando identificar o erro especifico
	public ResponseEntity<StandardError> emptyResultDataAccessException(EmptyResultDataAccessException e, HttpServletRequest request){
		List<String> erros = Arrays.asList(messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale()));
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError err = new StandardError(Instant.now(), status.value(), erros, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
}

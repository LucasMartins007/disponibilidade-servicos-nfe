/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selecaoviasoft.statusnfe.api.exception;

import com.selecaoviasoft.statusnfe.domain.exception.DomainException;
import com.selecaoviasoft.statusnfe.api.exception.Error.CampoErro;
import com.selecaoviasoft.statusnfe.domain.exception.EntidadeNaoEncontradaException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 *
 * @author lucas
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(DomainException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Error erro = new Error();
		erro.setStatus(status.value());
		erro.setTitulo(ex.getMessage());
		erro.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleNegocio(DomainException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Error erro = new Error();
		erro.setStatus(status.value());
		erro.setTitulo(ex.getMessage());
		erro.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
		
		ArrayList<CampoErro> camposErro = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			camposErro.add(new Error.CampoErro(nome, mensagem));
		}
		
		Error erro = new Error();
		erro.setStatus(status.value());
		erro.setTitulo("um ou mais campos estão inválidos, faça o preenchimento correto e tente novamente.");
		erro.setDataHora(OffsetDateTime.now());
		erro.setCampos(camposErro);
		
		
		return super.handleExceptionInternal(ex, erro, headers, status, request);
	}


}

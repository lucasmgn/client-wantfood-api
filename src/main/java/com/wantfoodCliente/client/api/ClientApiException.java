package com.wantfoodCliente.client.api;

import org.springframework.web.client.RestClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wantfoodCliente.client.model.Problem;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Problem problem;

	public ClientApiException(String message, RestClientResponseException cause) {
		super(message, cause);
		
		deserializerProblem(cause);
	}
	
	private void deserializerProblem(RestClientResponseException cause) {
		
		//Serializar e descerializar json
		ObjectMapper mapper = new ObjectMapper();
		
		/*
		 * configurando o object mapper a não falhar quando não encontrar propriedades, 
		 * porque o problem não possui "type" e "detail"
		 * */
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
//		Registrando o modulo de Datetime
		mapper.registerModule(new JavaTimeModule());
		mapper.findAndRegisterModules();
		
		try {
			this.problem = mapper.readValue(cause.getResponseBodyAsString(), Problem.class);
		} catch (JsonProcessingException e) {
			log.warn("Não foi possível desserializar a resposta em um problema", e);
		}
	}
	
	

}

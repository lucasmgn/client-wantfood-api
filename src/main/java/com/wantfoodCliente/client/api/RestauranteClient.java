package com.wantfoodCliente.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.wantfoodCliente.client.model.RestauranteModel;
import com.wantfoodCliente.client.model.RestauranteResumoDTO;
import com.wantfoodCliente.client.model.input.RestauranteInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {

	private static final String RESOURCE_PATH = "/restaurantes";

	private String url;

	private RestTemplate restTemplate;

	public List<RestauranteResumoDTO> listar(){
		try {
			URI resourceUri = URI.create(url + RESOURCE_PATH);
			
			//Pegando Json da URI e desserializando em um objeto RestauranteResumoDTO
			RestauranteResumoDTO[] restaurantes = restTemplate
					.getForObject(resourceUri, RestauranteResumoDTO[].class);
			
			return Arrays.asList(restaurantes);
		}catch(RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}
	
	public RestauranteModel adicionar(RestauranteInput restaurante) { 
		URI resourceUri = URI.create(url + RESOURCE_PATH);
		
		 try {
			 return restTemplate.postForObject(resourceUri, restaurante, RestauranteModel.class);
		 }catch(HttpClientErrorException e) {
			 throw new ClientApiException(e.getMessage(), e);
		 }
	}
}

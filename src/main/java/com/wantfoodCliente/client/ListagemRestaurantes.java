package com.wantfoodCliente.client;

import org.springframework.web.client.RestTemplate;

import com.wantfoodCliente.client.api.ClientApiException;
import com.wantfoodCliente.client.api.RestauranteClient;

public class ListagemRestaurantes {

	public static void main(String[] args) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			RestauranteClient client = new RestauranteClient("http://localhost:8080",restTemplate);
			
			client.listar().stream().forEach(restaurante -> System.out.println(restaurante));
		}catch(ClientApiException e) {
			if(e.getProblem() != null) {
				//omprimindo apenas a propriedade Usermessage da classe Problem
				System.out.println(e.getProblem().getUserMessage());
			}else {
				System.out.println("Erro desconhecido");
				e.getStackTrace();
			}
		}
	}

}

package com.wantfoodCliente.client;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.wantfoodCliente.client.api.ClientApiException;
import com.wantfoodCliente.client.api.RestauranteClient;
import com.wantfoodCliente.client.model.RestauranteModel;
import com.wantfoodCliente.client.model.input.CidadeIdInput;
import com.wantfoodCliente.client.model.input.CozinhaIdInput;
import com.wantfoodCliente.client.model.input.EnderecoInput;
import com.wantfoodCliente.client.model.input.RestauranteInput;

public class InclusaoRestaurantes {
	public static void main(String[] args) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			RestauranteClient client = new RestauranteClient("http://localhost:8080", restTemplate);

			
			var cozinha = new CozinhaIdInput();
			cozinha.setId(1L);
			
			var cidade = new CidadeIdInput();
			cidade.setId(1L);
			
			var endereco = new EnderecoInput();
			endereco.setCidade(cidade);
			endereco.setCep("40430390");
			endereco.setBairro("Centro");
			endereco.setNumero("321");
			endereco.setLogradouro("Rua Lima XS");
			endereco.setComplemento("Primeiro andar");

			var restaurante = new RestauranteInput();
			restaurante.setCozinha(new CozinhaIdInput());
			restaurante.setCozinha(cozinha);
			restaurante.setEndereco(endereco);
			restaurante.setNome(" ");
			restaurante.setTaxaFrete(new BigDecimal(9.65));
			
			RestauranteModel restauranteModel = client.adicionar(restaurante);
			
			System.out.println(restauranteModel);
		} catch (ClientApiException e) {
			if (e.getProblem() != null) {
				// omprimindo apenas a propriedade Usermessage da classe Problem
				System.out.println(e.getProblem().getUserMessage());
				
				e.getProblem().getObjects().stream()
		          .forEach(p -> System.out.println("- " + p.getUserMessage()));
			} else {
				System.out.println("Erro desconhecido");
				e.getStackTrace();
			}
		}
	}
}

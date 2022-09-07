package com.wantfoodCliente.client.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestauranteModel {
	
	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private boolean ativo;
	private boolean aberto; 
	private CozinhaResumoDTO cozinha;
	private EnderecoModel endereco;
	
}

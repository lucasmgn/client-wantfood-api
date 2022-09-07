package com.wantfoodCliente.client.model;

import lombok.Data;

@Data
public class EnderecoModel {
	
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String baiiro;
	private CidadeResumoDTO cidade;
}

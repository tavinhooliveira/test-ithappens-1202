package com.ithappens.model;

public enum Payment {

	AVISTA("avista"), BOLETO("boleto"), CARTAO("cartao");

	private String descricao;

	Payment(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}

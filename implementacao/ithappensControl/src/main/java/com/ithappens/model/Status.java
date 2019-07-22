package com.ithappens.model;

public enum Status {

	ATIVO("ativo"), CANCELADO("cancelelado"), PROCESSADO("processado");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}

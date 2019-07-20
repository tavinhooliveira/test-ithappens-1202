package com.ithappens.model;

public enum TypeRecipe {
	
	ENTRADA("entrada"),
	SAIDA("saida");
	
	private String descricao;
	
	TypeRecipe(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}

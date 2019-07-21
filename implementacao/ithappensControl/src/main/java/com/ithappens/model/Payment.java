package com.ithappens.model;

public enum Payment {
	
		AVISTA("avista"), BOLETO("BOLETO"), CARTAO("cartao");

		private String descricao;

		Payment(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}

}

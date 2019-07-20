package com.ithappens.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.ithappens.validation.SKU;


@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@SKU
	@Column(name = "sku", unique = true)
	@NotBlank(message = "SKU é obrigatório")
	private String sku;
	
	@NotBlank(message = "A descrição é obrigatória")
	@Size(min = 1, max = 50, message = "O tamanho da descrição deve ser entre 1 e 50")
	private String description;
	
	private String barCode;
	
	@DecimalMin("0.01")
	@DecimalMax(value = "9999999.99", message = "O Valor do produto deve ser menor que R$9.999.999,99")
	private BigDecimal value;
	
	private Integer qtdProduct;
	
	@OneToMany(mappedBy = "products")
	private List<OrderedItem> orderedItems;
		
	@PrePersist	
	@PreUpdate
	private void prePersistUpdate(){
		sku = sku.toUpperCase();
	}
	
	

	public Long getCodigo() {
		return codigo;
	}



	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}



	public String getSku() {
		return sku;
	}



	public void setSku(String sku) {
		this.sku = sku;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getBarCode() {
		return barCode;
	}



	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}



	public BigDecimal getValue() {
		return value;
	}



	public void setValue(BigDecimal value) {
		this.value = value;
	}



	public Integer getQtdProduct() {
		return qtdProduct;
	}



	public void setQtdProduct(Integer qtdProduct) {
		this.qtdProduct = qtdProduct;
	}
	
	public List<OrderedItem> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(List<OrderedItem> orderedItems) {
		this.orderedItems = orderedItems;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	
}

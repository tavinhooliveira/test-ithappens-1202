package com.ithappens.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private BigDecimal total = BigDecimal.ZERO;
	
	private Integer qtdProduct;

	@Size(max = 700, message = "A Descrição não pode conter mais de 700 caracteres")
	private String descricao;

	@NotNull(message = "A data é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataOrder;

	@ManyToOne
	@NotNull(message = "O usuário é obrigatório")
	@JoinColumn(name = "codigo_users")
	private User users;

	@ManyToOne
	@NotNull(message = "O cliente é obrigatório")
	@JoinColumn(name = "codigo_clients")
	private Client clients;

	@ManyToOne
	@NotNull(message = "A filial é obrigatório")
	@JoinColumn(name = "codigo_branhcs")
	private Branch branchs;

	@OneToMany(mappedBy = "sales")
	private List<OrderedItem> orderedItems;

	public List<OrderedItem> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(List<OrderedItem> orderedItems) {
		this.orderedItems = orderedItems;
	}

	@Enumerated(EnumType.STRING)
	private TypeRecipe tipo;

	@Enumerated(EnumType.STRING)
	private Status status;

	public boolean isOpen() {
		return Status.ATIVO.equals(this.status);
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TypeRecipe getTipo() {
		return tipo;
	}

	public void setTipo(TypeRecipe tipo) {
		this.tipo = tipo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Client getClients() {
		return clients;
	}

	public void setClients(Client clients) {
		this.clients = clients;
	}

	public Branch getBranchs() {
		return branchs;
	}

	public void setBranchs(Branch branchs) {
		this.branchs = branchs;
	}



	public Date getDataOrder() {
		return dataOrder;
	}

	public void setDataOrder(Date dataOrder) {
		this.dataOrder = dataOrder;
	}
	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getQtdProduct() {
		return qtdProduct;
	}

	public void setQtdProduct(Integer qtdProduct) {
		this.qtdProduct = qtdProduct;
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
		Sale other = (Sale) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

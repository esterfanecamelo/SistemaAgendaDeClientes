package org.model;

public class Telefone {

	private Integer id;

	private Integer numero;

	private Cliente cliente;

	public Telefone(Cliente cliente) {
		this.cliente = cliente;
	}

	public Telefone() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", numero=" + numero + ", cliente=" + cliente + "]\n";
	}

}

package model;

import java.util.Objects;

public class Endereco {
	private Integer numero;
	private String cidade;
	private String rua;
	
	public Endereco(Integer numero, String cidade, String rua) {
		this.numero = numero;
		this.cidade = cidade;
		this.rua = rua;
	}
	
	public Endereco(String cidade, String rua) {
		this.cidade = cidade;
		this.rua = rua;
	}
	

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cidade, numero, rua);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(cidade, other.cidade) && Objects.equals(numero, other.numero)
				&& Objects.equals(rua, other.rua);
	}
	
	
}

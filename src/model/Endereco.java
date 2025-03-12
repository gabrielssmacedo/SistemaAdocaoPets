package model;

public class Endereco {
	private Integer numero;
	private String cidade;
	private String rua;
	
	public Endereco(Integer numero, String cidade, String rua) {
		this.numero = numero;
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
}

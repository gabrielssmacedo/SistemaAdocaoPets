package model;

import model.enums.SexoPet;
import model.enums.TipoPet;

public class Pet {
	private String nome;
	private TipoPet tipo;
	private SexoPet sexo;
	private Endereco endereco;
	private Double idade;
	private Double peso;
	private String raca;
	
	public Pet(String nome) {
		if(nome == null) System.out.println(); //lançar exceção
		
		this.nome = nome;
	}

	public Pet(String nome, TipoPet tipo, SexoPet sexo, Endereco endereco, Double idade, Double peso, String raca) {
		this.nome = nome;
		this.tipo = tipo;
		this.sexo = sexo;
		this.endereco = endereco;
		this.idade = idade;
		this.peso = peso;
		this.raca = raca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoPet getTipo() {
		return tipo;
	}

	public void setTipo(TipoPet tipo) {
		this.tipo = tipo;
	}

	public SexoPet getSexo() {
		return sexo;
	}

	public void setSexo(SexoPet sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Double getIdade() {
		return idade;
	}

	public void setIdade(Double idade) {
		this.idade = idade;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}
	
	public boolean cadastrar() {
		///
		///
		return false;
	}
	
	public boolean alterarCadastro() {
		///
		///
		return false;
	}
	
	public boolean deletarCadastro() {
		///
		///
		return false;
	}

	@Override
	public String toString() {
		return getNome() + " - " 
			 + getTipo() + " - "
			 + getSexo() + " - " 
			 + getEndereco().getRua() + ",  "
			 + getEndereco().getNumero() + " - "
			 + getEndereco().getCidade() + " - " 
			 + getIdade() + " - "
			 + getPeso() + " - " 
			 + getRaca();
	}

}

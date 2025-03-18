package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import controller.ConsultaService;
import controller.interfaces.Busca;
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
	private LocalDateTime dataHora;
	
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
	
	public boolean cadastrar(String path) {
		dataHora = LocalDateTime.now();
		StringBuilder nomeArquivo = new StringBuilder();
		
		String[] nomes = this.nome.split(" ");
		StringBuilder nomeMaiusculo = new StringBuilder();
		for(String n : nomes) {
			nomeMaiusculo.append(n.toUpperCase());
		}
		
		nomeArquivo.append(String.valueOf(dataHora.getYear()));
		nomeArquivo.append(String.valueOf(dataHora.getMonthValue()));
		nomeArquivo.append(String.valueOf(dataHora.getDayOfMonth()));
		nomeArquivo.append("T");
		nomeArquivo.append(String.valueOf(dataHora.getHour()));
		nomeArquivo.append(String.valueOf(dataHora.getMinute()));
		nomeArquivo.append("-");
		nomeArquivo.append(nomeMaiusculo.toString());
		nomeArquivo.append(".txt");
		
		path = path + "\\" + nomeArquivo.toString();
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			bw.write(String.format("1 - %s\n", nome));
			bw.write(String.format("2 - %s\n", tipo));
			bw.write(String.format("3 - %s\n", sexo));
			bw.write(String.format("4 - %s\n", 
					  endereco.getRua() + ", "
					+ String.valueOf(endereco.getNumero()) + ", "
					+ endereco.getCidade()));
			bw.write(String.format("5 - %.1f anos\n", idade));
			bw.write(String.format("6 - %.1fkg\n", peso));
			bw.write(String.format("7 - %s\n", raca));
			return true;
		}
		catch(IOException e) {
			System.out.println("Erro na geração do cadastro.");
		}
	
		return false;
	}
	
	public boolean alterarCadastro(String diretorio, Busca tipoBusca) {
		
		
		
		
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
			 + getEndereco().getRua() + ", "
			 + getEndereco().getNumero() + ", "
			 + getEndereco().getCidade() + " - " 
			 + getIdade() + " - "
			 + getPeso() + " - " 
			 + getRaca();
	}

}

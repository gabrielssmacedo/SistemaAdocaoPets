package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

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
	final static String respostaVazia = "Nao Informado";
	final static String italico = "\u001B[3m";
	final static String fechaItalico = "\u001B[0m";
	
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
	
	public void cadastrar(String path) {
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
			if(endereco.getNumero() == null) {
				bw.write(String.format("4 - %s\n", 
						  endereco.getRua() + ", "
						+ respostaVazia + ", "
						+ endereco.getCidade()));
			}
			else {
				bw.write(String.format("4 - %s\n", 
						  endereco.getRua() + ", "
						+ String.valueOf(endereco.getNumero()) + ", "
						+ endereco.getCidade()));
			}
			if(idade == null) bw.write(String.format("5 - %s\n", respostaVazia));
			else bw.write(String.format("5 - %.1f anos\n", idade));
			if(peso == null) bw.write(String.format("6 - %s\n", respostaVazia));
			else bw.write(String.format("6 - %.1fkg\n", peso));
			if(raca == null) bw.write(String.format("7 - %s\n", respostaVazia));
			else bw.write(String.format("7 - %s\n", raca));
			System.out.println(italico + "\nPet cadastrado com sucesso.\n" + fechaItalico);
			
		}
		catch(IOException e) {
			System.out.println(italico + "\nErro ao gerar cadastro.\n" + fechaItalico);
		}
	}
	
	public void alterarCadastro(String caminhoArquivo) throws IOException{
		
		if(caminhoArquivo.isEmpty()) throw new IOException(italico + "\nNão foi possivel encontrar o pet.\n" + fechaItalico);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))){
			bw.write(String.format("1 - %s\n", this.nome));
			bw.write(String.format("2 - %s\n", this.tipo));
			bw.write(String.format("3 - %s\n", this.sexo));
			bw.write(String.format("4 - %s\n", 
					  endereco.getRua() + ", "
					+ String.valueOf(endereco.getNumero()) + ", "
					+ endereco.getCidade()));
			bw.write(String.format("5 - %.1f anos\n", this.idade));
			bw.write(String.format("6 - %.1fkg\n", this.peso));
			bw.write(String.format("7 - %s\n", this.raca));
			System.out.println(italico + "\nCadastro alterado com sucesso\n" + fechaItalico);
			
		}
		catch(IOException e) {
			System.out.println(italico + "\nErro ao alterar cadastro.\n" + fechaItalico);
		}
		
	}
	
	public void deletarCadastro(String caminhoArquivo) {
		File arquivo = new File(caminhoArquivo);
		try {
			arquivo.delete();
			System.out.println(italico + "\nCadastro deletado com sucesso.\n" + fechaItalico);
		}
		catch(Exception e) {
			System.out.println(italico + "\nErro ao deletar o Cadastro\n" + fechaItalico);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(endereco, idade, nome, peso, raca, sexo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		return Objects.equals(endereco, other.endereco) && Objects.equals(idade, other.idade)
				&& Objects.equals(nome, other.nome) && Objects.equals(peso, other.peso)
				&& Objects.equals(raca, other.raca) && sexo == other.sexo && tipo == other.tipo;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getNome());
		sb.append(" - ");
		sb.append(getTipo());
		sb.append(" - ");
		sb.append(getSexo());
		sb.append(" - ");
		sb.append(getEndereco().getRua());
		sb.append(", ");
		sb.append(getEndereco().getNumero() == null? respostaVazia : getEndereco().getNumero());
		sb.append(" - ");
		sb.append(getEndereco().getCidade());
		sb.append(" - ");
		
		if(getIdade() == null) sb.append(respostaVazia);
		else sb.append(String.format("%.1f anos", idade));
		sb.append(" - ");
		
		if(getPeso() == null) sb.append(respostaVazia);
		else sb.append(String.format("%.1fkg", peso));
		sb.append(" - ");

		sb.append(getRaca() == null? respostaVazia : raca);
		
		return sb.toString();
	}

}

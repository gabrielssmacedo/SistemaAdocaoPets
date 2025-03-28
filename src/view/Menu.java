package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class Menu {
	private final static String abreNegrito = "\033[1m";
	private final static String fechaNegrito = "\033[0m";
	private final static String italico = "\u001B[3m";
	private final static String fechaItalico = "\u001B[0m";
	
	public static void menuCadastro() {
		System.out.println("\nDeseja:");
		System.out.println(" [1] Cadastrar um novo pet");
		System.out.println(" [2] Alterar os dados do pet cadastrado");
		System.out.println(" [3] Deletar um pet cadastrado");
		System.out.println(" [4] Listar todos os pets cadastrados");
		System.out.println(" [5] Listar pets por algum critério (idade, nome, raça)");
		System.out.println(" [6] Sair");
	}

	public static void menuBuscaPets() {
		System.out.println("\nBuscar por:");
		System.out.println("[1] Nome");
		System.out.println("[2] Sexo");
		System.out.println("[3] Idade");
		System.out.println("[4] Peso");
		System.out.println("[5] Raca");
		System.out.println("[6] Endereco");
		System.out.println("[7] Nome e Idade");
		System.out.println("[8] Idade e Peso");
		System.out.println("[9] Nome e Peso");
		System.out.println("[10] Raca e Peso");
		System.out.println("[11] Sexo e Raca");
	}
	
	public static List<String> lerFormulario(String caminhoFormulario){
		List<String> perguntas = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(caminhoFormulario))){
			String line = br.readLine();
			
			while(line != null) {
				perguntas.add(line);
				line = br.readLine();
			}
		}
		catch(IOException e) {
			System.out.println(italico + "Erro na leitura do Formulario.\n" + fechaItalico);
			return null;
		}
		
		return perguntas;
	}
	
	public static boolean validaRespostas(String resposta, Integer indice) throws RuntimeException {
			switch (indice) {
			case 0:
				if(resposta.isEmpty()) throw new RuntimeException(italico + "\nValor invalido: nome não pode estar vazio\n" + fechaItalico);
				else if(temCaracteresEspeciais(resposta)) throw new RuntimeException(italico + "\nValor invalido: o nome deve conter SOMENTE letras.\n" + fechaItalico);
	
				break;	
			case 4:
				Double idade = Double.parseDouble(resposta);
				if(idade > 20) throw new RuntimeException(italico + "\nValor invalido: idade deve ser ate 20 anos.\n" + fechaItalico);	
					
				break;
			case 5:
				double peso = Double.parseDouble(resposta);
				if(peso > 60 || peso < 0.50) throw new RuntimeException(italico + "\nValor invalido: peso deve estar entre 0.5Kg e 60Kg\n" + fechaItalico);	
				
				break;	
			case 6:
				String raca = resposta;
				if(temCaracteresEspeciais(raca)) throw new RuntimeException(italico + "\nValor invalido: digite apenas letras\n" + fechaItalico);					
			}
	
		return true;
	}
	
	public static void inicio() {
		String barra = "=";
		String espaco = " ";
		int numBarras = 100;
		int numEspacos = 37;
		for(int i = 0; i < numBarras; i++) System.out.print(barra);
		System.out.print(abreNegrito + "\n|" + fechaNegrito);
		for(int i = 0; i < numEspacos; i++) System.out.print(espaco);
		System.out.print(abreNegrito + "SISTEMA DE ADOÇÃO DE PETS" + fechaNegrito);
		for(int i = 0; i < numEspacos-1; i++) System.out.print(espaco);
		System.out.print(abreNegrito + "|\n" + fechaNegrito);
		for(int i = 0; i < numBarras; i++) System.out.print(abreNegrito + barra + fechaNegrito);
		System.out.println("\n");
		System.out.println(abreNegrito + "Bem vindo ao Sistema de cadastro para a Adoção de Pets!" + fechaNegrito);
	}
	
	private static boolean temCaracteresEspeciais(String s) {
		String[] nomes = s.split(" ");
		
		for(String n : nomes) {
	    	int contador = 0;
	    	
	    	while(contador < n.length()) {
	    		char caracter = n.charAt(contador);
	    		if(!Character.isLetter(caracter)) return true;
	    		contador++;
	    	}
		}
		
		return false;
	}
}

package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class Menu {

	public static void menuCadastro() {
		System.out.println("Deseja:");
		System.out.println(" [1] Cadastrar um novo pet");
		System.out.println(" [2] Alterar os dados do pet cadastrado");
		System.out.println(" [3] Deletar um pet cadastrado");
		System.out.println(" [4] Listar todos os pets cadastrados");
		System.out.println(" [5] Listar pets por algum critério (idade, nome, raça)");
		System.out.println(" [6] Sair");
	}

	public static void menuBuscaPets() {
		System.out.println("Buscar por:");
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

		System.out.println("> ");
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
			System.out.println("Erro na leitura do Formulario.");
			return null;
		}
		
		return perguntas;
	}
	
	public static boolean validaRespostas(String resposta, Integer indice) throws RuntimeException {
			switch (indice) {
			case 0:
				if(resposta.isEmpty()) throw new RuntimeException("Valor invalido: nome não pode estar vazio");
				else if(temCaracteresEspeciais(resposta)) throw new RuntimeException("Valor invalido: o nome deve conter SOMENTE letras.");
	
				break;	
			case 4:
				Double idade = Double.parseDouble(resposta);
				if(idade > 20) throw new RuntimeException("Valor invalido: idade deve ser ate 20 anos.");	
					
				break;
			case 5:
				double peso = Double.parseDouble(resposta);
				if(peso > 60 || peso < 0.50) throw new RuntimeException("Valor invalido: peso deve estar entre 0.5Kg e 60Kg");	
				
				break;	
			case 6:
				String raca = resposta;
				if(temCaracteresEspeciais(raca)) return false;
				
				break;				
			}
	
		return true;
	}
	
	public static void inicio() {
		String barra = "=";
		String espaco = " ";
		int numBarras = 132;
		int numEspacos = 53;
		for(int i = 0; i < numBarras; i++) System.out.print(barra);
		System.out.print("\n|");
		for(int i = 0; i < numEspacos; i++) System.out.print(espaco);
		System.out.print("SISTEMA DE ADOÇÃO DE PETS");
		for(int i = 0; i < numEspacos-1; i++) System.out.print(espaco);
		System.out.print("|\n");
		for(int i = 0; i < numBarras; i++) System.out.print(barra);
		System.out.println("\n");
		System.out.println("Bem vindo ao Sistema de cadastro para a Adoção de Pets!\n");
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

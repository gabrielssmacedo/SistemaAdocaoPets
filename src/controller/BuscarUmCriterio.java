package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import controller.interfaces.Busca;
import model.Endereco;
import model.Pet;
import model.enums.Criterio;
import model.enums.SexoPet;
import model.enums.TipoPet;

public class BuscarUmCriterio implements Busca{
	
	private String informacao;
	private Criterio criterio;

	@Override
	public List<Pet> buscarPet(String diretorio) {
		File diretorioCadastro = new File(diretorio);
		File[] arqPetsCadastrados = diretorioCadastro.listFiles();
		List<String> infoPets = new ArrayList<>();
		List<Pet> listaPets = new ArrayList<>();
		
		for(File caminho : arqPetsCadastrados) {
			try(BufferedReader br = new BufferedReader(new FileReader(caminho))){
				String line = br.readLine();
				
				while(line != null) {
					infoPets.add(line.substring(4, line.length()));
					line = br.readLine();
				}
				
				Pet pet = converterPet(infoPets);
				listaPets.add(pet);
			}
			catch(IOException e) {
				System.out.println("Erro na leitura do arquivo.");
			}
		}
		
		switch(criterio) {
		case NOME:
			return listaPets.stream().filter(x -> x.getNome() == informacao).collect(Collectors.toList());
		case SEXO:
			return listaPets.stream().filter(x -> x.getSexo() == SexoPet.valueOf(informacao.toUpperCase())).collect(Collectors.toList());
		case IDADE:
			return listaPets.stream().filter(x -> x.getIdade() == Double.parseDouble(informacao)).collect(Collectors.toList());
		
		case PESO:
			return listaPets.stream().filter(x -> x.getPeso() == Double.parseDouble(informacao)).collect(Collectors.toList());
			
		case RACA:
			return listaPets.stream().filter(x -> x.getRaca() == informacao).collect(Collectors.toList());
			
		case ENDERECO:
			String[] endereco = informacao.split(",");
			String rua = endereco[0].trim();
			String cidade = endereco[1].trim();
			return listaPets.stream().
					filter(x -> (x.getEndereco().getCidade() == cidade && x.getEndereco().getRua() == rua) || x.getEndereco().getCidade().contains(informacao))
					.collect(Collectors.toList());
		default:
			return null;
		}
	}
	
	private Pet converterPet(List<String> infoPets) {
		String[] infoEndereco = infoPets.get(3).split(", ");
		for(String s: infoEndereco) {
			System.out.println(s);
		}
		Endereco endereco = new Endereco(Integer.parseInt(infoEndereco[1]), infoEndereco[2], infoEndereco[0]);
		String[] idade = infoPets.get(4).split(" anos");
		String[] peso = infoPets.get(5).split("kg");
		
		Pet pet = new Pet(
				infoPets.get(0)
				, TipoPet.valueOf(infoPets.get(1).toUpperCase())
				, SexoPet.valueOf(infoPets.get(2).toUpperCase())
				, endereco
				, Double.parseDouble(idade[0])
				, Double.parseDouble(peso[0])
				, infoPets.get(6)
				);
		
		return pet;
	}

}

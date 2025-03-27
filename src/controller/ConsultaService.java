package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.interfaces.Busca;
import model.Endereco;
import model.Pet;
import model.enums.SexoPet;
import model.enums.TipoPet;

public class ConsultaService {
	private String diretorio;
	private Busca tipoBusca;
	
	public ConsultaService(String diretorio) {
		this.diretorio = diretorio;
	}
	
	public ConsultaService(String diretorio, Busca tipoBusca) {
		this.diretorio = diretorio;
		this.tipoBusca = tipoBusca;
	}
	
	public List<Pet> consultar(String tipoPet) {
		List<Pet> listaPets = new ArrayList<>();
		Set<Pet> conjuntoPets = listarTodosPets().keySet();
		
		for(Pet pet : conjuntoPets) {
			listaPets.add(pet);
		}
		
		return tipoBusca.buscarPet(listaPets, tipoPet);
	}
	
	public Map<Pet, File> listarTodosPets(){
		File diretorioCadastro = new File(diretorio);
		File[] arqPetsCadastrados = diretorioCadastro.listFiles();
		List<Pet> listaPets = new ArrayList<>();
		Map<Pet, File> petsCadastrados = new LinkedHashMap<>();
		
		for(File caminho : arqPetsCadastrados) {
			try(BufferedReader br = new BufferedReader(new FileReader(caminho))){
				List<String> infoPets = new ArrayList<>();
				String line = br.readLine();
				
				while(line != null) {
					infoPets.add(line.substring(4, line.length()));
					line = br.readLine();
				}
				
				Pet pet = converterPet(infoPets);
				
				petsCadastrados.put(pet, caminho);
				listaPets.add(pet);
			}
			catch(IOException e) {
				System.out.println("\u001B[3m" + "Erro na leitura do arquivo." + "\u001B[0m");
				return null;
			}
		}
		
		
		return petsCadastrados;
	}
	
	
	private Pet converterPet(List<String> infoPets) {
		Endereco endereco;
		String[] infoEndereco = infoPets.get(3).split(", ");
	
		if(infoEndereco[1].equalsIgnoreCase("Nao Informado")) {
			endereco = new Endereco(infoEndereco[2], infoEndereco[0]);
		}
		else endereco = new Endereco(Integer.parseInt(infoEndereco[1]), infoEndereco[2], infoEndereco[0]);
		String[] idade = infoPets.get(4).split(" anos");
		String[] peso = infoPets.get(5).split("kg");
		
		if(!idade[0].toUpperCase().equalsIgnoreCase("NAO INFORMADO")) idade[0] = idade[0].replace(',', '.');
		if(!peso[0].toUpperCase().equalsIgnoreCase("NAO INFORMADO")) peso[0] = peso[0].replace(',', '.');
		
		Pet pet = new Pet(
				infoPets.get(0)
				, TipoPet.valueOf(infoPets.get(1).toUpperCase())
				, SexoPet.valueOf(infoPets.get(2).toUpperCase())
				, endereco
				, idade[0].toUpperCase().equalsIgnoreCase("NAO INFORMADO")? null : Double.parseDouble(idade[0])
				, peso[0].toUpperCase().equalsIgnoreCase("NAO INFORMADO")? null : Double.parseDouble(peso[0])
				, infoPets.get(6)
				);
		
		return pet;
	}
}

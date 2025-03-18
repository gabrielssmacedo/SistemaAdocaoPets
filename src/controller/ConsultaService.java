package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.interfaces.Busca;
import model.Endereco;
import model.Pet;
import model.enums.SexoPet;
import model.enums.TipoPet;

public class ConsultaService {
	private String diretorio;
	private Busca tipoBusca;
	
	public ConsultaService(String diretorio, Busca tipoBusca) {
		this.diretorio = diretorio;
		this.tipoBusca = tipoBusca;
	}
	
	public List<Pet> consultar() {
		List<Pet> listaPets = listarTodosPets();
		
		return tipoBusca.buscarPet(listaPets);
	}
	
	public List<Pet> listarTodosPets(){
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
				return null;
			}
		}
		
		return listaPets;
	}
	
	private Pet converterPet(List<String> infoPets) {
		String[] infoEndereco = infoPets.get(3).split(", ");
		
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

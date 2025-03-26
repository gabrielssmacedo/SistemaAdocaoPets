package controller;

import java.util.List;
import java.util.stream.Collectors;

import controller.interfaces.Busca;
import model.Pet;
import model.enums.Criterio;
import model.enums.SexoPet;

public class BuscarDoisCriterios implements Busca{
	
	private String informacaoPet1;
	private String informacaoPet2;
	private Criterio criterio;
	
	public BuscarDoisCriterios(String informacaoPet1, String informacaoPet2, Criterio criterio) {
		this.informacaoPet1 = informacaoPet1;
		this.informacaoPet2 = informacaoPet2;
		this.criterio = criterio;
	}
	
	@Override
	public List<Pet> buscarPet(List<Pet> listaPets, String tipoPet) {
		informacaoPet1 = informacaoPet1.toUpperCase().trim();
		informacaoPet2 = informacaoPet2.toUpperCase().trim();
		
		switch(criterio) {
		case NOME_IDADE:
			return listaPets.stream().
					filter(pet -> pet.getNome().toUpperCase().contains(informacaoPet1) && pet.getIdade() == Double.parseDouble(informacaoPet2))
					.collect(Collectors.toList());
		case IDADE_PESO:
			return listaPets.stream()
					.filter(pet -> pet.getIdade() == Double.parseDouble(informacaoPet1) && pet.getPeso() == Double.parseDouble(informacaoPet2))
					.collect(Collectors.toList());
		case NOME_PESO:
			return listaPets.stream()
					.filter(pet -> pet.getNome().toUpperCase().contains(informacaoPet1) && pet.getPeso() == Double.parseDouble(informacaoPet2))
					.collect(Collectors.toList());
		case RACA_PESO:
			return listaPets.stream()
					.filter(pet -> pet.getRaca().toUpperCase().contains(informacaoPet1) && pet.getPeso() == Double.parseDouble(informacaoPet2))
					.collect(Collectors.toList());
		case SEXO_RACA:
			return listaPets.stream()
					.filter(pet -> pet.getSexo() == SexoPet.valueOf(informacaoPet1) && pet.getRaca().toUpperCase().contains(informacaoPet2))
					.collect(Collectors.toList());
		default:
			return null;
		}
	}

}

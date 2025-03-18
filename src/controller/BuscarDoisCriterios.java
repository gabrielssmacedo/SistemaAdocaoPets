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

	@Override
	public List<Pet> buscarPet(List<Pet> listaPets) {
		informacaoPet1 = informacaoPet1.toUpperCase();
		informacaoPet2 = informacaoPet2.toUpperCase();
		
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
					.filter(pet -> pet.getRaca().toUpperCase() == informacaoPet1 && pet.getPeso() == Double.parseDouble(informacaoPet2))
					.collect(Collectors.toList());
		case SEXO_RACA:
			return listaPets.stream()
					.filter(pet -> pet.getSexo() == SexoPet.valueOf(informacaoPet1) && pet.getRaca().toUpperCase() == informacaoPet2)
					.collect(Collectors.toList());
		default:
			return null;
		}
	}

}

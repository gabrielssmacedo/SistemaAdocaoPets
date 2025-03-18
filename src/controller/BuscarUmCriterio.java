package controller;

import java.util.List;

import java.util.stream.Collectors;

import controller.interfaces.Busca;
import model.Pet;
import model.enums.Criterio;
import model.enums.SexoPet;

public class BuscarUmCriterio implements Busca{
	
	private String informacaoPet;
	private Criterio criterio;
	
	public BuscarUmCriterio(String informacaoPet, Criterio criterio) {
		this.informacaoPet = informacaoPet;
		this.criterio = criterio;
	}

	@Override
	public List<Pet> buscarPet(List<Pet> listaPets) {
		informacaoPet = informacaoPet.toUpperCase();
		
		switch(criterio) {
		case NOME:
			return listaPets.stream().filter(x -> x.getNome().toUpperCase().contains(informacaoPet)).collect(Collectors.toList());
		case SEXO:
			return listaPets.stream().filter(x -> x.getSexo() == SexoPet.valueOf(informacaoPet)).collect(Collectors.toList());
		case IDADE:
			return listaPets.stream().filter(x -> x.getIdade() == Double.parseDouble(informacaoPet)).collect(Collectors.toList());
		case PESO:
			return listaPets.stream().filter(x -> x.getPeso() == Double.parseDouble(informacaoPet)).collect(Collectors.toList());
		case RACA:
			return listaPets.stream().filter(x -> x.getRaca().toUpperCase() == informacaoPet).collect(Collectors.toList());
		case ENDERECO:
			String[] endereco = informacaoPet.split(",");
			String rua = endereco[0].trim().toUpperCase();
			String cidade = endereco[1].trim().toUpperCase();
			return listaPets.stream()
					.filter(x -> (x.getEndereco().getCidade().toUpperCase() == cidade && x.getEndereco().getRua().toUpperCase() == rua) 
							|| x.getEndereco().getCidade().toUpperCase().contains(informacaoPet))
					.collect(Collectors.toList());
		default:
			return null;
		}
	}
	
}

package controller;

import java.util.List;
import java.util.stream.Collectors;

import controller.interfaces.Busca;
import model.Pet;
import model.enums.Criterio;
import model.enums.SexoPet;
import model.enums.TipoPet;

public class BuscarUmCriterio implements Busca{
	
	private String informacaoPet;
	private Criterio criterio;
	
	public BuscarUmCriterio(String informacaoPet, Criterio criterio) {
		this.informacaoPet = informacaoPet;
		this.criterio = criterio;
	}

	@Override
	public List<Pet> buscarPet(List<Pet> listaPets, String tipoPet) {
		informacaoPet = informacaoPet.toUpperCase().trim();
		
		switch(criterio) {
		case NOME:
			if(tipoPet.equalsIgnoreCase(TipoPet.CACHORRO.toString()))
				return listaPets.stream().filter(x -> x.getTipo() == TipoPet.CACHORRO && x.getNome().toUpperCase().contains(informacaoPet)).collect(Collectors.toList());				
			else
				return listaPets.stream().filter(x -> x.getTipo() == TipoPet.GATO && x.getNome().toUpperCase().contains(informacaoPet)).collect(Collectors.toList());				
		case SEXO:
			if(tipoPet.equalsIgnoreCase(TipoPet.CACHORRO.toString()))
				return listaPets.stream().filter(x -> x.getTipo() == TipoPet.CACHORRO && x.getSexo() == SexoPet.valueOf(informacaoPet)).collect(Collectors.toList());
			else
				return listaPets.stream().filter(x -> x.getTipo() == TipoPet.GATO && x.getSexo() == SexoPet.valueOf(informacaoPet)).collect(Collectors.toList());
		case IDADE:
			if(tipoPet.equalsIgnoreCase(TipoPet.CACHORRO.toString()))
				return listaPets.stream().filter(x -> x.getTipo() == TipoPet.CACHORRO && x.getIdade() == Double.parseDouble(informacaoPet)).collect(Collectors.toList());
			else
				return listaPets.stream().filter(x -> x.getTipo() == TipoPet.GATO && x.getIdade() == Double.parseDouble(informacaoPet)).collect(Collectors.toList());
		case PESO:
			if(tipoPet.equalsIgnoreCase(TipoPet.CACHORRO.toString()))
				return listaPets.stream().filter(x -> x.getTipo() == TipoPet.CACHORRO && x.getPeso() == Double.parseDouble(informacaoPet)).collect(Collectors.toList());
			else
				return listaPets.stream().filter(x -> x.getTipo() == TipoPet.GATO && x.getPeso() == Double.parseDouble(informacaoPet)).collect(Collectors.toList());
		case RACA:
			if(tipoPet.equalsIgnoreCase(TipoPet.CACHORRO.toString()))
				return listaPets.stream().filter(x -> x.getTipo() == TipoPet.CACHORRO && x.getRaca().toUpperCase().contains(informacaoPet)).collect(Collectors.toList());
			else
				return listaPets.stream().filter(x -> x.getTipo() == TipoPet.GATO && x.getRaca().toUpperCase().contains(informacaoPet)).collect(Collectors.toList());
		case ENDERECO: //CONTINUAR AQUI. ESTAVA VENDO ONDE IA COLOCAR SE É CACHORRO OU GATO NOS FILTERS
			String[] endereco = informacaoPet.split(",");
			String rua = endereco[0].trim().toUpperCase();
			String cidade = endereco[1].trim().toUpperCase();
			
			if(tipoPet.equalsIgnoreCase(TipoPet.CACHORRO.toString()))
				return listaPets.stream()
						.filter(x -> x.getTipo() == TipoPet.CACHORRO && ((x.getEndereco().getCidade().toUpperCase() == cidade && x.getEndereco().getRua().toUpperCase().contains(rua)) 
								|| x.getEndereco().getCidade().toUpperCase().contains(informacaoPet)))
						.collect(Collectors.toList());
			else
				return listaPets.stream()
						.filter(x -> x.getTipo() == TipoPet.GATO && ((x.getEndereco().getCidade().toUpperCase() == cidade && x.getEndereco().getRua().toUpperCase().contains(rua)) 
								|| x.getEndereco().getCidade().toUpperCase().contains(informacaoPet)))
						.collect(Collectors.toList());
		default:
			return null;
		}
	}
	
}

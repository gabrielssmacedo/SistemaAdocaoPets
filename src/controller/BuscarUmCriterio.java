package controller;

import java.util.List;

import java.util.stream.Collectors;

import controller.interfaces.Busca;
import model.Pet;
import model.enums.Criterio;
import model.enums.SexoPet;

public class BuscarUmCriterio implements Busca{
	
	private String informacao;
	private Criterio criterio;
	
	public BuscarUmCriterio(String informacao, Criterio criterio) {
		this.informacao = informacao;
		this.criterio = criterio;
	}

	@Override
	public List<Pet> buscarPet(List<Pet> listaPets) {
		
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
	
}

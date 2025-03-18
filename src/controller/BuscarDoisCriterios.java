package controller;

import controller.interfaces.Busca;
import model.Pet;

public class BuscarDoisCriterios<T> implements Busca{
	
	private T criterioUm;
	private T criterioDois;

	@Override
	public List<Pet> buscarPet(String diretorio) {
		// TODO Auto-generated method stub
		return null;
	}

}

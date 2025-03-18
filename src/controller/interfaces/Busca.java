package controller.interfaces;

import java.util.List;

import model.Pet;

public interface Busca {
	List<Pet> buscarPet(List<Pet> listaPets);
}

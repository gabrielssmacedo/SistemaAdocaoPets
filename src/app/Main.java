package app;

import java.util.Locale;

import model.Endereco;
import model.Pet;
import model.enums.SexoPet;
import model.enums.TipoPet;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		String path = "C:\\Users\\gabri\\OneDrive\\Área de Trabalho\\SistemaAdocaoPets\\arquivosCadastro";
		
		Pet pet = new Pet("Gabriel Santos Macedo");
		TipoPet tipo = TipoPet.valueOf("GATO");
		SexoPet sexo = SexoPet.MACHO;
		pet.setTipo(tipo);
		pet.setSexo(sexo);
		pet.setEndereco(new Endereco(456 ,"Rua 2", "São Paulo"));
		pet.setIdade(6.0);
		pet.setPeso(56.0);
		pet.setRaca("Humano");
		
		if(pet.cadastrar(path)) System.out.println("Cadastrado com Sucesso.");
		else System.out.println("Não foi possivel efetuar o cadastro");
	}

}

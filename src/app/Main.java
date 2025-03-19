package app;

import java.util.Scanner;

import view.Menu;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Menu menu = new Menu();
		
		menu.inicio();
		
		menu.menuCadastro();
		
		
		
		
		sc.close();
	}

}

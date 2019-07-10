package br.edu.ifms.taskmanager.teste;

import com.google.gson.Gson;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Pessoa {
	private String nome;
	private Integer idade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", idade=" + idade + "]";
	}

}

public class Teste {
	public static void paraObjeto() throws IOException {
		File file = new File("src/br/edu/ifms/taskmanager/teste/Pessoa.json");
		Scanner scanner = new Scanner(file);
		
		String string = new String();
		while(scanner.hasNext()) {
			string += scanner.nextLine();
		}
		
		Gson gson = new Gson();
		Pessoa pessoa = gson.fromJson(string, Pessoa.class);
		System.out.println(pessoa.toString());
		
		scanner.close();
	}
	
	public static void paraJSON() throws IOException {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Matheus Gomes");
		pessoa.setIdade(17);

		Gson gson = new Gson();
		String string = gson.toJson(pessoa);
		System.out.println(string);
		
		FileWriter fileWriter = new FileWriter("src/br/edu/ifms/taskmanager/teste/Pessoa.json");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.printf("%s", string);
		
		fileWriter.close();
	}
	public static void main(String[] args) {
		try {
			paraJSON();
			
		} catch(IOException e) {}
		
		try {
			paraObjeto();
		
		} catch(IOException e) {}
	}
}

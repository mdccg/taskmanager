package br.edu.ifms.taskmanager.mockBD;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import br.edu.ifms.taskmanager.model.Categoria;
import br.edu.ifms.taskmanager.model.Tarefa;
import br.edu.ifms.taskmanager.model.Usuario;

public class Banco {
	private ArrayList<Categoria> categorias;
	private ArrayList<Tarefa> tarefas;
	private ArrayList<Usuario> usuarios;

	public Banco() throws FileNotFoundException {
		this.categorias = new ArrayList<>();
		this.tarefas = new ArrayList<>();
		this.usuarios = new ArrayList<>();
		
		File fileCategorias = new File("src/br/edu/ifms/taskmanager/mockBD/Categorias.json");
		File fileTarefas = new File("src/br/edu/ifms/taskmanager/mockBD/Tarefas.json");
		File fileUsuarios = new File("src/br/edu/ifms/taskmanager/mockBD/Usuarios.json");

		Gson gson = new Gson();
		Scanner scanner = new Scanner(fileCategorias);

		while (scanner.hasNext()) {
			String string = scanner.nextLine();
			Categoria categoria = gson.fromJson(string, Categoria.class);
			this.categorias.add(categoria);
		}

		scanner.close();
		scanner = new Scanner(fileTarefas);

		while (scanner.hasNext()) {
			String string = scanner.nextLine();
			Tarefa tarefa = gson.fromJson(string, Tarefa.class);
			this.tarefas.add(tarefa);
		}

		scanner.close();
		scanner = new Scanner(fileUsuarios);

		while (scanner.hasNext()) {
			String string = scanner.nextLine();
			Usuario usuario = gson.fromJson(string, Usuario.class);
			this.usuarios.add(usuario);
		}

		scanner.close();

		if(this.categorias == null)
			this.categorias = new ArrayList<>();
		if(this.tarefas == null)
			this.tarefas = new ArrayList<>();
		if(this.usuarios == null)
			this.usuarios = new ArrayList<>();	
	}

	public Banco(ArrayList<Categoria> categorias, ArrayList<Tarefa> tarefas, ArrayList<Usuario> usuarios) {
		super();
		this.categorias = categorias;
		this.tarefas = tarefas;
		this.usuarios = usuarios;
	}

	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ArrayList<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(ArrayList<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}

package br.edu.ifms.taskmanager.mockBD;

import br.edu.ifms.taskmanager.model.*;

import java.util.ArrayList;

public class Banco {
	private ArrayList<Categoria> categorias;
	private ArrayList<Tarefa> tarefas;
	private ArrayList<Usuario> usuarios;

	public Banco() {
		this.categorias = new ArrayList<>();
		this.tarefas = new ArrayList<>();
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

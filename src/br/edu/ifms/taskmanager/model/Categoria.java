package br.edu.ifms.taskmanager.model;

import br.edu.ifms.taskmanager.model.Tarefa;

import java.util.ArrayList;

public class Categoria {
	private Long id;
	private String titulo;
	private ArrayList<Tarefa> tarefas;

	public Categoria() {
		this.tarefas = new ArrayList<>();
	}

	public Categoria(Long id, String titulo, ArrayList<Tarefa> tarefas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.tarefas = tarefas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(ArrayList<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

}

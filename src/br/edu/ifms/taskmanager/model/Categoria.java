package br.edu.ifms.taskmanager.model;

import java.util.ArrayList;

public class Categoria {
	private Long id;
	private String titulo;
	private ArrayList<Long> id_tarefas;

	public Categoria() {
		this.id_tarefas = new ArrayList<>();
	}

	public Categoria(Long id, String titulo, ArrayList<Long> id_tarefas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.id_tarefas = id_tarefas;
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

	public ArrayList<Long> getId_Tarefas() {
		return id_tarefas;
	}

	public void setId_Tarefas(ArrayList<Long> id_tarefas) {
		this.id_tarefas = id_tarefas;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", titulo=" + titulo + ", id_tarefas=" + id_tarefas + "]";
	}

}

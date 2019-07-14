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
		return "ID: " + id + "\n" + "Título: " + titulo + "\n" + "ID da(s) tarefa(s): " + id_tarefas + "\n" + "\n";

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_tarefas == null) ? 0 : id_tarefas.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_tarefas == null) {
			if (other.id_tarefas != null)
				return false;
		} else if (!id_tarefas.equals(other.id_tarefas))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

}

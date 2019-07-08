package br.edu.ifms.taskmanager.model;

import br.edu.ifms.taskmanager.model.Tarefa;

import java.util.ArrayList;

public class Usuario {
	private Long id;
	private String email;
	private String nome;
	private String senha;
	private ArrayList<Tarefa> tarefas;

	public Usuario() {
		this.tarefas = new ArrayList<>();
	}

	public Usuario(Long id, String email, String nome, String senha, ArrayList<Tarefa> tarefas) {
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.tarefas = tarefas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(ArrayList<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

}

package br.edu.ifms.taskmanager.dao;

import java.util.ArrayList;

import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Tarefa;

public class TarefaDAO {
	Banco banco;

	public TarefaDAO(Banco banco) {
		super();
		this.banco = banco;
	}

	public boolean adicionaTarefa(Tarefa tarefa) {
		ArrayList<Tarefa> tarefas = banco.getTarefas();
		
		if(this.buscaTarefaPorTitulo(tarefa.getTitulo()) != null)
			return false;
		
		return tarefas.add(tarefa);
	}

	public Tarefa buscaTarefaPorId(Long id) {
		ArrayList<Tarefa> tarefas = banco.getTarefas();

		for (Tarefa tarefa : tarefas)
			if (tarefa.getId().equals(id))
				return tarefa;

		return null;
	}

	public boolean atualizaTarefa(Tarefa tarefa) {
		if(tarefa.getTitulo().equals("") ||
				tarefa.getPrazo().equals(null) ||
				tarefa.getPrioridade().equals(""))
			return false;
		
		ArrayList<Tarefa> tarefas = banco.getTarefas();

		for (Tarefa tarefaBD : tarefas) {
			if (tarefaBD.getId().equals(tarefa.getId())) {
				tarefaBD.setTitulo(tarefa.getTitulo());
				tarefaBD.setPrazo(tarefa.getPrazo());
				tarefaBD.setPrioridade(tarefa.getPrioridade());
				tarefaBD.setDataEdicao(tarefa.getDataEdicao());
				tarefaBD.setId_Categoria(tarefa.getId_Categoria());
				return true;
			}
		}

		return false;
	}

	public boolean deletaTarefa(Tarefa tarefa) {
		ArrayList<Tarefa> tarefas = banco.getTarefas();

		return tarefas.remove(tarefa);
	}

	public Tarefa buscaTarefaPorTitulo(String titulo) {
		ArrayList<Tarefa> tarefas = banco.getTarefas();

		for(Tarefa tarefa : tarefas)
			if(tarefa.getTitulo().equals(titulo))
				return tarefa;
		
		return null;
	}
	
	public String listaTarefas() {
		ArrayList<Tarefa> tarefas = banco.getTarefas();
		String string = new String();

		for (Tarefa tarefa : tarefas)
			string += tarefa.toString();

		return string;
	}
}

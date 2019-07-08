package br.edu.ifms.taskmanager.dao;

import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Tarefa;

import java.util.ArrayList;

public class TarefaDAO {
	Banco banco;

	public TarefaDAO(Banco banco) {
		super();
		this.banco = banco;
	}

	public boolean adicionaTarefa(Tarefa tarefa) {
		ArrayList<Tarefa> tarefas = banco.getTarefas();

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
		ArrayList<Tarefa> tarefas = banco.getTarefas();

		for (Tarefa tarefaBD : tarefas) {
			if (tarefaBD.getId().equals(tarefa.getId())) {
				tarefas.remove(tarefaBD);
				tarefas.add(tarefa);
				return true;
			}
		}

		return false;
	}

	public boolean deletaTarefa(Tarefa tarefa) {
		ArrayList<Tarefa> tarefas = banco.getTarefas();

		return tarefas.remove(tarefa);
	}

}

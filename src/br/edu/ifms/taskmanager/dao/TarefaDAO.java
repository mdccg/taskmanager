package br.edu.ifms.taskmanager.dao;

import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Categoria;
import br.edu.ifms.taskmanager.model.Tarefa;
import br.edu.ifms.taskmanager.dao.CategoriaDAO;

public class TarefaDAO {
	Banco banco;

	public TarefaDAO(Banco banco) {
		super();
		this.banco = banco;
	}

	public boolean adicionaTarefa(Tarefa tarefa) {
		boolean repetida = this.buscaTarefaPorTitulo(tarefa.getTitulo()) != null;
		return tarefa.getTitulo().equals("") || tarefa.getPrioridade().equals("") || repetida ? false
				: this.banco.getTarefas().add(tarefa);
	}

	public String listaTarefas() {
		String string = new String();

		for (Tarefa tarefa : this.banco.getTarefas())
			string += tarefa.toString();

		return string;
	}

	public Tarefa buscaTarefaPorId(Long id) {
		for (Tarefa tarefa : this.banco.getTarefas())
			if (tarefa.getId().equals(id))
				return tarefa;

		return null;
	}

	public Tarefa buscaTarefaPorTitulo(String titulo) {
		for (Tarefa tarefa : this.banco.getTarefas())
			if (tarefa.getTitulo().equals(titulo))
				return tarefa;

		return null;
	}

	public boolean atualizaTarefa(Tarefa atualizada) {
		if (atualizada.getTitulo().equals("") || atualizada.getPrioridade().equals(""))
			return false;

		for (Tarefa salva : this.banco.getTarefas()) {
			if (salva.getId().equals(atualizada.getId())) {

				salva.setTitulo(atualizada.getTitulo());
				salva.setPrazo(atualizada.getPrazo());
				salva.setPrioridade(atualizada.getPrioridade());
				salva.setDataEdicao(atualizada.getDataEdicao());
				salva.setId_Categoria(atualizada.getId_Categoria());

				return true;
			}
		}

		return false;
	}

	public boolean deletaTarefa(Tarefa tarefa) {
		if(this.banco.getTarefas().remove(tarefa)) {
			if(tarefa.getId_Categoria() != -1l) {
				Categoria categoria = new CategoriaDAO(banco).buscaCategoriaPorId(tarefa.getId_Categoria());
				if(categoria != null)
					categoria.getId_Tarefas().remove(tarefa.getId());
				return true;
			}
		}
		return false;
	}
}

package br.edu.ifms.taskmanager.dao;

import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Categoria;
import br.edu.ifms.taskmanager.model.Tarefa;
import br.edu.ifms.taskmanager.dao.TarefaDAO;

public class CategoriaDAO {
	Banco banco;

	public CategoriaDAO(Banco banco) {
		super();
		this.banco = banco;
	}

	public boolean adicionaCategoria(Categoria categoria) {
		boolean repetida = this.buscaCategoriaPorTitulo(categoria.getTitulo()) != null;
		return categoria.getTitulo().equals("") || repetida ? false : this.banco.getCategorias().add(categoria);
	}

	public String listaCategorias() {
		String string = new String();

		for (Categoria categoria : this.banco.getCategorias())
			string += categoria.toString();

		return string;
	}

	public Categoria buscaCategoriaPorId(Long id) {
		for (Categoria categoria : this.banco.getCategorias())
			if (categoria.getId().equals(id))
				return categoria;

		return null;
	}

	public Categoria buscaCategoriaPorTitulo(String titulo) {
		for (Categoria categoria : this.banco.getCategorias())
			if (categoria.getTitulo().equals(titulo))
				return categoria;

		return null;
	}

	public boolean atualizaCategoria(Categoria atualizada) {
		if (atualizada.getTitulo().equals(""))
			return false;

		for (Categoria salva : this.banco.getCategorias()) {
			if (salva.getId().equals(atualizada.getId())) {

				salva.setTitulo(atualizada.getTitulo());
				salva.setId_Tarefas(atualizada.getId_Tarefas());

				return true;
			}
		}

		return false;
	}

	public boolean deletaCategoria(Categoria categoria) {
		if(this.banco.getCategorias().remove(categoria)) {
			for(Long id_tarefa : categoria.getId_Tarefas()) {
				new TarefaDAO(banco).buscaTarefaPorId(id_tarefa).setId_Categoria(-1l);
			}
			return true;
		}
		return false;
	}
}

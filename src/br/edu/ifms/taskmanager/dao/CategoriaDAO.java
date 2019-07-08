package br.edu.ifms.taskmanager.dao;

import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Categoria;

import java.util.ArrayList;

public class CategoriaDAO {
	Banco banco;

	public CategoriaDAO(Banco banco) {
		super();
		this.banco = banco;
	}

	public boolean adicionaCategoria(Categoria categoria) {
		ArrayList<Categoria> categorias = banco.getCategorias();

		return categorias.add(categoria);
	}

	public Categoria buscaCategoriaPorId(Long id) {
		ArrayList<Categoria> categorias = banco.getCategorias();

		for (Categoria categoria : categorias)
			if (categoria.getId().equals(id))
				return categoria;

		return null;
	}

	public boolean atualizaCategoria(Categoria categoria) {
		ArrayList<Categoria> categorias = banco.getCategorias();

		for (Categoria categoriaBD : categorias) {
			if (categoriaBD.getId().equals(categoria.getId())) {
				categorias.remove(categoriaBD);
				categorias.add(categoria);
				return true;
			}
		}

		return false;
	}

	public boolean deletaCategoria(Categoria categoria) {
		ArrayList<Categoria> categorias = banco.getCategorias();

		return categorias.remove(categoria);
	}

}

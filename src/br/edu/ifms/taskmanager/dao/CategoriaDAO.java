package br.edu.ifms.taskmanager.dao;

import java.util.ArrayList;

import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Categoria;

public class CategoriaDAO {
	Banco banco;

	public CategoriaDAO(Banco banco) {
		super();
		this.banco = banco;
	}

	public boolean adicionaCategoria(Categoria categoria) {
		ArrayList<Categoria> categorias = banco.getCategorias();
		
		if(this.buscaCategoriaPorTitulo(categoria.getTitulo()) != null)
			return false;
		
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
		if(categoria.getTitulo().equals(""))
			return false;
		
		ArrayList<Categoria> categorias = banco.getCategorias();

		for (Categoria categoriaBD : categorias) {
			if (categoriaBD.getId().equals(categoria.getId())) {
				categoriaBD.setTitulo(categoria.getTitulo());
				categoriaBD.setId_Tarefas(categoria.getId_Tarefas());
				return true;
			}
		}

		return false;
	}

	public boolean deletaCategoria(Categoria categoria) {
		ArrayList<Categoria> categorias = banco.getCategorias();

		return categorias.remove(categoria);
	}

	public Categoria buscaCategoriaPorTitulo(String titulo) {
		ArrayList<Categoria> categorias = banco.getCategorias();
		
		for(Categoria categoria : categorias)
			if(categoria.getTitulo().equals(titulo))
				return categoria;
		
		return null;
	}
	
	public String listaCategorias() {
		ArrayList<Categoria> categorias = banco.getCategorias();
		String string = new String();

		for (Categoria categoria : categorias)
			string += categoria.toString();

		return string;
	}
	
}

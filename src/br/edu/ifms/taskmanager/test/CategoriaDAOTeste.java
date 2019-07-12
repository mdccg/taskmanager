package br.edu.ifms.taskmanager.test;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifms.taskmanager.dao.CategoriaDAO;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Categoria;

public class CategoriaDAOTeste {
	Banco banco;
	CategoriaDAO categoriaDAO;
	Categoria categoria;
	String listaCategorias;

	@Before
	public void setUp() throws FileNotFoundException {
		banco = new Banco();
		categoriaDAO = new CategoriaDAO(banco);
		categoria = new Categoria(1l, "Escola", new ArrayList<>());
		categoriaDAO.adicionaCategoria(categoria);
		listaCategorias = categoria.toString();

	}

	@Test
	public void testaAdicionarCategoria() {
		Assert.assertTrue(categoriaDAO.adicionaCategoria(categoria));
	}

	@Test
	public void testaDeletarCategoria() {
		Assert.assertTrue(categoriaDAO.deletaCategoria(categoria));
	}

	@Test
	public void testaListarCategorias() {
		Assert.assertEquals(listaCategorias, categoriaDAO.listaCategorias());
	}

	@Test
	public void testaBuscarCategoriaPorId() {
		Assert.assertEquals(categoria, categoriaDAO.buscaCategoriaPorId(1l));
	}

	@Test
	public void testaBuscarCategoriaNulaPorId() {
		Assert.assertEquals(null, categoriaDAO.buscaCategoriaPorId(-1l));
	}

	@Test
	public void testaAtualizarCategoria() {
		categoria.setTitulo("IFMS");
		Assert.assertTrue(categoriaDAO.atualizaCategoria(categoria));

		categoria.setTitulo(null);
		Assert.assertFalse(categoriaDAO.atualizaCategoria(categoria));
	}
}

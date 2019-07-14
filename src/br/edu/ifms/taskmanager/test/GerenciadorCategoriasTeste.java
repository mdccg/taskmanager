package br.edu.ifms.taskmanager.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifms.taskmanager.dao.CategoriaDAO;
import br.edu.ifms.taskmanager.manager.GerenciadorCategorias;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Categoria;

public class GerenciadorCategoriasTeste {
	Banco banco;
	Categoria categoria;
	CategoriaDAO categoriaDAO;
	GerenciadorCategorias gerenciadorCategorias;

	@Before
	public void setUp() throws FileNotFoundException {
		banco = new Banco();
		categoria = new Categoria(0l, "IFMS", new ArrayList<>());
		categoriaDAO = new CategoriaDAO(banco);
		gerenciadorCategorias = new GerenciadorCategorias(banco);

		categoriaDAO.adicionaCategoria(categoria);
	}
	
	@Test
	public void testaAdicionarCategoriaPeloGerenciador() {
		Assert.assertEquals("A categoria foi adicionada com êxito.", gerenciadorCategorias.adicionaCategoria("Acadêmico"));
	}
	
	@Test
	public void testaAdicionarCategoriaComTituloIgualPeloGerenciador() {
		Assert.assertEquals("A categoria não foi adicionada.", gerenciadorCategorias.adicionaCategoria("IFMS"));
	}
	
	@Test
	public void testaBuscarCategoriaPorIdPeloGerenciador() {
		Assert.assertEquals(categoria.toString(), gerenciadorCategorias.buscaCategoriaPorId(categoria.getId()));
	}
	
	@Test
	public void testaListarCategoriasPeloGerenciador() {
		Assert.assertEquals(categoria.toString(), gerenciadorCategorias.listaCategorias());
	}
	
	@Test
	public void testaAtualizarCategoriaComTituloIgualPeloGerenciador() {
		Assert.assertEquals("A categoria não foi atualizada.", gerenciadorCategorias.atualizaCategoria(categoria, "IFMS"));
	}
	
	@Test
	public void testaDeletarCategoriaPeloGerenciador() {
		Assert.assertEquals("A categoria foi deletada com êxito.", gerenciadorCategorias.deletaCategoria(categoria, "Y"));
	}
}

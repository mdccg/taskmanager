package br.edu.ifms.taskmanager.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifms.taskmanager.manager.GerenciadorCategorias;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Categoria;

public class GerenciadorCategoriasTeste {
	Banco banco;
	GerenciadorCategorias gerenciadorCategorias;

	@Before
	public void setUp() throws FileNotFoundException {
		banco = new Banco();
		gerenciadorCategorias = new GerenciadorCategorias(banco);
		
		gerenciadorCategorias.adicionaCategoria("IFMS");
	}
	
	@Test
	public void testaAdicionarCategoriaPeloGerenciador() {
		Assert.assertEquals("A categoria foi adicionada com êxito.", gerenciadorCategorias.adicionaCategoria("Acadêmico"));
	}
	
	@Test
	public void testaAdicionarCategoriaComMesmoNomePeloGerenciador() {
		Assert.assertEquals("A categoria não foi adicionada.", gerenciadorCategorias.adicionaCategoria("IFMS"));
	}
	
	@Test
	public void testaBuscarCategoriaPeloGerenciador() {
		Categoria categoria = new Categoria(0l, "IFMS", new ArrayList<>());
		Assert.assertEquals(categoria.toString(), gerenciadorCategorias.buscaCategoriaPorId(categoria.getId()));
	}
}

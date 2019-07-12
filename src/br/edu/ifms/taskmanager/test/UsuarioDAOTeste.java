package br.edu.ifms.taskmanager.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifms.taskmanager.dao.UsuarioDAO;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Usuario;

public class UsuarioDAOTeste {
	Banco banco;
	UsuarioDAO usuarioDAO;
	Usuario usuario;
	
	@Before
	public void setUp() throws FileNotFoundException {
		banco = new Banco();
		usuarioDAO = new UsuarioDAO(banco);
		usuario = new Usuario(1l, "matheus.gomes@estudante.ifms.edu.br", "Matheus Gomes", "1234567890", new ArrayList<>());
	}
	
	@Test
	public void testaAdicionarUsuario() {
		Assert.assertTrue(usuarioDAO.adicionaUsuario(usuario));
	}
	
	@Test
	public void testaAdicionarUsuarioComMesmoEnderecoEletronico() {
		usuario = new Usuario(1l, "matheus.gomes@estudante.ifms.edu.br", "Matheus Gomes", "1234567890", new ArrayList<>());
		usuarioDAO.adicionaUsuario(usuario);
		
		usuario = new Usuario(2l, "matheus.gomes@estudante.ifms.edu.br", "Matheus Comparotto", "0987654321", new ArrayList<>());
		Assert.assertFalse(usuarioDAO.adicionaUsuario(usuario));
	}

	@Test
	public void testaBuscarUsuarioPorId() {
		usuario = new Usuario(1l, "matheus.gomes@estudante.ifms.edu.br", "Matheus Gomes", "1234567890", new ArrayList<>());
		usuarioDAO.adicionaUsuario(usuario);
		Assert.assertEquals(usuario, usuarioDAO.buscaUsuarioPorId(1l));
	}
}

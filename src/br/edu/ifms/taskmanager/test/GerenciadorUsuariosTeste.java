package br.edu.ifms.taskmanager.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifms.taskmanager.dao.UsuarioDAO;
import br.edu.ifms.taskmanager.manager.GerenciadorUsuarios;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Usuario;

public class GerenciadorUsuariosTeste {
	Banco banco;
	Usuario usuario;
	UsuarioDAO usuarioDAO;
	GerenciadorUsuarios gerenciadorUsuarios;

	@Before
	public void setUp() throws FileNotFoundException {
		banco = new Banco();
		usuario = new Usuario(2147483648l, "fernando.fernandes@ifms.edu.br", "Fernando Fernandes", "fernando",
				new ArrayList<>());
		usuarioDAO = new UsuarioDAO(banco);
		gerenciadorUsuarios = new GerenciadorUsuarios(banco);
	}

	@Test
	public void testaAdicionarUsuarioPeloGerenciador() {
		Assert.assertEquals("O usuário foi adicionado com êxito.", gerenciadorUsuarios.adicionaUsuario(usuario));
	}

	@Test
	public void testaBuscarUsuarioPorIDPeloGerenciador() {
		usuarioDAO.adicionaUsuario(usuario);
		Assert.assertEquals(usuario.toString(), gerenciadorUsuarios.buscaUsuarioPorID(usuario.getId()));
	}

	@Test
	public void testaListarUsuariosPeloGerenciador() {
		banco.getUsuarios().clear();

		String string = new String();
		usuarioDAO.adicionaUsuario(usuario);
		string += usuario.toString();

		usuario = new Usuario(4294967296l, "negris.lukas@ifms.edu.br", "Negris Lukas", "0x539", new ArrayList<>());
		usuarioDAO.adicionaUsuario(usuario);
		string += usuario.toString();

		usuario = new Usuario(8589934592l, "caesar.claudius@colmeia.udesc.br", "Caesar Claudius", "ccs1664",
				new ArrayList<>());
		usuarioDAO.adicionaUsuario(usuario);
		string += usuario.toString();

		Assert.assertEquals(string, gerenciadorUsuarios.listaUsuarios());
	}

	@Test
	public void testaAtualizarUsuarioPeloGerenciador() {
		usuarioDAO.adicionaUsuario(usuario);

		String email = "fernando.fernandes@ifms.edu.br";
		String nome = "Fernando Fernandes";
		String senha = "fernandes";

		Assert.assertEquals("O usuário foi atualizado com êxito.",
				gerenciadorUsuarios.atualizaUsuario(usuario, email, nome, senha));
	}

	public void testaDeletarUsuarioPeloGerenciador() {
		usuarioDAO.adicionaUsuario(usuario);

		Assert.assertEquals("O usuário foi deletado com êxito.", gerenciadorUsuarios.deletaUsuario(usuario, "Y"));
	}
}

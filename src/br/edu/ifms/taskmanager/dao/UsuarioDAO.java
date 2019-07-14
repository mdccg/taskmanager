package br.edu.ifms.taskmanager.dao;

import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Usuario;

public class UsuarioDAO {
	Banco banco;

	public UsuarioDAO(Banco banco) {
		super();
		this.banco = banco;
	}

	public boolean adicionaUsuario(Usuario usuario) {
		boolean repetido = this.buscaUsuarioPorEmail(usuario.getEmail()) != null;
		return repetido ? false : this.banco.getUsuarios().add(usuario);
	}

	public String listaUsuarios() {
		String string = new String();

		for (Usuario usuario : this.banco.getUsuarios())
			string += usuario.toString();

		return string;
	}

	public Usuario buscaUsuarioPorId(Long id) {
		for (Usuario usuario : this.banco.getUsuarios())
			if (usuario.getId().equals(id))
				return usuario;

		return null;
	}

	public Usuario buscaUsuarioPorEmail(String email) {
		for (Usuario usuario : this.banco.getUsuarios())
			if (usuario.getEmail().equals(email))
				return usuario;

		return null;
	}

	public boolean atualizaUsuario(Usuario atualizado) {
		if (atualizado.getNome().equals("") || atualizado.getEmail().equals("") || atualizado.getSenha().equals(""))
			return false;

		for (Usuario salvo : this.banco.getUsuarios()) {
			if (salvo.getId().equals(atualizado.getId())) {

				salvo.setEmail(atualizado.getEmail());
				salvo.setNome(atualizado.getNome());
				salvo.setSenha(atualizado.getSenha());

				return true;
			}
		}

		return false;
	}

	public boolean deletaUsuario(Usuario usuario) {
		return this.banco.getUsuarios().remove(usuario);
	}

}

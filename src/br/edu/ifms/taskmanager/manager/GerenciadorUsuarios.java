package br.edu.ifms.taskmanager.manager;

import static br.edu.ifms.taskmanager.main.Main.input;
import static br.edu.ifms.taskmanager.main.Main.print;

import br.edu.ifms.taskmanager.dao.UsuarioDAO;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Usuario;

public class GerenciadorUsuarios {
	Banco banco;
	Long qtde_usuarios;
	UsuarioDAO usuarioDAO;
	
	public GerenciadorUsuarios(Banco banco) {
		this.banco = banco;
		this.qtde_usuarios = (long) banco.getUsuarios().size();
		this.usuarioDAO = new UsuarioDAO(banco);
	}
	
	public void gerenciarUsuarios() {
		while (true) {
			String opcao = input(
					"User Manager v1.33.7\n" +
					"\n" +
					"[A][d]iciona usuário\n" +
					"[B]usca usuário por [I][D]\n" +
					"[L]i[s]tar usuários\n" +
					"[A][t]ualiza usuário\n" +
					"[D][e][l]eta usuário\n" +
					"# [s]udo rm -fr /");

			switch (opcao.toUpperCase()) {
			case "AD":
				Usuario usuario = new Usuario();
				
				String email = input("Endereço eletrônico:");
				String nome = input("Nome:");
				String senha = input("Senha:");
				
				usuario.setId(qtde_usuarios);
				usuario.setEmail(email);
				usuario.setNome(nome);
				usuario.setSenha(senha);
				
				print(this.adicionaUsuario(usuario));
				break;
				
			case "BID":
				Long id = null;
				try {
					id = Long.valueOf(input("ID do usuário:"));
					
				} catch(Exception exception) {
					print("ID inválido."); break;
				}
				
				print(this.buscaUsuarioPorID(id));
				break;
				
			case "LS":
				print(this.listaUsuarios());
				break;
				
			case "AT":
				id = null;
				try {
					id = Long.valueOf(input("ID do usuário:"));
					
				} catch(Exception exception) {
					print("ID inválido."); break;
				}
				
				usuario = usuarioDAO.buscaUsuarioPorId(id);
				
				if(usuario == null) {
					print("Usuário inválido."); break;
				}
				
				email = input("Endereço eletrônico:", usuario.getEmail());
				nome = input("Nome:", usuario.getNome());
				senha = input("Senha:", usuario.getSenha());
				
				print(this.atualizaUsuario(usuario, email, nome, senha));
				break;
				
			case "DEL":
				id = null;
				try {
					id = Long.valueOf(input("ID do usuário:"));

				} catch(Exception exception) {
					print("ID inválido."); break;
				}
				
				usuario = usuarioDAO.buscaUsuarioPorId(id);
				
				if(usuario == null) {
					print("Usuário inválido."); break;
				}
				
				String string = input(usuario.toString() + "Excluir este usuário? [Y/n]");
				print(this.deletaUsuario(usuario, string));
				break;
				
			case "S":
				return;
				
			default:
				print("Opção inválida, Sherlock Holmes!");
			}
			qtde_usuarios = (long) banco.getUsuarios().size();
		}
	}
	
	public String adicionaUsuario(Usuario usuario) {
		return usuarioDAO.adicionaUsuario(usuario) ? "O usuário foi adicionado com êxito." : "O usuário não foi adicionado.";
	}
	
	public String buscaUsuarioPorID(Long id) {
		Usuario usuario = usuarioDAO.buscaUsuarioPorId(id);
		return usuario != null ? usuario.toString() : "O usuário não foi encontrado.";
	}
	
	public String listaUsuarios() {
		return banco.getUsuarios().size() != 0 ? usuarioDAO.listaUsuarios() : "Nenhum usuário foi encontrado.";
	}
	
	public String atualizaUsuario(Usuario usuario, String email, String nome, String senha) {
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSenha(senha);
		
		return usuarioDAO.atualizaUsuario(usuario) ? "O usuário foi atualizado com êxito." : "O usuário não foi atualizado.";
	}
	
	public String deletaUsuario(Usuario usuario, String string) {
		if(string.equals("") || string.toUpperCase().equals("Y"))
			if(new UsuarioDAO(banco).deletaUsuario(usuario))
				return "O usuário foi deletado com êxito.";
		return "O usuário não foi deletado.";
	}
}
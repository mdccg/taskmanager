package br.edu.ifms.taskmanager.manager;

import static br.edu.ifms.taskmanager.main.Main.input;
import static br.edu.ifms.taskmanager.main.Main.print;

import br.edu.ifms.taskmanager.dao.UsuarioDAO;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Usuario;

public class GerenciadorUsuarios {
	Banco banco;
	
	public GerenciadorUsuarios(Banco banco) {
		this.banco = banco;
	}
	
	public void gerenciarUsuarios() {
		Long qtde_usuarios = (long) banco.getUsuarios().size();
		
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
				
				usuario.setEmail(email);
				usuario.setNome(nome);
				usuario.setSenha(senha);
				
				if(new UsuarioDAO(banco).adicionaUsuario(usuario)) {
					usuario.setId((long) ++qtde_usuarios);
					print("O usuário foi adicionado com êxito.");
					
				} else
					print("O usuário não foi adicionado.");
				break;
				
			case "BID":
				Long id = null;
				try {
					id = Long.valueOf(input("ID do usuário:"));
					
				} catch(Exception exception) {
					print("ID inválido."); break;
				}
				
				usuario = new UsuarioDAO(banco).buscaUsuarioPorId(id);
				
				if(usuario != null)
					print(usuario.toString());
				else
					print("O usuário não foi encontrado.");
				break;
				
			case "LS":
				if(banco.getUsuarios().size() == 0)
					print("Nenhum usuário foi encontrado.");
				else
					print(new UsuarioDAO(banco).listaUsuarios());
				break;
				
			case "AT":
				id = null;
				try {
					id = Long.valueOf(input("ID do usuário:"));
					
				} catch(Exception exception) {
					print("ID inválido."); break;
				}
				
				usuario = new UsuarioDAO(banco).buscaUsuarioPorId(id);
				
				if(usuario == null) {
					print("Usuário inválido."); break;
				}
				
				email = input("Endereço eletrônico:", usuario.getEmail());
				nome = input("Nome:", usuario.getNome());
				senha = input("Senha:", usuario.getSenha());
				
				usuario.setEmail(email);
				usuario.setNome(nome);
				usuario.setSenha(senha);
				
				if(new UsuarioDAO(banco).atualizaUsuario(usuario))
					print("O usuário foi atualizado com êxito.");
				else
					print("O usuário não foi atualizado.");
				break;
				
			case "DEL":
				id = null;
				try {
					id = Long.valueOf(input("ID do usuário:"));

				} catch(Exception exception) {
					print("ID inválido."); break;
				}
				
				usuario = new UsuarioDAO(banco).buscaUsuarioPorId(id);
				
				if(usuario == null) {
					print("Usuário inválido."); break;
				}
				
				String string = input(usuario.toString() + "Excluir este usuário? [Y/n]");
				
				if(string.equals("") || string.toUpperCase().equals("Y"))
					if(new UsuarioDAO(banco).deletaUsuario(usuario))
						print("O usuário foi deletado com êxito.");
				else
					print("O usuário não foi deletado.");
				break;
				
			case "S":
				return;
				
			default:
				print("Opção inválida, Sherlock Holmes!");
			}
		}
	}
}
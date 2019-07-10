package br.edu.ifms.taskmanager.manager;

import br.edu.ifms.taskmanager.dao.UsuarioDAO;

import static br.edu.ifms.taskmanager.manager.Main.input;
import static br.edu.ifms.taskmanager.manager.Main.print;

import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Usuario;

public class GerenciadorUsuarios {
	Banco banco;
	
	public GerenciadorUsuarios(Banco banco) {
		this.banco = banco;
	}
	
	void gerenciarUsuarios() {
		Long qtde_usuarios = 0l;
		
		while (true) {
			String opcao = input("(A)diciona usuário\n" +
					"(B)usca usuário por ID\n" +
					"(AT)ualiza usuário\n" +
					"(D)eleta usuário\n" +
					"(s)udo rm -fr /");

			switch (opcao.toUpperCase()) {
			case "A":
				Usuario usuario = new Usuario();
				
				String email = input("Endereço eletrônico:");
				String nome = input("Nome:");
				String senha = input("Senha:");
				
				usuario.setEmail(email);
				usuario.setNome(nome);
				usuario.setSenha(senha);
				
				if(new UsuarioDAO(banco).adicionaUsuario(usuario))
					usuario.setId(++qtde_usuarios);
				else
					print("O usuário não foi adicionado.");
				break;
				
			case "B":
				Long id = null;
				try {
					id = Long.valueOf(input("ID do usuário:"));
				} catch(Exception exception) {
					print("Usuário inválido."); break;
				}
				
				usuario = new UsuarioDAO(banco).buscaUsuarioPorId(id);
				
				if(usuario != null)
					print(usuario.toString());
				else
					print("O usuário não foi encontrado.");
				break;
				
			case "AT":
				id = null;
				try {
					id = Long.valueOf(input("ID do usuário:"));
				} catch(Exception exception) {
					print("Usuário inválido."); break;
				}
				
				usuario = new UsuarioDAO(banco).buscaUsuarioPorId(id);
				
				if(usuario == null)
					return;
				
				email = input("Endereço eletrônico:");
				nome = input("Nome:");
				senha = input("Senha:");
				
				usuario.setEmail(email);
				usuario.setNome(nome);
				usuario.setSenha(senha);
				
				if(new UsuarioDAO(banco).atualizaUsuario(usuario))
					print("Usuário atualizado com êxito.");
				else
					print("O usuário não foi atualizado.");
				break;
				
			case "D":
				id = null;
				try {
					id = Long.valueOf(input("ID do usuário:"));
				} catch(Exception exception) {
					print("Usuário inválido."); break;
				}
				
				usuario = new UsuarioDAO(banco).buscaUsuarioPorId(id);
				
				if(new UsuarioDAO(banco).deletaUsuario(usuario))
					print("Usuário deletado com êxito.");
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
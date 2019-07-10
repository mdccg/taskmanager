/*
 * Querido mantenedor:
 *
 * Quando eu escrevi este código, apenas eu e Deus
 * sabíamos o que era.
 * Agora, só Deus sabe!
 *
 * Então se você acabou de tentar 'otimizar'
 * esta rotina (e falhou),
 * por favor incremente o seguinte contador
 * como um aviso
 * para o próximo cara:
 *
 * total_de_horas_perdidas_aqui = 67
 */

package br.edu.ifms.taskmanager.manager;

import br.edu.ifms.taskmanager.dao.UsuarioDAO;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Usuario;

import static br.edu.ifms.taskmanager.manager.Main.input;
import static br.edu.ifms.taskmanager.manager.Main.print;

import java.sql.Date;

import javax.swing.JOptionPane;

public class Main {
	public static Date inputData(String input) {
		boolean valido = false;
		Date data = null;
		do {
			try {
				String[] string = input(input).split("/"); 
				String stringData = new String(); 
				
				for(int i = 2; i > -1; --i) {
					stringData += string[i];
					
					if(i >= 1)
						stringData += "-";
				}
				
				data = Date.valueOf(stringData);
				valido = true;
				
			} catch(Exception exception) {
				print("Data inválida.");
			}
		} while(!valido);
		
		return data;
	}
	
	public static String input(String string) {
		return JOptionPane.showInputDialog(null, string);
	}

	public static String input() {
		return JOptionPane.showInputDialog(null, "");
	}

	public static void print(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	
	public static void main(String[] args) {
		Banco banco = new Banco();
		
		while(true) {
			String opcao = input("# -*- TASK MANAGER v2000 -*-\n" +
					"sponsored by IFMS\n" +
					"Gerenciar (U)suários\n" +
					"Gerenciar (T)arefas\n" +
					"Gerenciar (C)ategorias\n" +
					"(p)oweroff");
			
			switch(opcao.toUpperCase()) {
			case "U":
				new GerenciadorUsuarios(banco).gerenciarUsuarios();
				break;
			
			case "T":
				Long id_usuario = null;
				try {
					id_usuario = Long.valueOf(input("ID do usuário:"));
				} catch(Exception exception) {
					print("Usuário inválido."); break;
				}
				
				Usuario usuario = new UsuarioDAO(banco).buscaUsuarioPorId(id_usuario);
				
				if(usuario == null) {
					print("Usuário inválido."); break;
				}
				
				new GerenciadorTarefas(banco).gerenciarTarefas(usuario);
				break;
				
			case "C":
				id_usuario = null;
				try {
					id_usuario = Long.valueOf(input("ID do usuário:"));
				} catch(Exception exception) {
					print("Usuário inválido."); break;
				}
				
				usuario = new UsuarioDAO(banco).buscaUsuarioPorId(id_usuario);
				
				if(usuario == null) {
					print("Usuário inválido."); break;
				}
				
				new GerenciadorCategorias(banco).gerenciarCategorias(usuario);
				break;
				
			case "P":
				return;
				
			default:
				print("Opção inválida, Sherlock Holmes.");
			}
		}
	}
}

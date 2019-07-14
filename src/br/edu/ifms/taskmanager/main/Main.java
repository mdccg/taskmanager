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
 * total_de_horas_perdidas_aqui = 134
 */

package br.edu.ifms.taskmanager.main;

import java.io.IOException;
import java.sql.Date;

import javax.swing.JOptionPane;

import br.edu.ifms.taskmanager.dao.UsuarioDAO;
import br.edu.ifms.taskmanager.manager.GerenciadorCategorias;
import br.edu.ifms.taskmanager.manager.GerenciadorTarefas;
import br.edu.ifms.taskmanager.manager.GerenciadorUsuarios;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Usuario;

public class Main {
	public static Date inputDate(String string, Object object) {
		boolean valido = false;
		Date date = null;
		do {
			try {
				String[] strings = input(string, object).split("/");
				String stringDate = new String();

				for (int i = 2; i > -1; --i) {
					stringDate += strings[i];

					if (i >= 1)
						stringDate += "-";
				}

				date = Date.valueOf(stringDate);
				valido = true;

			} catch (Exception exception) {
				print("Data inválida.");
			}
		} while (!valido);

		return date;
	}

	public static Date inputDate(String string) {
		boolean valido = false;
		Date date = null;
		do {
			try {
				String[] strings = input(string).split("/");
				String stringDate = new String();

				for (int i = 2; i > -1; --i) {
					stringDate += strings[i];

					if (i >= 1)
						stringDate += "-";
				}

				date = Date.valueOf(stringDate);
				valido = true;

			} catch (Exception exception) {
				print("Data inválida.");
			}
		} while (!valido);

		return date;
	}

	public static String input(String string, Object object) {
		return JOptionPane.showInputDialog(string, object);
	}

	public static String input(String string) {
		return JOptionPane.showInputDialog(string);
	}

	public static String input() {
		return JOptionPane.showInputDialog("");
	}

	public static void print(Object string) {
		JOptionPane.showMessageDialog(null, string);
	}

	public static boolean verificaCadastro(Banco banco, GerenciadorTarefas gerenciadorTarefas) {
		String string = input("Endereço eletrônico:");

		Usuario usuario = new UsuarioDAO(banco).buscaUsuarioPorEmail(string);

		if (usuario == null) {
			print("Endereço eletrônico inválido.");
			return false;
		}

		String senha = input("Senha:");

		if (usuario.getSenha().equals(senha))
			gerenciadorTarefas.gerenciarTarefas(usuario);

		return false;
	}

	public static boolean verificaCadastro(Banco banco, GerenciadorCategorias gerenciadorCategorias) {
		String string = input("Endereço eletrônico:");

		Usuario usuario = new UsuarioDAO(banco).buscaUsuarioPorEmail(string);

		if (usuario == null) {
			print("Endereço eletrônico inválido.");
			return false;
		}

		String senha = input("Senha:");

		if (usuario.getSenha().equals(senha))
			gerenciadorCategorias.gerenciarCategorias(usuario);

		return false;
	}

	public static void main(String[] args) throws IOException {
		Banco banco = new Banco();

		while (true) {
			String opcao = input("Task Manager Trial Version\n" + "\n" + "[G]erenciar [u]suários\n"
					+ "[G]erenciar [t]arefas\n" + "[G]erenciar [c]ategorias\n" + "$ [p]oweroff");

			switch (opcao.toUpperCase()) {
			case "GU":
				new GerenciadorUsuarios(banco).gerenciarUsuarios();
				break;

			case "GT":
				verificaCadastro(banco, new GerenciadorTarefas(banco));
				break;

			case "GC":
				verificaCadastro(banco, new GerenciadorCategorias(banco));
				break;

			case "P":
				banco.close();
				return;

			default:
				print("Opção inválida, Sherlock Holmes!");
			}
		}
	}
}

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

package br.edu.ifms.taskmanager.main;

import com.google.gson.Gson;

import br.edu.ifms.taskmanager.dao.UsuarioDAO;
import br.edu.ifms.taskmanager.manager.GerenciadorCategorias;
import br.edu.ifms.taskmanager.manager.GerenciadorTarefas;
import br.edu.ifms.taskmanager.manager.GerenciadorUsuarios;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.*;

import java.sql.Date;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Main {
	public static void checkpoint(Banco banco) throws IOException {
		Gson gson = new Gson();

		FileWriter fileWriter = new FileWriter("src/br/edu/ifms/taskmanager/mockBD/Categorias.json");
		PrintWriter printWriter = new PrintWriter(fileWriter);

		for (Categoria categoria : banco.getCategorias())
			printWriter.printf("%s\n", gson.toJson(categoria));

		fileWriter.close();

		fileWriter = new FileWriter("src/br/edu/ifms/taskmanager/mockBD/Tarefas.json");
		printWriter = new PrintWriter(fileWriter);

		for (Tarefa tarefa : banco.getTarefas())
			printWriter.printf("%s\n", gson.toJson(tarefa));

		fileWriter.close();

		fileWriter = new FileWriter("src/br/edu/ifms/taskmanager/mockBD/Usuarios.json");
		printWriter = new PrintWriter(fileWriter);

		for (Usuario usuario : banco.getUsuarios())
			printWriter.printf("%s\n", gson.toJson(usuario));

		fileWriter.close();
	}

	public static Date inputData(String input, Object args) {
		boolean valido = false;
		Date data = null;
		do {
			try {
				String[] string = input(input, args).split("/");
				String stringData = new String();

				for (int i = 2; i > -1; --i) {
					stringData += string[i];

					if (i >= 1)
						stringData += "-";
				}

				data = Date.valueOf(stringData);
				valido = true;

			} catch (Exception exception) {
				print("Data inválida.");
			}
		} while (!valido);

		return data;
	}
	
	public static Date inputData(String input) {
		boolean valido = false;
		Date data = null;
		do {
			try {
				String[] string = input(input).split("/");
				String stringData = new String();

				for (int i = 2; i > -1; --i) {
					stringData += string[i];

					if (i >= 1)
						stringData += "-";
				}

				data = Date.valueOf(stringData);
				valido = true;

			} catch (Exception exception) {
				print("Data inválida.");
			}
		} while (!valido);

		return data;
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

	public static void main(String[] args) throws IOException {
		Banco banco = new Banco();

		while (true) {
			String opcao = input(
					"Task Manager Trial Version\n" +
					"\n" +
					"[G]erenciar [u]suários\n" +
					"[G]erenciar [t]arefas\n" +
					"[G]erenciar [c]ategorias\n" +
					"$ [p]oweroff");

			switch (opcao.toUpperCase()) {
			case "GU":
				new GerenciadorUsuarios(banco).gerenciarUsuarios();
				break;

			case "GT":
				String email = input("Endereço eletrônico:");
				
				Usuario usuario = new UsuarioDAO(banco).buscaUsuarioPorEmail(email);
				if (usuario == null) {
					print("Endereço eletrônico inválido.");
					break;
				}
				
				String senha = input("Senha:");
				
				if(usuario.getSenha().equals(senha))
					new GerenciadorTarefas(banco).gerenciarTarefas(usuario);
				else
					print("Senha inválida.");
				
				break;

			case "GC":
				email = input("Endereço eletrônico:");
				
				usuario = new UsuarioDAO(banco).buscaUsuarioPorEmail(email);
				if (usuario == null) {
					print("Endereço eletrônico inválido.");
					break;
				}
				
				senha = input("Senha:");
				
				if(usuario.getSenha().equals(senha))
					new GerenciadorCategorias(banco).gerenciarCategorias(usuario);
				else
					print("Senha inválida.");
				
				break;

			case "P":
				checkpoint(banco);
				return;

			default:
				print("Opção inválida, Sherlock Holmes!");
			}
		}
	}
}

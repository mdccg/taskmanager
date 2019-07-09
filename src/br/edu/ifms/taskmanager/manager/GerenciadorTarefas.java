package br.edu.ifms.taskmanager.manager;

import br.edu.ifms.taskmanager.dao.TarefaDAO;
import static br.edu.ifms.taskmanager.manager.GerenciadorUsuarios.input;
import static br.edu.ifms.taskmanager.manager.GerenciadorUsuarios.print;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.*;

import java.sql.Date;

public class GerenciadorTarefas {
	public static void main(String[] args) {
		Banco banco = new Banco();
		Long qtde_tarefas = 0l;

		while (true) {
			String opcao = input("(A)diciona tarefa\n" +
					"(B)usca tarefa por ID\n" +
					"(AT)ualiza tarefa\n" +
					"(D)eleta tarefa\n" +
					"(s)udo rm -fr /");

			switch (opcao.toUpperCase()) {
			case "A":
				Tarefa tarefa = new Tarefa();
				
				String titulo = input("Título:");
				Date prazo = Date.valueOf(input("Prazo:"));
				String prioridade = input("Prioridade:");
				
				// TODO
				
				if(new TarefaDAO(banco).adicionaTarefa(tarefa))
					tarefa.setId(++qtde_tarefas);
				else
					print("A tarefa não foi adicionada.");
				break;
				
			case "B":
				Long id = Long.valueOf(input("ID do tarefa:"));
				tarefa = new TarefaDAO(banco).buscaTarefaPorId(id);
				
				if(tarefa != null)
					print(tarefa.toString());
				else
					print("A tarefa não foi encontrada.");
				break;
				
			case "AT":
				id = Long.valueOf(input("ID do tarefa:"));
				tarefa = new TarefaDAO(banco).buscaTarefaPorId(id);
				
				if(tarefa == null)
					return;
				
				// TODO
				
				if(new TarefaDAO(banco).atualizaTarefa(tarefa))
					print("Tarefa atualizada com êxito.");
				else
					print("A tarefa não foi atualizada.");
				break;
				
			case "D":
				id = Long.valueOf(input("ID do tarefa:"));
				tarefa = new TarefaDAO(banco).buscaTarefaPorId(id);
				
				if(new TarefaDAO(banco).deletaTarefa(tarefa))
					print("Tarefa deletada com êxito.");
				else
					print("A tarefa não foi deletada.");
				break;
				
			case "S":
				return;
				
			default:
				print("Opção inválida, Sherlock Holmes!");
			}
		}
	}
}
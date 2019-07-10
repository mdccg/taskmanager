package br.edu.ifms.taskmanager.manager;

import br.edu.ifms.taskmanager.dao.CategoriaDAO;
import br.edu.ifms.taskmanager.dao.TarefaDAO;

import static br.edu.ifms.taskmanager.manager.Main.inputData;
import static br.edu.ifms.taskmanager.manager.Main.input;
import static br.edu.ifms.taskmanager.manager.Main.print;

import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.*;

import java.sql.Date;

import java.util.Calendar;

public class GerenciadorTarefas {
	Banco banco;
	
	public GerenciadorTarefas(Banco banco) {
		this.banco = banco;
	}
	
	void gerenciarTarefas (Usuario usuario) {
		Long qtde_tarefas = (long) banco.getTarefas().size();
		
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
				Date prazo = inputData("Prazo: dd/MM/yyyy");
				String prioridade = input("Prioridade:");
				
				Calendar calendar = Calendar.getInstance();
				Date dataCriacao = new Date(calendar.getTime().getTime());
				Date dataEdicao = new Date(calendar.getTime().getTime());
				
				tarefa.setTitulo(titulo);
				tarefa.setPrazo(prazo);
				tarefa.setPrioridade(prioridade);
				tarefa.setDataCriacao(dataCriacao);
				tarefa.setDataEdicao(dataEdicao);
				tarefa.setId_Usuario(usuario.getId());
				usuario.getTarefas().add(tarefa);
				
				if(new TarefaDAO(banco).adicionaTarefa(tarefa)) {
					tarefa.setId((long) ++qtde_tarefas);
					
					boolean valido = false;
					Long id_categoria = null;
					do {
						try {
							id_categoria = Long.valueOf(input("ID da categoria: (-1 se não houver)"));
							if(id_categoria == -1l) break;
							
							tarefa.setId_Categoria(id_categoria);
							Categoria categoria = new CategoriaDAO(banco).buscaCategoriaPorId(id_categoria);
							categoria.getId_Tarefas().add(tarefa.getId());
							
							valido = true;
							if(new CategoriaDAO(banco).buscaCategoriaPorId(id_categoria) == null)
								valido = false;
							
						} catch(Exception exception) {
							print("Categoria inválida.");
						}
					} while(!valido);
					
					if(id_categoria == -1l) 
						tarefa.setId_Categoria(-1l);
					
					print("A tarefa foi adicionada com êxito.");
					
				} else
					print("A tarefa não foi adicionada.");
				break;
				
			case "B":
				Long id = null;
				try {
					id = Long.valueOf(input("ID da tarefa:"));
				} catch(Exception exception) {
					print("Tarefa inválida."); break;
				}
				
				tarefa = new TarefaDAO(banco).buscaTarefaPorId(id);
				
				if(tarefa != null)
					print(tarefa.toString());
				else
					print("A tarefa não foi encontrada.");
				break;
				
			case "AT":
				id = null;
				try {
					id = Long.valueOf(input("ID da tarefa:"));
				} catch(Exception exception) {
					print("Tarefa inválida."); break;
				}
				
				tarefa = new TarefaDAO(banco).buscaTarefaPorId(id);
				
				if(tarefa == null) {
					print("Tarefa inválida.");
					break;
				}
				
				titulo = input("Título:");
				prazo = inputData("Prazo: dd/MM/yyyy");
				prioridade = input("Prioridade:");
				
				calendar = Calendar.getInstance();
				dataEdicao = new Date(calendar.getTime().getTime());
				
				tarefa.setTitulo(titulo);
				tarefa.setPrazo(prazo);
				tarefa.setPrioridade(prioridade);
				tarefa.setDataEdicao(dataEdicao);
				
				
				if(new TarefaDAO(banco).atualizaTarefa(tarefa)) {
					tarefa.setId((long) ++qtde_tarefas);
					
					boolean valido = false;
					Long id_categoria = null;
					do {
						try {
							id_categoria = Long.valueOf(input("ID da categoria: (-1 se não houver)"));
							if(id_categoria == -1l) break;
							
							tarefa.setId_Categoria(id_categoria);
							Categoria categoria = new CategoriaDAO(banco).buscaCategoriaPorId(id_categoria);
							categoria.getId_Tarefas().add(tarefa.getId());
							
							valido = true;
							if(new CategoriaDAO(banco).buscaCategoriaPorId(id_categoria) == null)
								valido = false;
							
						} catch(Exception exception) {
							print("Categoria inválida.");
						}
					} while(!valido);
					print("A tarefa foi atualizada com êxito.");
					
				} else
					print("A tarefa não foi atualizada.");
			break;
				
			case "D":
				id = null;
				try {
					id = Long.valueOf(input("ID da tarefa:"));
				} catch(Exception exception) {
					print("Tarefa inválida."); break;
				}
				
				tarefa = new TarefaDAO(banco).buscaTarefaPorId(id);
				
				if(new TarefaDAO(banco).deletaTarefa(tarefa))
					print("A tarefa deletada com êxito.");
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
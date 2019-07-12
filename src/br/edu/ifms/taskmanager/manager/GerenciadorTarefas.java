package br.edu.ifms.taskmanager.manager;

import static br.edu.ifms.taskmanager.main.Main.input;
import static br.edu.ifms.taskmanager.main.Main.inputData;
import static br.edu.ifms.taskmanager.main.Main.print;

import java.sql.Date;
import java.util.Calendar;

import br.edu.ifms.taskmanager.dao.CategoriaDAO;
import br.edu.ifms.taskmanager.dao.TarefaDAO;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Categoria;
import br.edu.ifms.taskmanager.model.Tarefa;
import br.edu.ifms.taskmanager.model.Usuario;

public class GerenciadorTarefas {
	Banco banco;

	public GerenciadorTarefas(Banco banco) {
		this.banco = banco;
	}

	public void gerenciarTarefas(Usuario usuario) {
		Long qtde_tarefas = (long) banco.getTarefas().size();

		while (true) {
			String opcao = input(
					"Task Manager v1.66.4\n" +
					"\n" +
					usuario.toString() +
					"[A][d]iciona tarefa\n" +
					"[B]usca tarefa por [I][D]\n" +
					"[L]i[s]ta tarefas\n" +
					"[A][t]ualiza tarefa\n" +
					"[D][e][l]eta tarefa\n" +
					"# [s]udo rm -fr /");

			switch (opcao.toUpperCase()) {
			case "AD":
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

				if (new TarefaDAO(banco).adicionaTarefa(tarefa)) {
					tarefa.setId((long) ++qtde_tarefas);

					boolean valido = false;
					Long id_categoria = null;
					do {
						try {
							id_categoria = Long.valueOf(input("ID da categoria: (-1 se não houver)"));
							if (id_categoria == -1l) {
								tarefa.setId_Categoria(-1l); break;
							}

							tarefa.setId_Categoria(id_categoria);
							Categoria categoria = new CategoriaDAO(banco).buscaCategoriaPorId(id_categoria);
							categoria.getId_Tarefas().add(tarefa.getId());

							valido = true;
							if (new CategoriaDAO(banco).buscaCategoriaPorId(id_categoria) == null)
								valido = false;

						} catch (Exception exception) {
							print("Categoria inválida.");
						}
					} while (!valido);

					print("A tarefa foi adicionada com êxito.");

				} else
					print("A tarefa não foi adicionada.");
				break;

			case "BID":
				Long id = null;
				try {
					id = Long.valueOf(input("ID da tarefa:"));
					
				} catch (Exception exception) {
					print("ID inválido.");
					break;
				}

				tarefa = new TarefaDAO(banco).buscaTarefaPorId(id);

				if (tarefa != null)
					print(tarefa.toString());
				else
					print("A tarefa não foi encontrada.");
				break;

			case "LS":
				if(banco.getTarefas().size() == 0)
					print("Nenhuma tarefa foi encontrada.");
				else
					print(new TarefaDAO(banco).listaTarefas());
				break;
				
			case "AT":
				id = null;
				try {
					id = Long.valueOf(input("ID da tarefa:"));
					
				} catch (Exception exception) {
					print("ID inválido.");
					break;
				}

				tarefa = new TarefaDAO(banco).buscaTarefaPorId(id);

				if (tarefa == null) {
					print("Tarefa inválida."); break;
				}
				
				if(tarefa.getId_Usuario() != usuario.getId()) {
					print("Esta tarefa foi criada por outro usuário."); break;
				}

				titulo = input("Título:", tarefa.getTitulo());
				prazo = inputData("Prazo: dd/MM/yyyy", tarefa.getPrazo());
				prioridade = input("Prioridade:", tarefa.getPrioridade());

				calendar = Calendar.getInstance();
				dataEdicao = new Date(calendar.getTime().getTime());

				tarefa.setTitulo(titulo);
				tarefa.setPrazo(prazo);
				tarefa.setPrioridade(prioridade);
				tarefa.setDataEdicao(dataEdicao);

				if (new TarefaDAO(banco).atualizaTarefa(tarefa)) {
					boolean valido = false;
					Long id_categoria = null;
					do {
						try {
							id_categoria = Long.valueOf(input("ID da categoria: (-1 se não houver)", tarefa.getId_Categoria()));
							if (id_categoria == -1l)
								break;

							tarefa.setId_Categoria(id_categoria);
							Categoria categoria = new CategoriaDAO(banco).buscaCategoriaPorId(id_categoria);
							categoria.getId_Tarefas().add(tarefa.getId());

							valido = true;
							if (new CategoriaDAO(banco).buscaCategoriaPorId(id_categoria) == null)
								valido = false;

						} catch (Exception exception) {
							print("Categoria inválida.");
						}
					} while (!valido);
					print("A tarefa foi atualizada com êxito.");

				} else
					print("A tarefa não foi atualizada.");
				break;

			case "DEL":
				id = null;
				try {
					id = Long.valueOf(input("ID da tarefa:"));
				
				} catch (Exception exception) {
					print("ID inválido."); break;
				}

				tarefa = new TarefaDAO(banco).buscaTarefaPorId(id);
				
				if(tarefa == null) {
					print("Tarefa inválida."); break;
				}
				
				String string = input(tarefa.toString() + "Excluir esta tarefa? [Y/n]");

				if(string.equals("") || string.toUpperCase().equals("Y"))
					if(new TarefaDAO(banco).deletaTarefa(tarefa))
						print("A tarefa foi deletada com êxito.");
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
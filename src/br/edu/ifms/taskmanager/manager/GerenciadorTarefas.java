package br.edu.ifms.taskmanager.manager;

import static br.edu.ifms.taskmanager.main.Main.input;
import static br.edu.ifms.taskmanager.main.Main.inputDate;
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
	Long qtde_tarefas;
	TarefaDAO tarefaDAO;

	public GerenciadorTarefas(Banco banco) {
		this.banco = banco;
		qtde_tarefas = (long) banco.getTarefas().size();
		tarefaDAO = new TarefaDAO(banco);
	}

	public void gerenciarTarefas(Usuario usuario) {
		while (true) {
			String opcao = input("Task Manager v1.66.4\n" + "\n" + usuario.toString() + "[A][d]iciona tarefa\n"
					+ "[B]usca tarefa por [I][D]\n" + "[L]i[s]ta tarefas\n" + "[A][t]ualiza tarefa\n"
					+ "[D][e][l]eta tarefa\n" + "# [s]udo rm -fr /");

			switch (opcao.toUpperCase()) {
			case "AD":
				String titulo = input("Título:");
				Date prazo = inputDate("Prazo: dd/MM/yyyy");
				String prioridade = input("Prioridade:");

				boolean valido = false;
				Long id_categoria = null;
				do {
					try {
						id_categoria = Long.valueOf(input("ID da categoria: (-1 se não houver)"));
						if (id_categoria == -1l)
							break;

						valido = new CategoriaDAO(banco).buscaCategoriaPorId(id_categoria) != null;

					} catch (Exception exception) {
						print("ID inválido.");
					}
				} while (!valido);

				print(this.adicionaTarefa(titulo, prazo, prioridade, id_categoria, usuario));
				break;

			case "BID":
				Long id = null;
				try {
					id = Long.valueOf(input("ID da tarefa:"));

				} catch (Exception exception) {
					print("ID inválido.");
					break;
				}

				print(this.buscaTarefaPorID(id));
				break;

			case "LS":
				print(this.listaTarefas());
				break;

			case "AT":
				id = null;
				try {
					id = Long.valueOf(input("ID da tarefa:"));

				} catch (Exception exception) {
					print("ID inválido.");
					break;
				}

				Tarefa tarefa = tarefaDAO.buscaTarefaPorId(id);

				if (tarefa == null) {
					print("Tarefa inválida.");
					break;
				}

				if (tarefa.getId_Usuario() != usuario.getId()) {
					print("Esta tarefa foi criada por outro usuário.");
					break;
				}

				titulo = input("Título:", tarefa.getTitulo());
				prazo = inputDate("Prazo: dd/MM/yyyy", tarefa.getPrazo());
				prioridade = input("Prioridade:", tarefa.getPrioridade());

				valido = false;
				id_categoria = null;
				do {
					try {
						id_categoria = Long
								.valueOf(input("ID da categoria: (-1 se não houver)", tarefa.getId_Categoria()));
						if (id_categoria == -1l)
							break;

						Categoria categoria = new CategoriaDAO(banco).buscaCategoriaPorId(id_categoria);
						valido = categoria != null;

					} catch (Exception exception) {
						print("Categoria inválida.");
					}
				} while (!valido);

				print(this.atualizaTarefa(tarefa, titulo, prazo, prioridade, id_categoria, usuario));
				break;

			case "DEL":
				id = null;
				try {
					id = Long.valueOf(input("ID da tarefa:"));

				} catch (Exception exception) {
					print("ID inválido.");
					break;
				}

				tarefa = tarefaDAO.buscaTarefaPorId(id);

				if (tarefa == null) {
					print("Tarefa inválida.");
					break;
				}

				String string = input(tarefa.toString() + "Excluir esta tarefa? [Y/n]");

				this.deletaTarefa(string, tarefa);
				break;

			case "S":
				return;

			default:
				print("Opção inválida, Sherlock Holmes!");
			}
			qtde_tarefas = (long) banco.getTarefas().size();
		}
	}

	public String adicionaTarefa(String titulo, Date prazo, String prioridade, Long id_categoria, Usuario usuario) {
		Tarefa tarefa = new Tarefa();

		Calendar calendar = Calendar.getInstance();
		Date dataCriacao = new Date(calendar.getTime().getTime());
		Date dataEdicao = new Date(calendar.getTime().getTime());

		tarefa.setId(qtde_tarefas);
		tarefa.setTitulo(titulo);
		tarefa.setPrazo(prazo);
		tarefa.setPrioridade(prioridade);
		tarefa.setDataCriacao(dataCriacao);
		tarefa.setDataEdicao(dataEdicao);
		tarefa.setId_Categoria(id_categoria);
		tarefa.setId_Usuario(usuario.getId());
		usuario.getTarefas().add(tarefa);

		return tarefaDAO.adicionaTarefa(tarefa) ? "A tarefa foi adicionada com êxito." : "A tarefa não foi adicionada.";
	}

	public String buscaTarefaPorID(Long id) {
		Tarefa tarefa = tarefaDAO.buscaTarefaPorId(id);
		return tarefa != null ? tarefa.toString() : "A tarefa não foi encontrada.";
	}

	public String listaTarefas() {
		return banco.getTarefas().size() == 0 ? "Nenhuma tarefa foi encontrada." : tarefaDAO.listaTarefas();
	}

	public String atualizaTarefa(Tarefa tarefa, String titulo, Date prazo, String prioridade, Long id_categoria,
			Usuario usuario) {
		tarefa.setTitulo(titulo);
		tarefa.setPrazo(prazo);
		tarefa.setPrioridade(prioridade);

		Calendar calendar = Calendar.getInstance();
		Date dataEdicao = new Date(calendar.getTime().getTime());
		tarefa.setDataEdicao(dataEdicao);

		tarefa.setId_Categoria(id_categoria);

		return tarefaDAO.atualizaTarefa(tarefa) ? "A tarefa foi atualizada com êxito." : "A tarefa não foi atualizada.";

	}

	public String deletaTarefa(String string, Tarefa tarefa) {
		if (string.equals("") || string.toUpperCase().equals("Y"))
			return tarefaDAO.deletaTarefa(tarefa) ? "A tarefa foi deletada com êxito." : "A tarefa não foi deletada.";
		return "A tarefa não foi deletada.";
	}
}
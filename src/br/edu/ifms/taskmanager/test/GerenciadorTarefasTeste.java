package br.edu.ifms.taskmanager.test;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifms.taskmanager.dao.TarefaDAO;
import br.edu.ifms.taskmanager.manager.GerenciadorTarefas;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Tarefa;
import br.edu.ifms.taskmanager.model.Usuario;

public class GerenciadorTarefasTeste {
	Banco banco;
	Tarefa tarefa;
	TarefaDAO tarefaDAO;
	GerenciadorTarefas gerenciadorTarefas;

	@Before
	public void setUp() throws FileNotFoundException {
		banco = new Banco();
		tarefa = new Tarefa();
		tarefaDAO = new TarefaDAO(banco);
		gerenciadorTarefas = new GerenciadorTarefas(banco);
	}

	@Test
	public void testaAdicionarTarefaPeloGerenciador() {
		String titulo = "Estagnar procrastinação";
		Date prazo = Date.valueOf("2020-01-01");
		String prioridade = "Baixa";
		Long id_categoria = -1l;
		Usuario usuario = new Usuario(2147483648l, "matheus.gomes@estudante.ifms.edu.br", "Matheus Gomes", "123",
				new ArrayList<>());

		Assert.assertEquals("A tarefa foi adicionada com êxito.",
				gerenciadorTarefas.adicionaTarefa(titulo, prazo, prioridade, id_categoria, usuario));
	}

	@Test
	public void testaBuscarTarefaPorIDPeloGerenciador() {
		Tarefa tarefa = new Tarefa();
		tarefa.setId(2147483648l);
		tarefa.setTitulo("Lançar Minecraft");
		tarefa.setPrazo(Date.valueOf("2009-05-17"));
		tarefa.setPrioridade("Pronto-Socorro");
		tarefa.setDataCriacao(Date.valueOf("2009-05-16"));
		tarefa.setDataEdicao(tarefa.getDataCriacao());
		tarefa.setId_Categoria(-1l);
		tarefa.setId_Usuario(1l);
		tarefaDAO.adicionaTarefa(tarefa);

		Assert.assertEquals(tarefa.toString(), gerenciadorTarefas.buscaTarefaPorID(tarefa.getId()));
	}

	@Test
	public void testaListarTarefasPeloGerenciador() {
		Assert.assertEquals(tarefaDAO.listaTarefas(), gerenciadorTarefas.listaTarefas());
	}

	@Test
	public void testaAtualizarTarefaPeloGerenciador() {
		Tarefa tarefa = tarefaDAO.buscaTarefaPorId(0l);
		Usuario usuario = new Usuario(2147483648l, "matheus.gomes@estudante.ifms.edu.br", "Matheus Gomes", "123",
				new ArrayList<>());

		Assert.assertEquals("A tarefa foi atualizada com êxito.", gerenciadorTarefas.atualizaTarefa(tarefa,
				"Terminar projeto de LP 3", Date.valueOf("2019-07-13"), "Pronto-Socorro", -1l, usuario));
	}

	@Test
	public void testaDeletarTarefaPeloGerenciador() {
		Tarefa tarefa = tarefaDAO.buscaTarefaPorId(0l);
		Assert.assertEquals("A tarefa foi deletada com êxito.", gerenciadorTarefas.deletaTarefa("Y", tarefa));
	}

}

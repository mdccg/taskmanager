package br.edu.ifms.taskmanager.test;

import java.io.FileNotFoundException;
import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifms.taskmanager.dao.TarefaDAO;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Tarefa;

public class TarefaDAOTeste {
	Banco banco;
	TarefaDAO tarefaDAO;
	Tarefa tarefa;
	
	@Before
	public void setUp() throws FileNotFoundException {
		banco = new Banco();
		tarefaDAO = new TarefaDAO(banco);
		tarefa = new Tarefa(1l, "Gosling", Date.valueOf("1970-01-01"), "Média", Date.valueOf("1970-01-01"), Date.valueOf("1970-01-01"), 1l, 1l);
		tarefaDAO.adicionaTarefa(tarefa);
	}
	
	@Test
	public void testaAdicionarTarefa() {
		tarefa = new Tarefa(2l, "James", Date.valueOf("1970-01-01"), "Pronto-Socorro", Date.valueOf("1970-01-01"), Date.valueOf("1970-01-01"), 1l, 2l);
		Assert.assertTrue(tarefaDAO.adicionaTarefa(tarefa));
	}

	@Test
	public void testaAdicionarTarefaComMesmoTitulo() {
		tarefa = new Tarefa(3l, "Gosling", Date.valueOf("1970-01-01"), "Fácil", Date.valueOf("1970-01-01"), Date.valueOf("1970-01-01"), 1l, 1l);
		
		
		Assert.assertFalse(tarefaDAO.adicionaTarefa(tarefa));
	}
	
	@Test
	public void testaDeletarTarefa() {
		Assert.assertTrue(tarefaDAO.deletaTarefa(tarefa));
	}
	
	@Test
	public void testaListarTarefa() {
		String string = tarefa.toString();
		Assert.assertEquals(string, tarefaDAO.listaTarefas());
	}
}

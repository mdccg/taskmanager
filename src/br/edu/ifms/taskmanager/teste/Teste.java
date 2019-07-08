package br.edu.ifms.taskmanager.teste;

import br.edu.ifms.taskmanager.dao.*;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.*;

import java.util.ArrayList;

import java.sql.Date;

public class Teste {
	public static void main(String[] args) {
		Banco banco = new Banco();

		Usuario usuario = new Usuario();
		usuario.setId(1l);
		usuario.setEmail("vinicius.moraes@ifms.edu.br");
		usuario.setNome("Vinicius Moraes");
		usuario.setSenha("1234567890");

		Tarefa tarefa = new Tarefa();
		tarefa.setId(1l);
		tarefa.setTitulo("Disciplina de inverno LP 3");
		tarefa.setPrazo(Date.valueOf("2019-07-08"));
		tarefa.setPrioridade("Pronto-Socorro");

		tarefa.setUsuario(usuario);

		ArrayList<Tarefa> aux = new ArrayList<>();
		aux.add(tarefa);
		usuario.setTarefas(aux);

		Categoria categoria = new Categoria();
		categoria.setId(1l);
		categoria.setTitulo("Trabalho");

		aux = new ArrayList<>();
		tarefa.setCategoria(categoria);
		aux.add(tarefa);
		categoria.setTarefas(aux);

		new UsuarioDAO(banco).adicionaUsuario(usuario);
		new TarefaDAO(banco).adicionaTarefa(tarefa);
		new CategoriaDAO(banco).adicionaCategoria(categoria);

		for (Tarefa teste : banco.getTarefas())
			System.out.println(teste.toString());
	}
}

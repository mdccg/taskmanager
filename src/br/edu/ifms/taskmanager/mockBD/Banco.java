package br.edu.ifms.taskmanager.mockBD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import br.edu.ifms.taskmanager.model.Categoria;
import br.edu.ifms.taskmanager.model.Tarefa;
import br.edu.ifms.taskmanager.model.Usuario;

public class Banco {
	private ArrayList<Categoria> categorias;
	private ArrayList<Tarefa> tarefas;
	private ArrayList<Usuario> usuarios;

	public Banco() throws FileNotFoundException {
		this.categorias = new ArrayList<>();
		this.tarefas = new ArrayList<>();
		this.usuarios = new ArrayList<>();

		this.parseObjeto("src/br/edu/ifms/taskmanager/mockBD/Categorias.json", Categoria.class, this.categorias);
		this.parseObjeto("src/br/edu/ifms/taskmanager/mockBD/Tarefas.json", Tarefa.class, this.tarefas);
		this.parseObjeto("src/br/edu/ifms/taskmanager/mockBD/Usuarios.json", Usuario.class, this.usuarios);

		this.categorias = (this.categorias == null) ? new ArrayList<>() : this.categorias;
		this.tarefas = (this.tarefas == null) ? new ArrayList<>() : this.tarefas;
		this.usuarios = (this.usuarios == null) ? new ArrayList<>() : this.usuarios;
	}

	public void close() throws IOException {
		this.parseJSON("src/br/edu/ifms/taskmanager/mockBD/Categorias.json", Categoria.class, this.categorias);
		this.parseJSON("src/br/edu/ifms/taskmanager/mockBD/Tarefas.json", Tarefa.class, this.tarefas);
		this.parseJSON("src/br/edu/ifms/taskmanager/mockBD/Usuarios.json", Usuario.class, this.usuarios);
	}

	public Banco(ArrayList<Categoria> categorias, ArrayList<Tarefa> tarefas, ArrayList<Usuario> usuarios) {
		super();
		this.categorias = categorias;
		this.tarefas = tarefas;
		this.usuarios = usuarios;
	}

	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ArrayList<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(ArrayList<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public <T> void parseObjeto(String string, Class<T> classOfT, ArrayList<T> arrayList) throws FileNotFoundException {
		File file = new File(string);
		Scanner scanner = new Scanner(file);
		Gson gson = new Gson();
		
		while (scanner.hasNext()) {
			String nextLine = scanner.nextLine();
			T t = gson.fromJson(nextLine, classOfT);
			arrayList.add(t);
		}

		scanner.close();
	}

	public <T> void parseJSON(String string, Class<T> classOfT, ArrayList<T> arrayList) throws IOException {
		FileWriter fileWriter = new FileWriter(string);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		Gson gson = new Gson();

		for (T t : arrayList)
			printWriter.printf("%s\n", gson.toJson(t));

		fileWriter.close();
	}

}

package br.edu.ifms.taskmanager.model;

import java.sql.Date;

public class Tarefa {
	private Long id;
	private String titulo;
	private Date prazo;
	private String prioridade;
	private Date dataCriacao;
	private Date dataEdicao;
	private Long id_categoria;
	private Long id_usuario;

	public Tarefa() {
	}

	public Tarefa(Long id, String titulo, Date prazo, String prioridade, Date dataCriacao, Date dataEdicao,
			Long id_categoria, Long id_usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.prazo = prazo;
		this.prioridade = prioridade;
		this.dataCriacao = dataCriacao;
		this.dataEdicao = dataEdicao;
		this.id_categoria = id_categoria;
		this.id_usuario = id_usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataEdicao() {
		return dataEdicao;
	}

	public void setDataEdicao(Date dataEdicao) {
		this.dataEdicao = dataEdicao;
	}

	public Long getId_Categoria() {
		return id_categoria;
	}

	public void setId_Categoria(Long id_categoria) {
		this.id_categoria = id_categoria;
	}

	public Long getId_Usuario() {
		return id_usuario;
	}

	public void setId_Usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\n" +
				"Título: " + titulo + "\n" +
				"Prazo: " + prazo + "\n" +
				"Prioridade: " + prioridade + "\n" +
				"Data de criação: " + dataCriacao + "\n" +
				"Data de edição: " + dataEdicao + "\n" +
				"ID da categoria: " + id_categoria + "\n" +
				"ID do usuário: " + id_usuario + "\n" +
				"\n";
	}

}

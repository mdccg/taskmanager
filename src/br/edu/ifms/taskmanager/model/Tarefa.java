package br.edu.ifms.taskmanager.model;

import br.edu.ifms.taskmanager.model.Categoria;
import br.edu.ifms.taskmanager.model.Usuario;

import java.sql.Date;

public class Tarefa {
	private Long id;
	private String titulo;
	private Date prazo;
	private String prioridade;
	private Date dataCriacao;
	private Date dataEdicao;
	private Categoria categoria;
	private Usuario usuario;

	public Tarefa() {
	}

	public Tarefa(Long id, String titulo, Date prazo, String prioridade, Date dataCriacao, Date dataEdicao,
			Categoria categoria, Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.prazo = prazo;
		this.prioridade = prioridade;
		this.dataCriacao = dataCriacao;
		this.dataEdicao = dataEdicao;
		this.categoria = categoria;
		this.usuario = usuario;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", titulo=" + titulo + ", prazo=" + prazo + ", prioridade=" + prioridade
				+ ", dataCriacao=" + dataCriacao + ", dataEdicao=" + dataEdicao + ", categoria=" + categoria
				+ ", usuario=" + usuario + "]";
	}
	
	
}

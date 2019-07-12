package br.edu.ifms.taskmanager.manager;

import static br.edu.ifms.taskmanager.main.Main.input;
import static br.edu.ifms.taskmanager.main.Main.print;

import br.edu.ifms.taskmanager.dao.CategoriaDAO;
import br.edu.ifms.taskmanager.dao.TarefaDAO;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.*;

public class GerenciadorCategorias {
	public static final int INF = 1 << 29;
	
	Banco banco;
	
	public GerenciadorCategorias(Banco banco) {
		this.banco = banco;
	}
	
	public void gerenciarCategorias (Usuario usuario) {
		Long qtde_categorias = (long) banco.getCategorias().size();
		
		while (true) {
			String opcao = input(
					"Category Manager v1.97.7\n" +
					"\n" +
					usuario.toString() +
					"[A][d]iciona categoria\n" +
					"[B]usca categoria por [I][D]\n" +
					"[L]i[s]ta categorias\n" +
					"[A][t]ualiza categoria\n" +
					"[D][e][l]eta categoria\n" +
					"# [s]udo rm -fr /");

			switch (opcao.toUpperCase()) {
			case "AD":
				Categoria categoria = new Categoria();
				
				String titulo = input("Título:");
				
				categoria.setTitulo(titulo);
				
				if(new CategoriaDAO(banco).adicionaCategoria(categoria)) {
					categoria.setId((long) ++qtde_categorias);
					print("A categoria foi adicionada com êxito.");
					
				}
				else
					print("A categoria não foi adicionada.");
				break;
				
			case "BID":
				Long id = null;
				try {
					id = Long.valueOf(input("ID da categoria:"));
					
				} catch(Exception exception) {
					print("ID inválido."); break;
				}
				
				categoria = new CategoriaDAO(banco).buscaCategoriaPorId(id);
				
				if(categoria != null)
					print(categoria.toString());
				else
					print("A categoria não foi encontrada.");
				break;
				
			case "LS":
				if(banco.getCategorias().size() == 0)
					print("Nenhuma categoria foi encontrada.");
				else
					print(new CategoriaDAO(banco).listaCategorias());
				break;
				
			case "AT":
				id = null;
				try {
					id = Long.valueOf(input("ID da categoria:"));
					
				} catch(Exception exception) {
					print("ID inválido."); break;
				}
				
				categoria = new CategoriaDAO(banco).buscaCategoriaPorId(id);
				
				if(categoria == null) {
					print("Categoria inválida."); break;
				}
				
				titulo = input("Título:", categoria.getTitulo());
				categoria.setTitulo(titulo);
				
				if(new CategoriaDAO(banco).atualizaCategoria(categoria))
					print("A categoria foi atualizada com êxito.");
				else
					print("A categoria não foi atualizada.");
				break;
				
			case "DEL":
				id = null;
				try {
					id = Long.valueOf(input("ID da categoria:"));
				
				} catch(Exception exception) {
					print("ID inválido."); break;
				}
				
				categoria = new CategoriaDAO(banco).buscaCategoriaPorId(id);
				
				if(categoria == null) {
					print("Categoria inválida."); break;
				}
				
				String string = input(categoria.toString() + "Excluir esta categoria? [Y/n]");
				
				if(string.equals("") || string.toUpperCase().equals("Y"))
					if(new CategoriaDAO(banco).deletaCategoria(categoria))
						print("A categoria foi deletada com êxito.");
				else
					print("A categoria não foi deletada.");
				break;
				
			case "S":
				return;
				
			default:
				print("Opção inválida, Sherlock Holmes!");
			}
		}
	}
}

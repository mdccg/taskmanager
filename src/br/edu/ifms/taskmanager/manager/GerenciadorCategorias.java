package br.edu.ifms.taskmanager.manager;

import static br.edu.ifms.taskmanager.manager.Main.input;
import static br.edu.ifms.taskmanager.manager.Main.print;

import br.edu.ifms.taskmanager.dao.CategoriaDAO;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.*;

public class GerenciadorCategorias {
	Banco banco;
	
	public GerenciadorCategorias(Banco banco) {
		this.banco = banco;
	}
	
	void gerenciarCategorias (Usuario usuario) {
		Long qtde_categorias = 0l;
		
		while (true) {
			String opcao = input("(A)diciona categoria\n" +
					"(B)usca categoria por ID\n" +
					"(AT)ualiza categoria\n" +
					"(D)eleta categoria\n" +
					"(s)udo rm -fr /");

			switch (opcao.toUpperCase()) {
			case "A":
				Categoria categoria = new Categoria();
				
				String titulo = input("Título:");
				
				categoria.setTitulo(titulo);
				
				if(new CategoriaDAO(banco).adicionaCategoria(categoria))
					categoria.setId((long) ++qtde_categorias);
				else
					print("A categoria não foi adicionada.");
				break;
				
			case "B":
				Long id = null;
				try {
					id = Long.valueOf(input("ID da categoria:"));
				} catch(Exception exception) {
					System.out.println("Categoria inválida."); break;
				}
				
				categoria = new CategoriaDAO(banco).buscaCategoriaPorId(id);
				
				if(categoria != null)
					print(categoria.toString());
				else
					print("A categoria não foi encontrada.");
				break;
				
			case "AT":
				id = null;
				try {
					id = Long.valueOf(input("ID da categoria:"));
				} catch(Exception exception) {
					System.out.println("Categoria inválida."); break;
				}
				
				categoria = new CategoriaDAO(banco).buscaCategoriaPorId(id);
				
				if(categoria == null)
					return;
				
				titulo = input("Título:");
				categoria.setTitulo(titulo);
				
				if(new CategoriaDAO(banco).atualizaCategoria(categoria))
					print("Categoria atualizada com êxito.");
				else
					print("A categoria não foi atualizada.");
				break;
				
			case "D":
				id = null;
				try {
					id = Long.valueOf(input("ID da categoria:"));
				} catch(Exception exception) {
					System.out.println("Categoria inválida."); break;
				}
				
				categoria = new CategoriaDAO(banco).buscaCategoriaPorId(id);
				
				if(new CategoriaDAO(banco).deletaCategoria(categoria))
					print("Categoria deletada com êxito.");
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

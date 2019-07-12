package br.edu.ifms.taskmanager.manager;

import static br.edu.ifms.taskmanager.main.Main.input;
import static br.edu.ifms.taskmanager.main.Main.print;

import br.edu.ifms.taskmanager.dao.CategoriaDAO;
import br.edu.ifms.taskmanager.mockBD.Banco;
import br.edu.ifms.taskmanager.model.Categoria;
import br.edu.ifms.taskmanager.model.Usuario;

public class GerenciadorCategorias {
	Banco banco;
	Long qtde_categorias;
	CategoriaDAO categoriaDAO;

	public GerenciadorCategorias(Banco banco) {
		this.banco = banco;
		this.qtde_categorias = (long) banco.getCategorias().size();
		this.categoriaDAO = new CategoriaDAO(banco);
	}

	public void gerenciarCategorias(Usuario usuario) {
		while (true) {
			String opcao = input("Category Manager v1.97.7\n" + "\n" + usuario.toString() + "[A][d]iciona categoria\n"
					+ "[B]usca categoria por [I][D]\n" + "[L]i[s]ta categorias\n" + "[A][t]ualiza categoria\n"
					+ "[D][e][l]eta categoria\n" + "# [s]udo rm -fr /");

			switch (opcao.toUpperCase()) {
			case "AD":
				String titulo = input("Título:");
				print(this.adicionaCategoria(titulo));
				break;

			case "BID":
				try {
					Long id = Long.valueOf(input("ID da categoria:"));
					print(this.buscaCategoriaPorId(id));

				} catch (Exception exception) {
					print("ID inválido.");
				}
				break;

			case "LS":
				print(this.listaCategorias());
				break;

			case "AT":
				try {
					Long id = Long.valueOf(input("ID da categoria:"));
					Categoria categoria = categoriaDAO.buscaCategoriaPorId(id);

					if (categoria == null) {
						print("Categoria inválida.");
						break;
					}

					titulo = input("Título:", categoria.getTitulo());
					print(this.atualizaCategoria(categoria, titulo));

				} catch (Exception exception) {
					print("ID inválido.");
				}
				break;

			case "DEL":
				try {
					Long id = Long.valueOf(input("ID da categoria:"));
					Categoria categoria = categoriaDAO.buscaCategoriaPorId(id);

					if (categoria == null) {
						print("Categoria inválida.");
						break;
					}

					String input = input(categoria.toString() + "Excluir esta categoria? [Y/n]");
					print(this.deletaCategoria(categoria, input));

				} catch (Exception exception) {
					print("ID inválido.");
				}
				break;

			case "S":
				return;

			default:
				print("Opção inválida, Sherlock Holmes!");
			}

			qtde_categorias = (long) banco.getCategorias().size();
			System.out.println(qtde_categorias);
		}
	}

	public String adicionaCategoria(String titulo) {
		Categoria categoria = new Categoria();
		categoria.setTitulo(titulo);

		return categoriaDAO.adicionaCategoria(categoria) ? "A categoria foi adicionada com êxito."
				: "A categoria não foi adicionada.";
	}

	public String buscaCategoriaPorId(Long id) {
		Categoria categoria = categoriaDAO.buscaCategoriaPorId(id);

		return categoria != null ? categoria.toString() : "A categoria não foi encontrada.";
	}

	public String listaCategorias() {
		return banco.getCategorias().size() == 0 ? "Nenhuma categoria foi encontrada." : categoriaDAO.listaCategorias();
	}

	public String atualizaCategoria(Categoria categoria, String titulo) {
		categoria.setTitulo(titulo);

		return categoriaDAO.atualizaCategoria(categoria) ? "A categoria foi atualizada com êxito."
				: "A categoria não foi atualizada.";
	}

	public String deletaCategoria(Categoria categoria, String input) {
		return categoriaDAO.deletaCategoria(categoria) && (input.equals("") || input.toUpperCase().equals("Y"))
				? "A categoria foi deletada com êxito."
				: "A categoria não foi deletada.";
	}
}

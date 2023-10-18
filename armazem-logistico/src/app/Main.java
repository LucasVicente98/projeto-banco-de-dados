package app;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Armazem;
import model.ArmazemDAO;
import model.Produto;
import model.ProdutoDAO;
import model.Responsavel;
import model.ResponsavelArmazemDTO;
import model.ResponsavelDAO;

public class Main {
	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		Scanner scanner = new Scanner(System.in);

		ArmazemDAO armazemDAO = new ArmazemDAO();
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();

		int countArmazens = armazemDAO.countArmazens();
		int countResponsaveis = responsavelDAO.countResponsaveis();
		int countProdutos = produtoDAO.countProdutos();

		int mainChoice;

		do {
			System.out.println("#######################################################");
			System.out.println("#   Sistema de Gerenciamento de Armazéns Logísticos   #");
			System.out.println("#######################################################\n");

			System.out.println("###################################");
			System.out.println("#  TOTAL DE REGISTROS EXISTENTES  #");
			System.out.println("#Número de Armazéns: " + countArmazens + "            #");
			System.out.println("#Número de Responsáveis: " + countResponsaveis + "        #");
			System.out.println("#Número de Produtos: " + countProdutos + "           #");
			System.out.println("###################################\n");
			
			System.out.println("*********************************************");
			System.out.println("*    Criado por: LUCAS LEITE VICENTE        *");
			System.out.println("*                                           *");
			System.out.println("*    DISCIPLINA: BANCO DE DADOS - 2023/02   *");
			System.out.println("*    PROFESSOR: HOWARD ROATTI               *");
			System.out.println("*********************************************\n");
			
			System.out.println("********************");
			System.out.println("*  MENU PRINCIPAL  *");
			System.out.println("********************");
			System.out.println("1. Armazem");
			System.out.println("2. Responsavel");
			System.out.println("3. Produto");
			System.out.println("4. Sair\n");

			System.out.print("Escolha uma opção: ");

			mainChoice = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			switch (mainChoice) {
			case 1:
				menuArmazem(scanner);
				break;
			case 2:
				menuResponsavel(scanner);
				break;
			case 3:
				menuProduto(scanner);
				break;
			case 4:
				System.out.println("Saindo do programa.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.\n");
			}

		} while (mainChoice != 4);
	}

	private static void menuArmazem(Scanner scanner) {
		ArmazemDAO armazemDAO = new ArmazemDAO();
		int armazemChoice;

		do {
			System.out.println("\nMenu Armazem:");
			System.out.println("1. Cadastrar Armazem");
			System.out.println("2. Listar Armazens");
			System.out.println("3. Atualizar Armazem");
			System.out.println("4. Excluir Armazem");
			System.out.println("5. Vincular Responsavel ao Armazem");
			System.out.println("6. Voltar ao Menu Principal\n");

			System.out.print("Escolha uma opção: ");
			armazemChoice = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			switch (armazemChoice) {
			case 1:
				cadastrarArmazem(scanner, armazemDAO);
				break;
			case 2:
				listarArmazens(armazemDAO);
				break;
			case 3:
				atualizarArmazem(scanner, armazemDAO);
				break;
			case 4:
				excluirArmazem(scanner, armazemDAO);
				break;
			case 5:
				vincularResponsavelAoArmazem(scanner, armazemDAO);
				break;
			case 6:
				System.out.println("Voltando ao Menu Principal.\n");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.\n");
			}

		} while (armazemChoice != 6);
	}

	private static void menuResponsavel(Scanner scanner) {
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		int responsavelChoice;

		do {
			System.out.println("\nMenu Responsavel:");
			System.out.println("1. Cadastrar Responsavel");
			System.out.println("2. Listar Responsaveis");
			System.out.println("3. Listar Armazens por Responsável");
			System.out.println("4. Atualizar Responsavel");
			System.out.println("5. Excluir Responsavel");
			System.out.println("6. Voltar ao Menu Principal\n");

			System.out.print("Escolha uma opção: ");
			responsavelChoice = scanner.nextInt();
			scanner.nextLine();

			switch (responsavelChoice) {
			case 1:
				cadastrarResponsavel(scanner, responsavelDAO);
				break;
			case 2:
				listarResponsaveis(responsavelDAO);
				break;
			case 3:
				exibirConsultaResponsaveisComArmazens(responsavelDAO);
				break;
			case 4:
				atualizarResponsavel(scanner, responsavelDAO);
				break;
			case 5:
				excluirResponsavel(scanner, responsavelDAO);
				break;
			case 6:
				System.out.println("Voltando ao Menu Principal.\n");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.\n");
			}

		} while (responsavelChoice != 6);
	}

	private static void menuProduto(Scanner scanner) {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArmazemDAO armazemDAO = new ArmazemDAO();

		int produtoChoice;

		do {
			System.out.println("\nMenu Produto:");
			System.out.println("1. Cadastrar Produto");
			System.out.println("2. Listar Produtos");
			System.out.println("3. Atualizar Produto");
			System.out.println("4. Excluir Produto");
			System.out.println("5. Consultar valor total dos produtos por Armazém");
			System.out.println("6. Voltar ao Menu Principal\n");

			System.out.print("Escolha uma opção: ");
			produtoChoice = scanner.nextInt();
			scanner.nextLine();

			switch (produtoChoice) {
			case 1:
				cadastrarProduto(scanner, produtoDAO, armazemDAO);
				break;
			case 2:
				listarProdutos(produtoDAO);
				break;
			case 3:
				atualizarProduto(scanner, produtoDAO, armazemDAO);
				break;
			case 4:
				excluirProduto(scanner, produtoDAO);
				break;
			case 5:
				exibirConsulta(produtoDAO, armazemDAO);
				break;
			case 6:
				System.out.println("Voltando ao Menu Principal.\n");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.\n");
			}

		} while (produtoChoice != 6);
	}

	private static void cadastrarArmazem(Scanner scanner, ArmazemDAO armazemDAO) {
		System.out.print("Nome do Armazem: ");
		String nome = scanner.nextLine();

		System.out.print("Localizacao do Armazem: ");
		String localizacao = scanner.nextLine();

		System.out.print("Capacidade do Armazem: ");
		int capacidade = scanner.nextInt();
		scanner.nextLine();

		Armazem armazem = new Armazem();
		armazem.setNome(nome);
		armazem.setLocalizacao(localizacao);
		armazem.setCapacidade(capacidade);

		armazemDAO.create(armazem);
		System.out.println("Armazem cadastrado com sucesso!\n");
	}

	private static void listarArmazens(ArmazemDAO armazemDAO) {
		List<Armazem> armazens = armazemDAO.getAll();
		System.out.println("Lista de Armazens:");
		for (Armazem armazem : armazens) {
			System.out.println("ID: " + armazem.getArmazem_id() + ", Nome: " + armazem.getNome());
		}
	}

	private static void atualizarArmazem(Scanner scanner, ArmazemDAO armazemDAO) {
		System.out.print("ID do Armazem a ser atualizado: ");
		int armazemId = scanner.nextInt();
		scanner.nextLine();

		Armazem armazem = armazemDAO.getById(armazemId);

		if (armazem != null) {
			System.out.print("Novo Nome do Armazem: ");
			String nome = scanner.nextLine();

			System.out.print("Nova Localizacao do Armazem: ");
			String localizacao = scanner.nextLine();

			System.out.print("Nova Capacidade do Armazem: ");
			int capacidade = scanner.nextInt();
			scanner.nextLine();

			armazem.setNome(nome);
			armazem.setLocalizacao(localizacao);
			armazem.setCapacidade(capacidade);

			armazemDAO.update(armazem);
			System.out.println("Armazem atualizado com sucesso!\n");
		} else {
			System.out.println("Armazem não encontrado.\n");
		}
	}

	private static void excluirArmazem(Scanner scanner, ArmazemDAO armazemDAO) {
		System.out.print("ID do Armazem a ser excluído: ");
		int armazemId = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer

		Armazem armazem = armazemDAO.getById(armazemId);

		if (armazem != null) {
			armazemDAO.delete(armazem.getArmazem_id());
			System.out.println("Armazem excluído com sucesso!\n");
		} else {
			System.out.println("Armazem não encontrado.\n");
		}
	}

	private static void vincularResponsavelAoArmazem(Scanner scanner, ArmazemDAO armazemDAO) {
		System.out.print("ID do Armazem para vincular responsável: ");
		int armazemId = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer

		System.out.print("ID do Responsavel para vincular ao Armazem: ");
		int responsavelId = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer

		armazemDAO.vincularResponsavel(armazemId, responsavelId);
		System.out.println("Responsavel vinculado ao Armazem com sucesso!\n");
	}

	private static void cadastrarResponsavel(Scanner scanner, ResponsavelDAO responsavelDAO) {
		System.out.print("Nome do Responsavel: ");
		String nome = scanner.nextLine();

		System.out.print("Telefone do Responsavel: ");
		String telefone = scanner.nextLine();

		Responsavel responsavel = new Responsavel();
		responsavel.setNome(nome);
		responsavel.setTelefone(telefone);

		responsavelDAO.create(responsavel);
		System.out.println("Responsavel cadastrado com sucesso!\n");
	}

	private static void listarResponsaveis(ResponsavelDAO responsavelDAO) {
		List<Responsavel> responsaveis = responsavelDAO.getAll();
		System.out.println("Lista de Responsaveis:");
		for (Responsavel responsavel : responsaveis) {
			System.out.println("ID: " + responsavel.getResponsavel_id() + ", Nome: " + responsavel.getNome());
		}
	}

	private static void exibirConsultaResponsaveisComArmazens(ResponsavelDAO responsavelDAO) {
		List<ResponsavelArmazemDTO> responsaveisComArmazens = responsavelDAO.getResponsaveisComArmazens();
		System.out.println("\nConsulta Responsáveis com Armazéns:\n");

		for (ResponsavelArmazemDTO dto : responsaveisComArmazens) {
			System.out.println("ID do Responsável: " + dto.getResponsavelId());
			System.out.println("Nome do Responsável: " + dto.getNomeResponsavel());
			System.out.println("Telefone do Responsável: " + dto.getTelefoneResponsavel());
			System.out.println("Nomes dos Armazéns: " + dto.getNomesArmazens());
			System.out.println("------------------------------");
		}
	}

	private static void atualizarResponsavel(Scanner scanner, ResponsavelDAO responsavelDAO) {
		System.out.print("ID do Responsavel a ser atualizado: ");
		int responsavelId = scanner.nextInt();
		scanner.nextLine();

		Responsavel responsavel = responsavelDAO.getById(responsavelId);

		if (responsavel != null) {
			System.out.print("Novo Nome do Responsavel: ");
			String nome = scanner.nextLine();

			System.out.print("Novo Telefone do Responsavel: ");
			String telefone = scanner.nextLine();

			responsavel.setNome(nome);
			responsavel.setTelefone(telefone);

			responsavelDAO.update(responsavel);
			System.out.println("Responsavel atualizado com sucesso!\n");
		} else {
			System.out.println("Responsavel não encontrado.\n");
		}
	}

	private static void excluirResponsavel(Scanner scanner, ResponsavelDAO responsavelDAO) {
		System.out.print("ID do Responsavel a ser excluído: ");
		int responsavelId = scanner.nextInt();
		scanner.nextLine();

		Responsavel responsavel = responsavelDAO.getById(responsavelId);

		if (responsavel != null) {
			responsavelDAO.delete(responsavel.getResponsavel_id());
			System.out.println("Responsavel excluído com sucesso!\n");
		} else {
			System.out.println("Responsavel não encontrado.\n");
		}
	}

	private static void cadastrarProduto(Scanner scanner, ProdutoDAO produtoDAO, ArmazemDAO armazemDAO) {
		System.out.print("Nome do Produto: ");
		String nome = scanner.nextLine();

		System.out.print("Quantidade do Produto: ");
		int quantidade = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Valor unitário do Produto: ");
		double valorUnitario = scanner.nextDouble();
		scanner.nextLine();

		System.out.print("ID do Armazem para associar o Produto: ");
		int armazemId = scanner.nextInt();
		scanner.nextLine();

		Armazem armazem = armazemDAO.getById(armazemId);

		if (armazem != null) {
			Produto produto = new Produto();
			produto.setNome(nome);
			produto.setQuantidade(quantidade);
			produto.setValorUnitario(valorUnitario);
			produto.setArmazem(armazem);

			produtoDAO.create(produto);
			System.out.println("Produto cadastrado com sucesso!\n");
		} else {
			System.out.println("Armazem não encontrado.\n");
		}
	}

	private static void listarProdutos(ProdutoDAO produtoDAO) {
		List<Produto> produtos = produtoDAO.getAll();
		System.out.println("Lista de Produtos:");
		for (Produto produto : produtos) {
			System.out.println("ID: " + produto.getProduto_id() + ", Nome: " + produto.getNome() + ", Quantidade: "
					+ produto.getQuantidade() + ", Valor Unitário: R$" + produto.getValorUnitario()
					+ ", Local de armazenamento: " + produto.getArmazem().getNome());
		}
	}

	private static void exibirConsulta(ProdutoDAO produtoDAO, ArmazemDAO armazemDAO) {
		System.out.println("\nConsulta:\n");
		produtoDAO.exibirValorTotalPorArmazem();
	}

	private static void atualizarProduto(Scanner scanner, ProdutoDAO produtoDAO, ArmazemDAO armazemDAO) {
		System.out.print("ID do Produto a ser atualizado: ");
		int produtoId = scanner.nextInt();
		scanner.nextLine();

		Produto produto = produtoDAO.getById(produtoId);

		if (produto != null) {
			System.out.print("Novo Nome do Produto: ");
			String nome = scanner.nextLine();

			System.out.print("Nova Quantidade do Produto: ");
			int quantidade = scanner.nextInt();
			scanner.nextLine();

			System.out.print("Novo valo unitário do Produto: ");
			double valorUnitario = scanner.nextDouble();
			scanner.nextLine();

			System.out.print("Novo ID do Armazem para associar o Produto: ");
			int armazemId = scanner.nextInt();
			scanner.nextLine();

			Armazem armazem = armazemDAO.getById(armazemId);

			if (armazem != null) {
				produto.setNome(nome);
				produto.setQuantidade(quantidade);
				produto.setValorUnitario(valorUnitario);
				produto.setArmazem(armazem);

				produtoDAO.update(produto);
				System.out.println("Produto atualizado com sucesso!\n");
			} else {
				System.out.println("Armazem não encontrado.\n");
			}
		} else {
			System.out.println("Produto não encontrado.\n");
		}
	}

	private static void excluirProduto(Scanner scanner, ProdutoDAO produtoDAO) {
		System.out.print("ID do Produto a ser excluído: ");
		int produtoId = scanner.nextInt();
		scanner.nextLine();

		Produto produto = produtoDAO.getById(produtoId);

		if (produto != null) {
			produtoDAO.delete(produto.getProduto_id());
			System.out.println("Produto excluído com sucesso!\n");
		} else {
			System.out.println("Produto não encontrado.\n");
		}
	}

}

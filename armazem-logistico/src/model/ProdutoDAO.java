package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.BaseDAO;
import connection.FactoryDAO;

public class ProdutoDAO extends BaseDAO implements FactoryDAO<Produto> {

	private static final Logger logger = Logger.getLogger(ProdutoDAO.class.getName());

	@Override
	public Produto getById(int id) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM produto WHERE produto_id = ?")) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return mapResultSetToProduto(resultSet);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao obter o produto por ID", e);
		}

		return null;
	}

	@Override
	public List<Produto> getAll() {
		List<Produto> produtos = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM produto order by produto_id");
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				Produto produto = mapResultSetToProduto(resultSet);
				produtos.add(produto);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao obter todos os produtos", e);
		}

		return produtos;
	}

	@Override
	public void create(Produto entity) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO produto(produto_id, nome, quantidade, valor_unitario, armazem_id) VALUES (produto_seq.NEXTVAL, ?, ?, ?, ?)")) {

			statement.setString(1, entity.getNome());
			statement.setInt(2, entity.getQuantidade());
			statement.setDouble(3, entity.getValorUnitario());
			statement.setInt(4, entity.getArmazem().getArmazem_id());

			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao salvar o produto", e);
		}
	}

	@Override
	public void update(Produto entity) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE produto SET nome = ?, quantidade = ?, valor_unitario = ? WHERE produto_id = ?")) {

			statement.setString(1, entity.getNome());
			statement.setInt(2, entity.getQuantidade());
			statement.setDouble(3, entity.getValorUnitario());
			statement.setInt(4, entity.getProduto_id());

			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao atualizar o produto", e);
		}
	}

	@Override
	public void delete(int id) {
		try (Connection connection = getConnection()) {
			// Verificar se o produto está associado a um armazém
			Produto produto = getById(id);
			if (produto != null && produto.getArmazem() != null) {
				// Decidir se quer excluir o produto associado ao armazém ou apenas remover a
				// referência do armazém
				// Neste exemplo, estamos apenas removendo a referência do armazém
				produto.setArmazem(null);
				update(produto);
			}

			// Finalmente, excluir o produto
			try (PreparedStatement statement = connection
					.prepareStatement("DELETE FROM produto WHERE produto_id = ?")) {
				statement.setInt(1, id);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao excluir o produto", e);
		}
	}

	private Produto mapResultSetToProduto(ResultSet resultSet) throws SQLException {
		Produto produto = new Produto();
		produto.setProduto_id(resultSet.getInt("produto_id"));
		produto.setNome(resultSet.getString("nome"));
		produto.setQuantidade(resultSet.getInt("quantidade"));
		produto.setValorUnitario(resultSet.getDouble("valor_unitario"));

		ArmazemDAO armazemDAO = new ArmazemDAO();
		produto.setArmazem(armazemDAO.getById(resultSet.getInt("armazem_id")));
		return produto;
	}

	public int countProdutos() {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT COUNT(1) FROM produto");
				ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao contar o número de produtos", e);
		}
		return 0;
	}

	public void exibirValorTotalPorArmazem() {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT A.ARMAZEM_ID, "
						+ "LISTAGG(P.NOME, ', ') WITHIN GROUP (ORDER BY P.NOME) AS \"ITENS\", "
						+ "'R$ ' || TO_CHAR(SUM(P.QUANTIDADE * P.VALOR_UNITARIO), '999,999,999.99') AS \"VALOR TOTAL DOS ITENS\" "
						+ "FROM PRODUTO P " + "INNER JOIN ARMAZEM A ON P.ARMAZEM_ID = A.ARMAZEM_ID "
						+ "GROUP BY A.ARMAZEM_ID")) {

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int armazemId = resultSet.getInt("ARMAZEM_ID");
					String itens = resultSet.getString("ITENS");
					String valorTotal = resultSet.getString("VALOR TOTAL DOS ITENS");

					System.out.println("ARMAZEM_ID: " + armazemId);
					System.out.println("ITENS: " + itens);
					System.out.println("VALOR TOTAL DOS ITENS: " + valorTotal);
					System.out.println();
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao executar a consulta", e);
		}
	}

}

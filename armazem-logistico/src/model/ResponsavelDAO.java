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

public class ResponsavelDAO extends BaseDAO implements FactoryDAO<Responsavel> {

	private static final Logger logger = Logger.getLogger(ResponsavelDAO.class.getName());

	@Override
	public Responsavel getById(int id) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM responsavel WHERE responsavel_id = ?")) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return mapResultSetToResponsavel(resultSet);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao obter o responsável por ID", e);
		}

		return null;
	}

	@Override
	public List<Responsavel> getAll() {
		List<Responsavel> responsaveis = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM responsavel order by responsavel_id");
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				Responsavel responsavel = mapResultSetToResponsavel(resultSet);
				responsaveis.add(responsavel);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao obter todos os responsáveis", e);
		}

		return responsaveis;
	}

	public List<ResponsavelArmazemDTO> getResponsaveisComArmazens() {
		List<ResponsavelArmazemDTO> responsaveisComArmazens = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT R.RESPONSAVEL_ID, R.NOME AS \"RESPONSAVEL\", R.TELEFONE, "
								+ "LISTAGG(A.NOME, ', ') WITHIN GROUP (ORDER BY A.NOME) AS \"ARMAZENS\" "
								+ "FROM RESPONSAVEL R " + "INNER JOIN ARMAZEM A ON A.RESPONSAVEL_ID = R.RESPONSAVEL_ID "
								+ "GROUP BY R.RESPONSAVEL_ID, R.NOME, R.TELEFONE");
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				ResponsavelArmazemDTO dto = new ResponsavelArmazemDTO();
				dto.setResponsavelId(resultSet.getInt("RESPONSAVEL_ID"));
				dto.setNomeResponsavel(resultSet.getString("RESPONSAVEL"));
				dto.setTelefoneResponsavel(resultSet.getString("TELEFONE"));
				dto.setNomesArmazens(resultSet.getString("ARMAZENS"));
				responsaveisComArmazens.add(dto);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao obter responsáveis com armazéns", e);
		}

		return responsaveisComArmazens;
	}

	@Override
	public void create(Responsavel entity) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO responsavel(responsavel_id, nome, telefone) VALUES (responsavel_seq.NEXTVAL, ?, ?)")) {

			statement.setString(1, entity.getNome());
			statement.setString(2, entity.getTelefone());

			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao salvar o responsável", e);
		}
	}

	@Override
	public void update(Responsavel entity) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE responsavel SET nome = ?, telefone = ? WHERE responsavel_id = ?")) {

			statement.setString(1, entity.getNome());
			statement.setString(2, entity.getTelefone());
			statement.setInt(3, entity.getResponsavel_id());

			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao atualizar o responsável", e);
		}
	}

	@Override
	public void delete(int id) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM responsavel WHERE responsavel_id = ?")) {

			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao excluir o responsável", e);
		}
	}

	private Responsavel mapResultSetToResponsavel(ResultSet resultSet) throws SQLException {
		Responsavel responsavel = new Responsavel();
		responsavel.setResponsavel_id(resultSet.getInt("responsavel_id"));
		responsavel.setNome(resultSet.getString("nome"));
		responsavel.setTelefone(resultSet.getString("telefone"));
		return responsavel;
	}

	public int countResponsaveis() {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT COUNT(1) FROM responsavel");
				ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao contar o número de responsáveis", e);
		}
		return 0;
	}
}

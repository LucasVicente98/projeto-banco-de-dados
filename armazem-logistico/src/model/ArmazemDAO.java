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

public class ArmazemDAO extends BaseDAO implements FactoryDAO<Armazem> {

	private static final Logger logger = Logger.getLogger(ArmazemDAO.class.getName());

	@Override
	public Armazem getById(int id) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM armazem WHERE armazem_id = ?")) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return mapResultSetToArmazem(resultSet);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao obter o armazém por ID", e);
		}

		return null;
	}

	@Override
	public List<Armazem> getAll() {
		List<Armazem> armazens = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM armazem order by armazem_id");
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				Armazem armazem = mapResultSetToArmazem(resultSet);
				armazens.add(armazem);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao obter todos os armazéns", e);
		}

		return armazens;
	}

	@Override
	public void create(Armazem entity) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO armazem(armazem_id, nome, localizacao, capacidade) VALUES (armazem_seq.NEXTVAL, ?, ?, ?)")) {

			statement.setString(1, entity.getNome());
			statement.setString(2, entity.getLocalizacao());
			statement.setInt(3, entity.getCapacidade());

			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao salvar o armazém", e);
		}
	}

	@Override
	public void update(Armazem entity) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE armazem SET nome = ?, localizacao = ?, capacidade = ? WHERE armazem_id = ?")) {

			statement.setString(1, entity.getNome());
			statement.setString(2, entity.getLocalizacao());
			statement.setInt(3, entity.getCapacidade());
			statement.setInt(4, entity.getArmazem_id());

			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao atualizar o armazém", e);
		}
	}

	@Override
	public void delete(int id) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM armazem WHERE armazem_id = ?")) {

			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao excluir o armazém", e);
		}
	}

	public void vincularResponsavel(int armazemId, int responsavelId) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE ARMAZEM SET responsavel_id = ? WHERE armazem_id = ?")) {

			statement.setInt(1, responsavelId);
			statement.setInt(2, armazemId);

			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao vincular responsável ao armazém", e);
		}
	}

	private Armazem mapResultSetToArmazem(ResultSet resultSet) throws SQLException {
		Armazem armazem = new Armazem();
		armazem.setArmazem_id(resultSet.getInt("armazem_id"));
		armazem.setNome(resultSet.getString("nome"));
		return armazem;
	}

	public int countArmazens() {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT COUNT(1) FROM armazem");
				ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao contar o número de armazéns", e);
		}
		return 0;
	}
}

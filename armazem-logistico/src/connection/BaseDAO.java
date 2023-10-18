package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

	protected Connection getConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "usuario", "senha");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao carregar o driver do banco de dados.");
		}
	}
}
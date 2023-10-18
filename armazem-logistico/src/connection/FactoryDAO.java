package connection;

import java.util.List;

public interface FactoryDAO<T> {

	T getById(int id);

	List<T> getAll();

	void create(T entity);

	void update(T entity);

	void delete(int id);

}

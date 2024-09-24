package mapper;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseMapper<T> {
  protected Connection connection;

  public BaseMapper(Connection connection) {
    this.connection = connection;
  }

  public abstract void create(T entity) throws SQLException;

  public abstract T findById(Long id) throws SQLException;

//  public abstract void update(T entity) throws SQLException;
//
//  public abstract void delete(int id) throws SQLException;

  protected Connection getConnection() {
    return connection;
  }

}

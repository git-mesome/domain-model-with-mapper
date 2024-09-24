package mapper;


import domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper extends BaseMapper<Product> {

  public ProductMapper(Connection connection) {
    super(connection);

  }

  @Override
  public void create(Product newProduct) throws SQLException {
    String sql = "INSERT INTO products (id, name, price) VALUES (?,?,?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setLong(1, newProduct.getId());
      stmt.setString(2, newProduct.getName());
      stmt.setBigDecimal(3, newProduct.getPrice());
      stmt.executeUpdate();
    }
  }

  @Override
  public Product findById(Long id) throws SQLException {
    String sql = "SELECT * FROM products WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return new Product(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getDouble("price"));
      }
      return null;
    }
  }
}

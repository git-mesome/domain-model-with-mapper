package mapper;


import domain.Order;
import domain.Product;


import java.sql.*;

public class OrderMapper extends BaseMapper<Order> {

  public OrderMapper(Connection connection) {
    super(connection);
  }

  @Override
  public void create(Order order) throws SQLException {
    String sql = "INSERT INTO orders (id, user_id, price) VALUES (?,?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      statement.setLong(1, order.getId());
      statement.setLong(2, order.getUser().getId());
      statement.setBigDecimal(3, order.getTotalPrice());
      statement.executeUpdate();

      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        Long orderId = generatedKeys.getLong(1);
        for (Product product : order.getProducts()) {
          addProductToOrder(orderId, product.getId());
        }
      }
    }
  }


  private void addProductToOrder(Long orderId, Long productId) throws SQLException {
    String sql = "INSERT INTO orders (id, product_id) VALUES (?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setLong(1, orderId);
      statement.setLong(2, productId);
      statement.executeUpdate();
    }
  }


  @Override
  public Order findById(Long id) throws SQLException {
    return null;
  }

}

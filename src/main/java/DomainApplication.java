

import domain.Order;
import domain.Product;
import domain.User;
import mapper.OrderMapper;
import mapper.ProductMapper;
import mapper.UserMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DomainApplication {
  public static void main(String[] args) {
    try (Connection connection = DB.connect()) {
      if (connection == null) {
        System.out.println("Database connection failed");
        return;
      }

      // UserMapper
      UserMapper userMapper = new UserMapper(connection);
      User newUser = new User(1L, "test", "test@test.com");
      userMapper.create(newUser);
      User retrievedUser = userMapper.findById(1L); // ID가 1인 사용자 조회
      System.out.println("사용자 조회: " + retrievedUser);

      // 식별자 맵 테스트
      User retrievedUser2 = userMapper.findById(1L); // 캐시에서 조회
      System.out.println("사용자 조회 (두 번째): " + retrievedUser2);

      // ProductMapper
      ProductMapper productMapper = new ProductMapper(connection);
      Product newProduct = new Product(1L, "Laptop", 1200.00);
      productMapper.create(newProduct);
      Product retrievedProduct = productMapper.findById(1L); // ID가 1인 제품 조회
      System.out.println("상품 조회: " + retrievedProduct);

      // OrderMapper
      OrderMapper orderMapper = new OrderMapper(connection);
      Order newOrder = new Order(1L, retrievedUser, List.of(retrievedProduct));
      orderMapper.create(newOrder);
      System.out.println("주문 조회: " + newOrder.toString());

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

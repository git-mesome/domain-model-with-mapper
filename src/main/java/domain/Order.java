package domain;

import java.math.BigDecimal;
import java.util.List;

public class Order {

  private final Long id;
  private final BigDecimal totalPrice;
  private final User user;
  private final List<Product> products;


  public Order(Long id, User user, List<Product> products) {
    this.id = id;
    this.user = user;
    this.products = products;
    this.totalPrice = calculateTotalPrice();
  }


  private BigDecimal calculateTotalPrice() {
    return products.stream()
        .map(Product::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }
  public List<Product> getProducts() {
    return products;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", user=" + user +
        ", products=" + products +
        ", totalPrice=" + totalPrice +
        '}';
  }
}

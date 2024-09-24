package domain;

import java.math.BigDecimal;

public class Product {
  private final Long id;
  private final String name;
  private final BigDecimal price ;

  public Product(Long id, String name, double price) {
    this.id = id;
    this.name = name;
    this.price = BigDecimal.valueOf(price);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        '}';
  }
}

package domain;

public class User {

  private final Long id;
  private final String name;
  private final String email;

  public User(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
  }


}

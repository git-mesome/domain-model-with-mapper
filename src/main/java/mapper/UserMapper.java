package mapper;


import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserMapper extends BaseMapper<User> {

  private Map<Long, User> identityMap = new HashMap<>();

  public UserMapper(Connection connection) {
    super(connection);
  }

  @Override
  public void create(User user) throws SQLException {
    String sql = "INSERT INTO users (id, name, email) VALUES (?,?, ?)";
    try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
      stmt.setLong(1, user.getId());
      stmt.setString(2, user.getName());
      stmt.setString(3, user.getEmail());
      stmt.executeUpdate();
// 캐시 O : 검색 시 캐시에서 찾아야함 : 데이터베이스 접근 No
// 캐시 X : 초기 생성 후 데이터베이스 접근해서 찾아야 함
//      identityMap.put(user.getId(), user);
      System.out.println("사용자 생성 및 캐시에 추가됨: " + user);
    }
  }

  @Override
  public User findById(Long id) throws SQLException {

    if (identityMap.containsKey(id)) {
      System.out.println("캐시에서 사용자 조회: " + id);
      return identityMap.get(id);
    }

    String sql = "SELECT * FROM users WHERE id = ?";
    try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
      stmt.setLong(1, id);
      ResultSet resultSet = stmt.executeQuery();
      if (resultSet.next()) {
        User user = new User(resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getString("email"));
        identityMap.put(id, user);
        System.out.println("데이터베이스에서 사용자 조회 및 캐시에 추가: " + user);
        return user;
      }

      return null;
    }
  }

}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
  public static Connection connect() throws SQLException {

    try {
      String url = "jdbc:postgresql://localhost:54324/eaa";
      String user = "minseo";
      String password = "minseo";

      return DriverManager.getConnection(url, user, password);

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
}

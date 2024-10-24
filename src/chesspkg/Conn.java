package chesspkg;

import java.sql.*;

public class Conn {
  Connection conn;
  Statement stmt;

  public Conn() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chess", "root", "shaan123");
      stmt = conn.createStatement();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

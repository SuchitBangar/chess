package chesspkg;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

public class Register extends JFrame implements ActionListener {

  JTextField tUsername;
  JPasswordField tPassword;
  JButton login, register, exit;

  Register() {

    JLabel username = new JLabel("Username");
    username.setBounds(350, 100, 100, 20);
    add(username);

    JLabel password = new JLabel("Password");
    password.setBounds(350, 150, 100, 20);
    add(password);

    tUsername = new JTextField();
    tUsername.setBounds(450, 100, 100, 20);
    add(tUsername);

    tPassword = new JPasswordField();
    tPassword.setBounds(450, 150, 100, 20);
    add(tPassword);

    register = new JButton("Register");
    register.setBounds(400, 200, 90, 20);
    register.setBackground(Color.black);
    register.setForeground(Color.white);
    register.addActionListener(this);
    add(register);

    exit = new JButton("Exit");
    exit.setBounds(400, 230, 90, 20);
    exit.setBackground(Color.black);
    exit.setForeground(Color.white);
    exit.addActionListener(this);
    add(exit);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/chess.png"));
    Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel img = new JLabel(i3);
    img.setBounds(0, 100, 300, 300);
    add(img);

    setTitle("Register");
    setSize(600, 450);
    setLayout(null);
    setLocation(450, 200);
    setBackground(Color.white);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == register) {
      try {
        String username = tUsername.getText();
        String password = tPassword.getText();

        Conn conn = new Conn();

        String checkQuery = "SELECT * FROM login WHERE username = '" + username + "'";
        ResultSet resSet = conn.stmt.executeQuery(checkQuery);

        if (resSet.next()) {
          JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different username.");
        } else {
          String insertQuery = "INSERT INTO login (username, password) VALUES ('" + username + "', '" + password + "')";
          int rowsAffected = conn.stmt.executeUpdate(insertQuery);

          if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Registered successfully. Please login.");
            setVisible(false);
            new Login();
          } else {
            JOptionPane.showMessageDialog(null, "Registration failed. Please try again.");
          }
        }

      } catch (Exception E) {
        E.printStackTrace();
      }
    } else if (e.getSource() == exit) {
      System.exit(0);
    }
  }

  public static void main(String[] args) {
    new Register();
  }
}

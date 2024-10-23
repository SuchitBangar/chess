package chesspkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ChessGame extends JFrame implements ActionListener {
  private JButton[][] squares = new JButton[8][8];
  private Board board = new Board();
  private boolean whiteTurn = true; // White moves first
  private int startX = -1, startY = -1; // For selecting piece to move
  private Font fontAwesome;

  public ChessGame() {
    loadFontAwesome();

    setTitle("Chess Game");
    setSize(600, 600);
    setLayout(new GridLayout(8, 8));

    initializeBoardGUI();

    setLocation(450, 200);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  private void loadFontAwesome() {
    try {
      fontAwesome = Font.createFont(Font.TRUETYPE_FONT, new File("src/FontAwesome.otf")).deriveFont(36f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(fontAwesome);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace(); // Check for errors in loading the font
    }
  }

  private void initializeBoardGUI() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        JButton button = new JButton();
        button.setFont(fontAwesome);
        button.setOpaque(true);
        button.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
        button.addActionListener(this);
        squares[row][col] = button;
        add(button);

        updateSquareIcon(row, col); // Place initial pieces
      }
    }
  }

  private void updateSquareIcon(int row, int col) {
    ChessPiece piece = board.getPiece(row, col);
    if (piece != null) {
      squares[row][col].setText(piece.getIcon());
    } else {
      squares[row][col].setText("");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton clickedButton = (JButton) e.getSource();

    // Find the coordinates of the clicked button
    int row = -1, col = -1;
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        if (squares[r][c] == clickedButton) {
          row = r;
          col = c;
          break;
        }
      }
    }

    // First click (select a piece)
    if (startX == -1) {
      ChessPiece piece = board.getPiece(row, col);
      if (piece != null && piece.isWhite() == whiteTurn) {
        startX = row;
        startY = col;
      }
    } else {
      // Second click (move the piece)
      if (board.movePiece(startX, startY, row, col)) {
        whiteTurn = !whiteTurn; // Switch turns
        updateSquareIcon(startX, startY);
        updateSquareIcon(row, col);
      }
      startX = -1; // Reset selection
      startY = -1;
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(ChessGame::new);
  }
}

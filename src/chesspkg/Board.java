package chesspkg;

public class Board {
  private ChessPiece[][] pieces;

  public Board() {
    pieces = new ChessPiece[8][8];
    setupInitialPosition();
  }

  private void setupInitialPosition() {
    for (int i = 0; i < 8; i++) {
      pieces[1][i] = new ChessPiece("pawn", false); // Black pawns
      pieces[6][i] = new ChessPiece("pawn", true); // White pawns
    }
    pieces[0][0] = pieces[0][7] = new ChessPiece("rook", false);
    pieces[7][0] = pieces[7][7] = new ChessPiece("rook", true);
    pieces[0][1] = pieces[0][6] = new ChessPiece("knight", false);
    pieces[7][1] = pieces[7][6] = new ChessPiece("knight", true);
    pieces[0][2] = pieces[0][5] = new ChessPiece("bishop", false);
    pieces[7][2] = pieces[7][5] = new ChessPiece("bishop", true);
    pieces[0][3] = new ChessPiece("queen", false);
    pieces[7][3] = new ChessPiece("queen", true);
    pieces[0][4] = new ChessPiece("king", false);
    pieces[7][4] = new ChessPiece("king", true);
  }

  public ChessPiece getPiece(int row, int col) {
    return pieces[row][col];
  }

  public boolean movePiece(int startX, int startY, int endX, int endY) {
    // For simplicity, no validation is done here
    ChessPiece piece = getPiece(startX, startY);
    if (piece != null) {
      pieces[endX][endY] = piece;
      pieces[startX][startY] = null;
      return true;
    }
    return false;
  }
}

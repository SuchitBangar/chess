public class ChessPiece {
    private String type; // "pawn", "rook", "knight", "bishop", "queen", "king"
    private boolean isWhite;

    public ChessPiece(String type, boolean isWhite) {
        this.type = type;
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public String getIcon() {
        if (isWhite) {
            switch (type) {
                case "pawn":
                    return "\u265F"; // White pawn
                case "rook":
                    return "\u265C"; // White rook
                case "knight":
                    return "\u265E"; // White knight
                case "bishop":
                    return "\u265D"; // White bishop
                case "queen":
                    return "\u265B"; // White queen
                case "king":
                    return "\u265A"; // White king
                default:
                    return "";
            }
        } else {
            switch (type) {
                case "pawn":
                    return "\u2659"; // Black pawn
                case "rook":
                    return "\u2656"; // Black rook
                case "knight":
                    return "\u2658"; // Black knight
                case "bishop":
                    return "\u2657"; // Black bishop
                case "queen":
                    return "\u2655"; // Black queen
                case "king":
                    return "\u2654"; // Black king
                default:
                    return "";
            }
        }
    }
}

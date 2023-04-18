package Group_Project_Chess_S2023.Business_Layer;

// A specialty case of a move that results in a pawn being promoted to another piece type
public class PromotionMove extends Move {
    private char promotedPiece;

    public PromotionMove(boolean p, int startFile, int startRank, int endFile, int endRank, char piece, char promoteTo) {
        super(p, startFile, startRank, endFile, endRank, piece);
        promotedClass = promoteTo;
        // isCastle = piece == 'K' && Math.abs(startFile - endFile) > 1
    }

    public char getPromotedClass() { return promotedPiece; }
}

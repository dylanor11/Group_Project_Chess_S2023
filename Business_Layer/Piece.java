package Group_Project_Chess_S2023.Business_Layer;

import java.util.List;

public abstract class Piece {
    protected int rank;
    protected int file;
    protected boolean color; // true for white, false for black
    protected boolean hasMoved;
    protected String name; // this may be redundant given the subclasses
    protected List<Move> legalMoves;

    public int getRank() { return rank; }

    public int getFile() { return file; }

    public boolean getColor() { return color; }

    public boolean getHasMoved() { return hasMoved; }

    public String getPiece() { return name; }

    public void printLegalMoves() {
        for( Move move : legalMoves ) {
            System.out.print(move.toString() + ", ");
        }
    }

    /*
     * Returns a list of legal moves for the piece given the current board state
     */
    public abstract List<Move> getLegalMoves(Board curBoard);

}

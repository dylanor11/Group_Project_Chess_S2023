package Group_Project_Chess_S2023.Business_Layer;

import java.util.ArrayList;
import Group_Project_Chess_S2023.Business_Layer.Board;

public abstract class Piece {
    protected int rank;
    protected int file;
    protected boolean color; // true for white, false for black
    protected boolean hasMoved;
    protected String name; // this may be redundant given the subclasses
    protected char symbol;
    protected ArrayList<Move> legalMoves;

    public int getRank() { return rank; }

    public int getFile() { return file; }

    public boolean getColor() { return color; }

    public boolean getHasMoved() { return hasMoved; }

    public String getPiece() { return name; }

    public char getSymbol() { return symbol; }
    
    public void printLegalMoves() {
        for( Move move : legalMoves ) {
            System.out.print(move.toString() + ", ");
        }
    }

    /*
     * Returns a list of legal moves for the piece given the current board state
     */
    public abstract ArrayList<Move> getLegalMoves(Board curBoard);

    /*
     * Checks if a move will put the piece's player's own king in check
     */
  /**
    private boolean causesCheck(Move newMove) {
        Piece movingPiece = board.getBoard()[newMove.getStart()[0]][newMove.getStart()[1]];
        // creates new post-move board and checks if a given color's king will be in check in that position.
        // current structure is more like static method
        // need to verify that copying board won't overwrite any curBoard data
        Board tempBoard = board.copy();

        tempBoard.getBoard()[newMove.getEnd()[0]][newMove.getEnd()[1]] = movingPiece;
        tempBoard.getBoard()[newMove.getStart()[0]][newMove.getStart()[1]] = null; // hopefully this doesn't destroy the moving piece
        //moves.add(new Move(curPlayer, startFile, startRank, endFile, endRank, movingPiece.getSymbol()));
        return tempBoard.inCheck(color); // returns if after the move the current player would be in check

    }
**/
}

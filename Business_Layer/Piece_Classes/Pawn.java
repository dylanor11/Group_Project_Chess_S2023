package Group_Project_Chess_S2023.Business_Layer.Piece_Classes;

import java.util.ArrayList;
import java.util.List;

import Group_Project_Chess_S2023.Business_Layer.Board;
import Group_Project_Chess_S2023.Business_Layer.Move;
import Group_Project_Chess_S2023.Business_Layer.Piece;

public class Pawn extends Piece {
    
    public Pawn(int f, int r, boolean c) {
        file = f;
        rank = r;
        color = c;
        hasMoved = false;
        name = "Pawn";
    }

    @Override
    public List<Move> getLegalMoves(Board curBoard) {
        return generateLegalMoves(curBoard);
    }

    private List<Move> generateLegalMoves(Board curBoard) {
        List<Move> newMoves = new ArrayList<Move>();
        
        //white
        if (color == true) {
            if (curBoard.getBoard()[file][rank+1] == null) { // space in front is open
                newMoves.add(new Move(true, file, rank, file, rank+1, "Pawn"));
                if (!hasMoved && curBoard.getBoard()[file][rank+2] == null) { // should avoid any null pointer exceptions
                    newMoves.add(new Move(true, file, rank, file, rank+2, "Pawn"));
                }
            }

            // capturing left
            if(file > 0 && curBoard.getBoard()[file-1][rank+1] != null && curBoard.getBoard()[file-1][rank+1].getColor() == false) {
                newMoves.add(new Move(true, file, rank, file-1, rank+1, "Pawn"));
            }

            // capturing right
            if(file < 8 && curBoard.getBoard()[file+1][rank+1] != null && curBoard.getBoard()[file+1][rank+1].getColor() == false) {
                newMoves.add(new Move(true, file, rank, file+1, rank+1, "Pawn"));
            }
        }
        // black
        else {
            if(curBoard.getBoard()[file][rank-1] == null) {
                newMoves.add(new Move(true, file, rank, file, rank-1, "Pawn"));

                if(!hasMoved && curBoard.getBoard()[file][rank-2] == null) {
                    newMoves.add(new Move(true, file, rank, file, rank-2, "Pawn"));
                }
            }

            // capturing left
            if(file > 0 && curBoard.getBoard()[file-1][rank-1] != null && curBoard.getBoard()[file-1][rank-1].getColor() == true) {
                newMoves.add(new Move(true, file, rank, file-1, rank-1, "Pawn"));
            }

            // capturing right
            if(file < 7 && curBoard.getBoard()[file+1][rank-1] != null && curBoard.getBoard()[file+1][rank-1].getColor() == true) {
                newMoves.add(new Move(true, file, rank, file+1, rank-1, "Pawn"));
            }
        }

        return newMoves;

        // needs to know double pawn moves to do en passant
        // either need access to game move list or pawns need to store if they have double moved
    }
}

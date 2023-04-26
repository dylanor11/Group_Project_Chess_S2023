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
        symbol = 'P';
    }

    @Override
    public ArrayList<Move> getLegalMoves(Board curBoard) {
        return generateLegalMoves(curBoard);
    }

    public ArrayList<Move> generateLegalMoves(Board curBoard) {
        ArrayList<Move> newMoves = new ArrayList<Move>();
        
        //white
        if (color == true) {
            if (curBoard.getBoard()[file][rank+1] == null) { // space in front is open
                newMoves.add(new Move(true, file, rank, file, rank+1, 'P'));
                if (!hasMoved && curBoard.getBoard()[file][rank+2] == null) { // should avoid any null pointer exceptions
                    newMoves.add(new Move(true, file, rank, file, rank+2, 'P'));
                }
            }

            // capturing left
            if(file > 0 && curBoard.getBoard()[file-1][rank+1] != null && curBoard.getBoard()[file-1][rank+1].getColor() == false) {
                newMoves.add(new Move(true, file, rank, file-1, rank+1, 'P'));
            }

            // capturing right
            if(file < 7 && curBoard.getBoard()[file+1][rank+1] != null && curBoard.getBoard()[file+1][rank+1].getColor() == false) {
                newMoves.add(new Move(true, file, rank, file+1, rank+1, 'P'));
            }
        }
        // black
        else {
            if(curBoard.getBoard()[file][rank-1] == null) {
                newMoves.add(new Move(true, file, rank, file, rank-1, 'P'));

                if(!hasMoved && curBoard.getBoard()[file][rank-2] == null) {
                    newMoves.add(new Move(true, file, rank, file, rank-2, 'P'));
                }
            }

            // capturing left
            if(file > 0 && curBoard.getBoard()[file-1][rank-1] != null && curBoard.getBoard()[file-1][rank-1].getColor() == true) {
                newMoves.add(new Move(true, file, rank, file-1, rank-1, 'P'));
            }

            // capturing right
            if(file < 7 && curBoard.getBoard()[file+1][rank-1] != null && curBoard.getBoard()[file+1][rank-1].getColor() == true) {
                newMoves.add(new Move(true, file, rank, file+1, rank-1, 'P'));
            }
        }

        return newMoves;

        // needs to know double pawn moves to do en passant
        // either need access to game move list or pawns need to store if they have double moved
    }
}

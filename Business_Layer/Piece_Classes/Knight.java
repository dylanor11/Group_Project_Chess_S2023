package Group_Project_Chess_S2023.Business_Layer.Piece_Classes;

import java.util.ArrayList;
import java.util.List;

import Group_Project_Chess_S2023.Business_Layer.Board;
import Group_Project_Chess_S2023.Business_Layer.Move;
import Group_Project_Chess_S2023.Business_Layer.Piece;

public class Knight extends Piece {
    
    public Knight(int f, int r, boolean c) {
        file = f;
        rank = r;
        color = c;
        hasMoved = false;
        name = "Knight";
        symbol = 'N';
    }

    @Override
    public List<Move> getLegalMoves(Board curBoard) {
        List<Move> newMoves = new ArrayList<Move>();
        // 8 if statements -- first check between files b and g
        if(file > 0) {
            if(rank > 1 && (curBoard.getBoard()[file-1][rank-2] == null || curBoard.getBoard()[file-1][rank-2].getColor() != color)) {
                newMoves.add(new Move(color, file, rank, file-1, rank-2, 'K'));
            }

            if(rank < 6 && (curBoard.getBoard()[file-1][rank+2] == null || curBoard.getBoard()[file-1][rank+2].getColor() != color)) {
                newMoves.add(new Move(color, file, rank, file-1, rank+2, 'K'));
            }

            if(file > 1) {
                if(rank > 0 && (curBoard.getBoard()[file-2][rank-1] == null || curBoard.getBoard()[file-2][rank-1].getColor() != color)) {
                    newMoves.add(new Move(color, file, rank, file-2, rank-1, 'K'));
                }

                if(rank < 7 && (curBoard.getBoard()[file-2][rank+1] == null || curBoard.getBoard()[file-2][rank+1].getColor() != color)) {
                    newMoves.add(new Move(color, file, rank, file-2, rank+1, 'K'));
                }
            }
        }

        if(file < 7) {
            if(rank > 1 && (curBoard.getBoard()[file+1][rank-2] == null || curBoard.getBoard()[file+1][rank-2].getColor() != color)) {
                newMoves.add(new Move(color, file, rank, file+1, rank-2, 'K'));
            }

            if(rank < 6 && (curBoard.getBoard()[file+1][rank+2] == null || curBoard.getBoard()[file+1][rank+2].getColor() != color)) {
                newMoves.add(new Move(color, file, rank, file+1, rank+2, 'K'));
            }

            if(file < 6) {
                if(rank > 0 && (curBoard.getBoard()[file+2][rank-1] == null || curBoard.getBoard()[file+2][rank-1].getColor() != color)) {
                    newMoves.add(new Move(color, file, rank, file+2, rank-1, 'K'));
                }

                if(rank < 7 && (curBoard.getBoard()[file+2][rank+1] == null || curBoard.getBoard()[file+2][rank+1].getColor() != color)) {
                    newMoves.add(new Move(color, file, rank, file+2, rank+1, 'K'));
                }
            }
        }
        
        return newMoves;
    }

}
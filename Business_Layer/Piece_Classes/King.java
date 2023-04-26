package Group_Project_Chess_S2023.Business_Layer.Piece_Classes;

import java.util.ArrayList;
import java.util.List;

import Group_Project_Chess_S2023.Business_Layer.Board;
import Group_Project_Chess_S2023.Business_Layer.Move;
import Group_Project_Chess_S2023.Business_Layer.Piece;

public class King extends Piece {
    private boolean isChecked; // Whether the king is in check

    
    public King(int f, int r, boolean c) {
        file = f;
        rank = r;
        color = c;
        hasMoved = false;
        name = "King";
        symbol = 'K';
    }

    @Override
    public ArrayList<Move> getLegalMoves(Board curBoard) {
        ArrayList<Move> newMoves = new ArrayList<Move>();
        int[][] moves = {{-1, -1}, {0, -1}, {-1, -1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] move : moves) {

            int newFile = file + move[0];
            int newRank = rank + move[1];

            if (isValidKingMove(curBoard,newFile, newRank)) {
              newMoves.add(new Move(color,file, rank, newFile, newRank, 'K'));
            }
        }
        return newMoves;
    }


    public boolean isValidKingMove(Board curBoard, int toFile, int toRank) {
        
        if ( toFile < 0 || toFile >= 7 || toRank < 0 || toRank >= 7) {
            return false;  // Piece out of board range, move invalid
        }

        Piece piece = curBoard.getBoard()[toFile][toRank];

        if(piece == null) {
            return false;
        }

        if (piece.getColor() == color) {
            return false;  // The target position is occupied by a friendly piece, move invalid
        }


        return true;
    }







}


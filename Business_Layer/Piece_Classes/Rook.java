package Group_Project_Chess_S2023.Business_Layer.Piece_Classes;

import java.util.ArrayList;
import java.util.List;

import Group_Project_Chess_S2023.Business_Layer.Board;
import Group_Project_Chess_S2023.Business_Layer.Move;
import Group_Project_Chess_S2023.Business_Layer.Piece;

public class Rook extends Piece {
    
    public Rook(int f, int r, boolean c) {
        file = f;
        rank = r;
        color = c;
        hasMoved = false;
        name = "Rook";
        symbol = 'R';
    }

    @Override
    public List<Move> getLegalMoves(Board curBoard) {
        List<Move> newMoves = new ArrayList<Move>();
        int newFile;
        int newRank;
        int step = 1;
        // left move
            while (step < 8) {

                newFile = file -step;
                newRank = rank ;
                if (isValidRookMove(curBoard,newFile, newRank)) {
                    newMoves.add(new Move(color,file, rank, newFile, newRank, "Rook"));
                    step++;
                }
                else{
                    step = 1;
                    break;
                }

            }
        // right move
        while (step < 8) {

            newFile = file +step;
            newRank = rank ;
            if (isValidRookMove(curBoard,newFile, newRank)) {
                newMoves.add(new Move(color,file, rank, newFile, newRank, "Rook"));
                step++;
            }
            else{
                step = 1;
                break;
            }

        }
        // down move
        while (step < 8) {

            newFile = file ;
            newRank = rank - step;
            if (isValidRookMove(curBoard,newFile, newRank)) {
                newMoves.add(new Move(color,file, rank, newFile, newRank, "Rook"));
                step++;
            }
            else{
                step = 1;
                break;
            }

        }
        // up move
        while (step < 8) {

            newFile = file ;
            newRank = rank + step;
            if (isValidRookMove(curBoard,newFile, newRank)) {
                newMoves.add(new Move(color,file, rank, newFile, newRank, "Rook"));
                step++;
            }
            else{
                step = 1;
                break;
            }

        }
          return newMoves;
    }



    public boolean isValidRookMove(Board curBoard, int toFile, int toRank) {
        Piece piece = curBoard.getBoard()[toFile][toRank];
        if ( toFile < 0 || toFile >= 7 || toRank < 0 || toRank >= 7) {
            return false;  // Piece out of board range, move invalid
        }
        if (piece.getColor() == color) {
            return false;  // The target position is occupied by a friendly piece, move invalid
        }


        return true;
    }
}
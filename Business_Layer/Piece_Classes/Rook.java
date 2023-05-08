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
    public ArrayList<Move> getLegalMoves(Board curBoard) {
        ArrayList<Move> newMoves = new ArrayList<Move>();
        // Generate legal moves for rook
        // Check vertical moves
        for (int i = file + 1; i <= 7; i++) {
            if (curBoard.getBoard()[i][rank] == null)
                newMoves.add(new Move(color, file, rank, i, rank, symbol));
            else {
                if (curBoard.getBoard()[i][rank].getColor() != this.getColor())
                    newMoves.add(new Move(color, file, rank, i, rank, symbol));
                break;
            }
        }
        for (int i = file - 1; i >= 0; i--) {
            if (curBoard.getBoard()[i][rank] == null)
                newMoves.add(new Move(color, file, rank, i, rank, symbol));
            else {
                if (curBoard.getBoard()[i][rank].getColor() != this.getColor())
                    newMoves.add(new Move(color, file, rank, i, rank, symbol));
                break;
            }
        }

        // Check horizontal moves
        for (int j = rank + 1; j <= 7; j++) {
            if (curBoard.getBoard()[file][j] == null)
                newMoves.add(new Move(color, file, rank, file, j, symbol));
            else {
                if (curBoard.getBoard()[file][j].getColor() != this.getColor())
                    newMoves.add(new Move(color, file, rank, file, j, symbol));
                break;
            }
        }
        for (int j = rank - 1; j >= 0; j--) {
            if (curBoard.getBoard()[file][j] == null)
                newMoves.add(new Move(color, file, rank, file, j, symbol));
            else {
                if (curBoard.getBoard()[file][j].getColor() != this.getColor())
                    newMoves.add(new Move(color, file, rank, file, j, symbol));
                break;
            }
        }
        return newMoves;
    }
}
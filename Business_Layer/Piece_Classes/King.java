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
        int[][] directions = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};

        for (int i = 0; i < directions.length; i++) {
            int newFile = file + directions[i][0];
            int newRank = rank + directions[i][1];
            if (newFile < 0 || newFile > 7 || newRank < 0 || newRank > 7) continue;
            if (curBoard.getBoard()[newFile][newRank] == null) {
                newMoves.add(new Move(color, file, rank, newFile, newRank, symbol));
            } else if (curBoard.getBoard()[newFile][newRank].getColor() != this.getColor()) {
                newMoves.add(new Move(color, file, rank, newFile, newRank, symbol));
            }
        }
        return newMoves;
    }





}


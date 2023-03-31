package Group_Project_Chess_S2023.Business_Layer.Piece_Classes;

import java.util.ArrayList;
import java.util.List;

import Group_Project_Chess_S2023.Business_Layer.Board;
import Group_Project_Chess_S2023.Business_Layer.Move;
import Group_Project_Chess_S2023.Business_Layer.Piece;

public class Bishop extends Piece {
    
    public Bishop(int f, int r, boolean c) {
        file = f;
        rank = r;
        color = c;
        hasMoved = false;
        name = "Bishop";
        symbol = 'B';
    }

    @Override
    public List<Move> getLegalMoves(Board curBoard) {
        return new ArrayList<Move>();
    }

}
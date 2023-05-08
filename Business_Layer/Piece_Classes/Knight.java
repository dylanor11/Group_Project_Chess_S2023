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
    public ArrayList<Move> getLegalMoves(Board curBoard) {
        ArrayList<Move> newMoves = new ArrayList<Move>();
        // 8 if statements -- first check between files b and g
        if(file > 0) {
            if(rank > 1 && (curBoard.getBoard()[file-1][rank-2] == null || curBoard.getBoard()[file-1][rank-2].getColor() != color)) {
                Move move1 = new Move(color, file, rank, file-1, rank-2, 'K');
                //if(!causesCheck(move1)) {
                    newMoves.add(move1);
                //}
            }

            if(rank < 6 && (curBoard.getBoard()[file-1][rank+2] == null || curBoard.getBoard()[file-1][rank+2].getColor() != color)) {
                Move move2 = new Move(color, file, rank, file-1, rank+2, 'K');
                //if(!causesCheck(move2)) {
                    newMoves.add(move2);
               // }
            }

            if(file > 1) {
                if(rank > 0 && (curBoard.getBoard()[file-2][rank-1] == null || curBoard.getBoard()[file-2][rank-1].getColor() != color)) {
                    Move move3 = new Move(color, file, rank, file-2, rank-1, 'K');
                    //if(!causesCheck(move3)) {
                        newMoves.add(move3);
                    //}
                }

                if(rank < 7 && (curBoard.getBoard()[file-2][rank+1] == null || curBoard.getBoard()[file-2][rank+1].getColor() != color)) {
                    Move move4 = new Move(color, file, rank, file-2, rank+1, 'K');
                    //if(!causesCheck(move4)) {
                        newMoves.add(move4);
                    //}
                }
            }
        }

        if(file < 7) {
            if(rank > 1 && (curBoard.getBoard()[file+1][rank-2] == null || curBoard.getBoard()[file+1][rank-2].getColor() != color)) {
                Move move5 = new Move(color, file, rank, file+1, rank-2, 'K');
                //if(!causesCheck(move5)) {
                    newMoves.add(move5);
                //}
            }

            if(rank < 6 && (curBoard.getBoard()[file+1][rank+2] == null || curBoard.getBoard()[file+1][rank+2].getColor() != color)) {
                Move move6 = new Move(color, file, rank, file+1, rank+2, 'K');
                    newMoves.add(move6);
                }
            }

            if(file < 6) {
                if(rank > 0 && (curBoard.getBoard()[file+2][rank-1] == null || curBoard.getBoard()[file+2][rank-1].getColor() != color)) {
                    Move move7 = new Move(color, file, rank, file+2, rank-1, 'K');
                    //if(!causesCheck(move7)) {
                        newMoves.add(move7);
                    //}
                }

                if(rank < 7 && (curBoard.getBoard()[file+2][rank+1] == null || curBoard.getBoard()[file+2][rank+1].getColor() != color)) {
                    Move move8 = new Move(color, file, rank, file+2, rank+1, 'K');
                    //if(!causesCheck(move8)) {
                        newMoves.add(move8);
                    //}
                }
            }
        
        return newMoves;
    }

}
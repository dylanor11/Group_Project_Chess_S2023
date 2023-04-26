package Group_Project_Chess_S2023.Business_Layer;

import Group_Project_Chess_S2023.Business_Layer.Piece_Classes.*;
import java.util.ArrayList;
public class Board {
    private Piece[][] squares; // [file][rank]
    private Piece whiteKing;
    private Piece blackKing;
    
    /*
     * Creates a new board with pieces in their designated positions
     * Java initializes values in object arrays to null
     */
    public Board() {
        squares = new Piece[8][8];
        squares[0][0] = new Rook(0, 0, true);
        squares[7][0] = new Rook(7, 0, true);
        squares[1][0] = new Knight(1, 0, true);
        squares[6][0] = new Knight(6, 0, true);

        squares[2][0] = new Bishop(2, 0, true);
        squares[5][0] = new Bishop(5, 0, true);

        squares[3][0] = new Queen(3, 0, true);
        squares[4][0] = new King(4, 0, true);
        whiteKing = squares[4][0];

        squares[0][7] = new Rook(0, 7, false);
        squares[7][7] = new Rook(7, 7, false);
        squares[1][7] = new Knight(1, 7, false);
        squares[6][7] = new Knight(6, 7, false);

        squares[2][7] = new Bishop(2, 7, false);
        squares[5][7] = new Bishop(5, 7, false);

        squares[3][7] = new Queen(3, 7, false);
        squares[4][7] = new King(4, 7, false);
        blackKing = squares[4][7];

        // create pawns
        for(int i = 0; i < 8; i++) {
            squares[i][1] = new Pawn(i, 1, true);
            squares[i][6] = new Pawn(i, 6, false);
        }
    }

    public Piece[][] getBoard() { return squares; }

    public Board copy() {
        Board newBoard = new Board();
        for(int file = 0; file < 8; file++) {
            for(int rank = 0; rank < 8; rank++) {
                newBoard.getBoard()[file][rank] = squares[file][rank];
            }
        }
        return newBoard;
    }


    public boolean whiteInCheck() {
        Board tempBoard = this.copy();
        Piece[][] tempPieces = tempBoard.getBoard();

        for(int file = 0; file < 8; file++) {
            for(int rank = 0; rank < 8; rank++) {
                if(squares[file][rank] != null) {
                    ArrayList<Move> tempMoves = tempPieces[file][rank].getLegalMoves(tempBoard);
                    for(Move move : tempMoves) {
                        if(move.getEnd()[0] == whiteKing.getFile() && move.getEnd()[1] == whiteKing.getRank());
                    }
                }
            }
        }

        return false;
    }

    public boolean blackInCheck() {
        Board tempBoard = this.copy();
        Piece[][] tempPieces = tempBoard.getBoard();

        for(int file = 0; file < 8; file++) {
            for(int rank = 0; rank < 8; rank++) {
                if(squares[file][rank] != null) {
                    ArrayList<Move> tempMoves = tempPieces[file][rank].getLegalMoves(tempBoard);
                    for(Move move : tempMoves) {
                        if(move.getEnd()[0] == blackKing.getFile() && move.getEnd()[1] == blackKing.getRank());
                    }
                }
            }
        }

        return false;
    }

    public boolean inCheck(boolean curPlayer) {
        Board tempBoard = this.copy();
        Piece[][] tempPieces = tempBoard.getBoard();

        for(int file = 0; file < 8; file++) {
            for(int rank = 0; rank < 8; rank++) {
                if(squares[file][rank] != null) {
                    ArrayList<Move> tempMoves = tempPieces[file][rank].getLegalMoves(tempBoard);
                    for(Move move : tempMoves) {
                        if(curPlayer) { // ... is white 
                            if(move.getEnd()[0] == whiteKing.getFile() && move.getEnd()[1] == whiteKing.getRank());
                        } else {
                            if(move.getEnd()[0] == blackKing.getFile() && move.getEnd()[1] == blackKing.getRank());
                        }
                    }
                }
            }
        }

        return false; // temporary
    }
}

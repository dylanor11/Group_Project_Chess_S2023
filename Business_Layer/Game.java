package Group_Project_Chess_S2023.Business_Layer;

import Group_Project_Chess_S2023.User_Layer.*;
import Group_Project_Chess_S2023.Business_Layer.Piece_Classes.Queen;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player[] players;
    private Board board;
    private boolean curPlayer; // true if white, false if black
    private List<Move> moves;

    /*
     * Creates a new game with 2 players of default names
     */
    public Game() {
        players = new Player[2];
        players[0] = new Player("Player 1");
        players[1] = new Player("Player 2");
        board = new Board();
        curPlayer = true;
        moves = new ArrayList<Move>();
    }

    public Game(String player1, String player2) {
        players = new Player[2];
        players[0] = new Player(player1);
        players[1] = new Player(player2);
        board = new Board();
        curPlayer = true;
        moves = new ArrayList<Move>();
    }

    public boolean getCurPlayer() { return curPlayer; }

    public Board getBoard() { return board; } // could also return the array of pieces with board.getBoard()

    public boolean freeMove(int startFile, int startRank, int endFile, int endRank) {
        Piece movingPiece = board.getBoard()[startFile][startRank];
        if(movingPiece != null) {
            board.getBoard()[endFile][endRank] = movingPiece;
            board.getBoard()[startFile][startRank] = null; // hopefully this doesn't destroy the moving piece
            moves.add(new Move(curPlayer, startFile, startRank, endFile, endRank, movingPiece.getSymbol()));
            curPlayer = !curPlayer;
            return true;
        }
        return false; // TODO: will return false if move is illegal
    }

    public boolean move(int startFile, int startRank, int endFile, int endRank) {
		boolean isPawnMove = false; //We need to know this for pawn promotion and for en passant
        Move lastMove = moves.get(moves.size()-1);
        Piece movingPiece = board.getBoard()[startFile][startRank];
        if(movingPiece == null || movingPiece.getColor() != curPlayer) return false; // stops if player tries to move null or opponent piece
        if(movingPiece.getSymbol() == 'P') // If the movivng piece is a pawn we check for that
        	{
        		isPawnMove = true; 
        	}
        
        //SEPERATE LOGIC FOR EN PASSANT MOVE
        if(isPawnMove)
        {  
            //If the last move was was a pawn double move
            if(lastMove.getPieceType() == 'P' && Math.abs(lastMove.getStart()[1] - lastMove.getEnd()[1]) == 2)
            {
                //If our pawn is on the same rank as where the last one ended
                if(startRank == lastMove.getEnd()[1])
                {   
                    // If our pawn is next to the double moving pawn from last move
                    if(lastMove.getEnd()[0] == startFile - 1)
                    {
                        //Left capture
                        if(endFile == startFile - 1)
                        {
                            if(endRank == startRank + 1 && movingPiece.getColor())
                                {
                                    //Valid white En passant left
                                    Move move = new Move(curPlayer, startFile, startRank, endFile, endRank, movingPiece.getSymbol());
                                    board.getBoard()[endFile][endRank] = movingPiece;
                                    board.getBoard()[endFile][endRank - 1] = null;
                                    board.getBoard()[startFile][startRank] = null;
                                    moves.add(move);
                                    curPlayer = !curPlayer;
                                    return true;



                                }
                            if(endRank == startRank - 1 && movingPiece.getColor() == false)
                                {
                                    //Valid black En passant left
                                    Move move = new Move(curPlayer, startFile, startRank, endFile, endRank, movingPiece.getSymbol());
                                    board.getBoard()[endFile][endRank] = movingPiece;
                                    board.getBoard()[endFile][endRank + 1] = null;
                                    board.getBoard()[startFile][startRank] = null;
                                    moves.add(move);
                                    curPlayer = !curPlayer;
                                    return true;

                                }    

                        }
                    }

                    else if(lastMove.getEnd()[0] == startFile + 1)
                    {   
                        //Right capture
                        if(endFile == startFile + 1)
                        {
                            if(endRank == startRank + 1 && movingPiece.getColor())
                                {
                                    //valid white En passant right
                                    Move move = new Move(curPlayer, startFile, startRank, endFile, endRank, movingPiece.getSymbol());
                                    board.getBoard()[endFile][endRank] = movingPiece;
                                    board.getBoard()[endFile][endRank - 1] = null;
                                    board.getBoard()[startFile][startRank] = null;
                                    moves.add(move);
                                    curPlayer = !curPlayer;
                                    return true;
                                }
                            if(endRank == startRank - 1 && movingPiece.getColor() == false)
                                {
                                   //valid black en passant right
                                    Move move = new Move(curPlayer, startFile, startRank, endFile, endRank, movingPiece.getSymbol());
                                    board.getBoard()[endFile][endRank] = movingPiece;
                                    board.getBoard()[endFile][endRank + 1] = null;
                                    board.getBoard()[startFile][startRank] = null;
                                    moves.add(move);
                                    curPlayer = !curPlayer;
                                    return true;
                                }
                        }
                    }
                }
            }
        }

        if(lastMove.isInList(movingPiece.getLegalMoves(board)) && !causesCheck(lastMove)) {
        	if(isPawnMove)

        		{
        			if(endFile == 7)
        			{
        				movingPiece = new Queen(startFile, startRank, true);
        			}
        			if(endFile == 0)
        			{
        				movingPiece = new Queen(startFile, startRank, false);
        			}
                  
        		}

            board.getBoard()[endFile][endRank] = movingPiece;
            board.getBoard()[startFile][startRank] = null; // hopefully this doesn't destroy the moving piece
            moves.add(lastMove);
            curPlayer = !curPlayer;
            return true;

            // need special case moves to represent castle because it involves moving two pieces
        }
        return false;
}

    public void rudimentaryPrint() {
        Piece[][] curBoard = board.getBoard();
        String[] ranks = new String[8];
        Piece curPiece;
        for(int i = 0; i < curBoard.length; i++) { // row - rank
            ranks[i] = "";
            for (int j = 0; j < curBoard.length; j++) { // column - file
                curPiece = curBoard[j][i];

                if (curPiece == null) {
                    ranks[i] += ". ";
                } else {
                    ranks[i] += curPiece.getSymbol() + " ";
                }
            }
        }
        
        for(int k = 7; k >= 0; k--) {
            System.out.println(ranks[k]);
        }

        System.out.println("---------------\n");
    }

    // it may be best to put this function in the piece class to avoid adding check-causing moves to their lists
    // this would make stalemate checking much easier
    private boolean causesCheck(Move newMove) {
        Piece movingPiece = board.getBoard()[newMove.getStart()[0]][newMove.getStart()[1]];
        // creates new post-move board and checks if a given color's king will be in check in that position.
        // current structure is more like static method
        // need to verify that copying board won't overwrite any curBoard data
        Board tempBoard = board.copy();

        tempBoard.getBoard()[newMove.getEnd()[0]][newMove.getEnd()[1]] = movingPiece;
        tempBoard.getBoard()[newMove.getStart()[0]][newMove.getStart()[1]] = null; // hopefully this doesn't destroy the moving piece
        //moves.add(new Move(curPlayer, startFile, startRank, endFile, endRank, movingPiece.getSymbol()));
        return tempBoard.inCheck(curPlayer); // returns if after the move the current player would be in check

    }


    // Technically the stalemate and checkmate checkers will go when you still can do a special move like En Passant
    public boolean stalemate() {
        if(board.inCheck(curPlayer)) {
            return false; // if your are in check, you are not stalemated
        }

        Piece[][] squares = board.getBoard();
        
        for(int file = 0; file < 8;) {
            for(int rank = 0; rank < 8;) {
                if(squares[file][rank] != null) {
                    Piece piece = squares[file][rank];
                    if(piece.getColor() == curPlayer) {
                        if(piece.getLegalMoves(board).size() != 0) {
                            return false; // if any of our pieces can move, we are not stalemated
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean checkmate() {
        if(!board.inCheck(curPlayer)) {
            return false; // if you aren't in check, you aren't checkmated
        }

        Piece[][] squares = board.getBoard();
        
        for(int file = 0; file < 8;) {
            for(int rank = 0; rank < 8;) {
                if(squares[file][rank] != null) {
                    Piece piece = squares[file][rank];
                    if(piece.getColor() == curPlayer) {
                        ArrayList<Move> moves = piece.getLegalMoves(board);
                        if(moves.size() != 0) {
                            // this piece has moves and is yours
                            for(Move move : moves) {
                                if(!causesCheck(move)) {
                                    return false; 
                                    // if you have a piece that can move in a way that you aren't in check, you aren't checkmated
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    // getting checkmate needs to verify that every move available to a player still results in a board in which they are in check

    public static void main(String[] args) {
        GameUI gameUI = new GameUI();
        //game1.rudimentaryPrint();
        //game1.move(1, 0, 2, 2);
        //game1.move(6, 0, 7, 2);
        //game1.move(1, 7, 0, 5);
        //game1.move(6, 7, 7, 5);

        // game1.move(0, 0, 0, 2);

        // TODO: Add interactive user-input testing in console
    }


}

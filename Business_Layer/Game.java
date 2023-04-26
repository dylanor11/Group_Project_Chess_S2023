package Group_Project_Chess_S2023.Business_Layer;
import Group_Project_Chess_S2023.User_Layer.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player[] players;
    private Board board;
    private boolean curPlayer; // true if white, false if black
    private List<Move> moves;
    private GameUI gameUI;

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
        Piece movingPiece = board.getBoard()[startFile][startRank];
        if(movingPiece == null || movingPiece.getColor() != curPlayer) return false; // stops if player tries to move null or opponent piece
        // else
        Move move = new Move(curPlayer, startFile, startRank, endFile, endRank, movingPiece.getSymbol());

        if(move.isInList(movingPiece.getLegalMoves(board)) && !causesCheck(move)) {
            board.getBoard()[endFile][endRank] = movingPiece;
            board.getBoard()[startFile][startRank] = null; // hopefully this doesn't destroy the moving piece
            moves.add(move);
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
        // creates new post-move board and checks if a given color's king will be in check in that position.
        // current structure is more like static method
        // need to verify that copying board won't overwrite any curBoard data
        Board tempBoard = board.copy();

        tempBoard.getBoard()[endFile][endRank] = movingPiece;
        tempBoard.getBoard()[startFile][startRank] = null; // hopefully this doesn't destroy the moving piece
        //moves.add(new Move(curPlayer, startFile, startRank, endFile, endRank, movingPiece.getSymbol()));
        return tempBoard.inCheck(curPlayer); // returns if after the move the current player would be in check

    }

    // getting checkmate needs to verify that every move available to a player still results in a board in which they are in check

    public void setUpBoard() {
        gameUI = new GameUI(this);
    }

    public static void main(String[] args) {
        Game game1 = new Game();
        game1.rudimentaryPrint();
        game1.move(1, 0, 2, 2);
        game1.move(6, 0, 7, 2);
        game1.move(1, 7, 0, 5);
        game1.move(6, 7, 7, 5);

        // game1.move(0, 0, 0, 2);
        //game1.rudimentaryPrint();
        //game1.setUpBoard();

        // TODO: Add interactive user-input testing in console
    }
}

package Group_Project_Chess_S2023.Business_Layer;

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
        movingPiece = board.getBoard()[startFile][startRank];
        if(movingPiece != null) {
            board.getBoard()[endFile][endRank] = movingPiece;
            board.getBoard()[startFile][startRank] = null; // hopefully this doesn't destroy the moving piece
            moves.add(new Move(curPlayer));
            return true;
        }
        return false; // TODO: will return false if move is illegal
    }

    // public boolean move(int startFile, int startRank, int endFile, int endRank) {
    //     movingPiece = board.getBoard()[startFile][startRank];
    // }
}

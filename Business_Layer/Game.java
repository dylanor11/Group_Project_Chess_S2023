package Group_Project_Chess_S2023.Business_Layer;

public class Game {
    private Player[] players;
    private Board board;
    private boolean curPlayer; // true if white, false if black

    /*
     * Creates a new game with 2 players of default names
     */
    public Game() {
        players = new Player[2];
        players[0] = new Player("Player 1");
        players[1] = new Player("Player 2");
        board = new Board();
        curPlayer = true;
    }

    public Game(String player1, String player2) {
        players = new Player[2];
        players[0] = new Player(player1);
        players[1] = new Player(player2);
        board = new Board();
        curPlayer = true;
    }

    public boolean getCurPlayer() { return curPlayer; }

    public Board getBoard() { return board; } // could also return the array of pieces with board.getBoard()

}

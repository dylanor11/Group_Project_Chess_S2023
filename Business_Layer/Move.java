package Group_Project_Chess_S2023.Business_Layer;

public class Move {
    private boolean player; // true for white, false for black
    private int[] startSpace;
    private int[] endSpace;
    private String pieceType;

    public Move(boolean p, int startFile, int startRank, int endFile, int endRank, String piece) {
        player = p;
        startSpace = new int[] {startFile, startRank};
        endSpace = new int[] {endFile, endRank};
        pieceType = piece;
    }

    public boolean getPlayer() { return player; }

    public int[] getStart() { return startSpace; }

    public int[] getEnd() { return endSpace; }

    public String getPieceType() { return pieceType; }
}

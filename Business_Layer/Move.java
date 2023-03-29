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

    // technically doesn't generate standard chess notation
    public String toString() {
        return pieceType.substring(0,1) + squareToString(startSpace) + " - " + squareToString(endSpace);
    }

    private static String squareToString(int[] square) {
        return fileToLetter(square[0]) + Integer.toString(square[1]);
    }

    public static char fileToLetter(int f) {
        return (char)(f + 97); // 
    }

}

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

    public boolean equals(Move otherMove) { // very rudimentary, need to keep track of move number to get actual move equality
        return startSpace[0] == otherMove.getStart()[0]
            && startSpace[1] == otherMove.getStart()[1]
            && endSpace[0]   == otherMove.getEnd()[0]
            && endSpace[1]   == otherMove.getEnd()[1];
    }

    public boolean isInList(List<Move> otherMoves) {
        boolean contains = false;
        for(Move move : otherMoves) {
            if(this.equals(move)) {
                contains = true;
            }
        }
        return contains;
    }

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

package Group_Project_Chess_S2023.Business_Layer;
import java.util.List;
import java.lang.Math;

public class Bishop implements Piece{
	

	public void updateLegalMoves()
		{
			
		}

	public boolean checkMove(Piece[][] board, int startFile, int startRank, int endFile, int endRank){

		//Firstly, if the bishop doesn't move diagonally the move is invalid.

		if(startFile == endFile || startRank == endRank){
			return false;
		}

		//Next, if the absolute value of the difference of startRank and endRank does not equal absolute value of difference
		//of startFile and endFile, it is also invalid

		if(Math.abs(startFile-endFile) != Math.abs(startRank-endRank)){
			return false;
		}

		//Finally we must figure out if it is moving left or right and if it is moving up or backwards

		int fileOffset;
		int rankOffset;

		if(startFile > endFile)
		{	//Moving left from white perspective
			fileOffset = -1;
		}
		else
		{	//Moving right from white perspective
			fileOffset = 1;
		}

		if(startRank > endRank)
		{	//Moving backwards from white perspective
			rankOffset = -1;
		}
		else
		{
			//moving forward from white perspective
			rankOffset = 1;
		}
		int x = startRank + rankOffset;
		int y = startFile + fileOffset;
		for(x; x != endRow; x + rankOffset){
			if(board[y][x] != nullPiece){
				return false;
			}
			y += fileOffset;
		}
		return true;
	}
}
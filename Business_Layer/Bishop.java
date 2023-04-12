package Group_Project_Chess_S2023.Business_Layer;
import java.util.List;
import java.lang.Math;

public class Bishop implements Piece{
	

	public void updateLegalMoves(Piece[][] board)
		{
			int startFile = this.getFile;
			int startRank = this.getRank;
			//Reset the legalmoves
			this.legalMoves = new ArrayList<Move>();


			//Direction 1
			int checkRank = startRank + 1;
			int checkFile = startFile + 1;
			boolean blocked = false;
			while(!blocked)
			{
				if(board[checkFile][checkRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank);
					legalMoves.append(newMove);
					checkRank++ ;
					checkFile++;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank);
						legalMoves.append(newMove);
					}
				}
			}

			//Direction 2
			checkRank = startRank + 1;
			checkFile = startFile - 1;
			boolean blocked = false;
			while(!blocked)
			{
				if(board[checkFile][checkRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank);
					legalMoves.append(newMove);
					checkRank++;
					checkFile--;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank);
						legalMoves.append(newMove);
					}
				}
			}

			//Direction 3
			checkRank = startRank - 1;
			checkFile = startFile + 1;
			boolean blocked = false;
			while(!blocked)
			{
				if(board[checkFile][checkRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank);
					legalMoves.append(newMove);
					checkRank--;
					checkFile++;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank);
						legalMoves.append(newMove);
					}
				}
			}

			//Direction 4
			checkRank = startRank - 1;
			checkFile = startFile - 1;
			boolean blocked = false;
			while(!blocked)
			{
				if(board[checkFile][checkRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank);
					legalMoves.append(newMove);
					checkRank--;
					checkFile--;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank);
						legalMoves.append(newMove);
					}
				}
			}

		}

	public boolean checkMove(Piece[][] board, int startFile, int startRank, int endFile, int endRank){

		//Firstly, if the bishop doesn't move diagonally the move is invalid.

		if(startFile == endFile || startRank == endRank){
			return false;
		}

		if(board[endFile][endRank].isWhite() == this.isWhite())
		{
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
		for(x; x != endRank; x + rankOffset){
			if(board[y][x] != nullPiece){
				return false;
			}
			y += fileOffset;
		}
		return true;
	}
}
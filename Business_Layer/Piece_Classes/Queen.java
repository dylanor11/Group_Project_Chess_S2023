package Group_Project_Chess_S2023.Business_Layer;
import java.util.List;
import java.lang.Math;


public class Queen implements Piece{
	
	public Queen(int f, int r, boolean c) {
        file = f;
        rank = r;
        color = c;
        hasMoved = false;
        name = "Queen";
        symbol = 'Q';
    }

public void updateLegalMoves(Piece[][] board)
		{
			int startFile = this.file;
			int startRank = this.rank;
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
					if(board[checkFile][checkRank].getColor() != this.color)
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
					if(board[checkFile][checkRank].getColor() != this.color)
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
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank);
						legalMoves.append(newMove);
					}
				}
			}

			//Direction 5
			int checkFile = startFile + 1;
			boolean blocked = false;
			while(!blocked)
			{
				if(board[checkFile][startRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank);
					this.legalMoves.append(newMove);
					checkFile++;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank);
						this.legalMoves.append(newMove);
					}
				}
			}

			//Direction 6
			int checkFile = startFile - 1;
			boolean blocked = false;
			while(!blocked)
			{
				if(board[checkFile][startRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank);
					this.legalMoves.append(newMove);
					checkFile--;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank);
						this.legalMoves.append(newMove);
					}
				}
			}

			//Direction 7
			int checkRank = startRank + 1;
			boolean blocked = false;
			while(!blocked)
			{
				if(board[checkFile][startRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank);
					this.legalMoves.append(newMove);
					checkRank++;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank);
						this.legalMoves.append(newMove);
					}
				}
			}
			//Direction 8
			int checkRank = startRank - 1;
			boolean blocked = false;
			while(!blocked)
			{
				if(board[checkFile][startRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank);
					this.legalMoves.append(newMove);
					checkRank--;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank);
						this.legalMoves.append(newMove);
					}
				}
			}
}

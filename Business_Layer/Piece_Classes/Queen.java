package Group_Project_Chess_S2023.Business_Layer.Piece_Classes;

import Group_Project_Chess_S2023.Business_Layer.Board;
import Group_Project_Chess_S2023.Business_Layer.Move;
import Group_Project_Chess_S2023.Business_Layer.Piece;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;


public class Queen extends Piece{
	
	public Queen(int f, int r, boolean c) {
        file = f;
        rank = r;
        color = c;
        hasMoved = false;
        name = "Queen";
        symbol = 'Q';
    }

	@Override
    public List<Move> getLegalMoves(Board curBoard) {
        updateLegalMoves(curBoard.getBoard());
		return legalMoves;
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
					Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank, 'Q');
					legalMoves.add(newMove);
					checkRank++ ;
					checkFile++;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank, 'Q');
						legalMoves.add(newMove);
					}
				}
			}

			//Direction 2
			checkRank = startRank + 1;
			checkFile = startFile - 1;
			blocked = false;
			while(!blocked)
			{
				if(board[checkFile][checkRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank, 'Q');
					legalMoves.add(newMove);
					checkRank++;
					checkFile--;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank, 'Q');
						legalMoves.add(newMove);
					}
				}
			}

			//Direction 3
			checkRank = startRank - 1;
			checkFile = startFile + 1;
			blocked = false;
			while(!blocked)
			{
				if(board[checkFile][checkRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank, 'Q');
					legalMoves.add(newMove);
					checkRank--;
					checkFile++;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank, 'Q');
						legalMoves.add(newMove);
					}
				}
			}

			//Direction 4
			checkRank = startRank - 1;
			checkFile = startFile - 1;
			blocked = false;
			while(!blocked)
			{
				if(board[checkFile][checkRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank, 'Q');
					legalMoves.add(newMove);
					checkRank--;
					checkFile--;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, checkRank, 'Q');
						legalMoves.add(newMove);
					}
				}
			}

			//Direction 5
			checkFile = startFile + 1;
			blocked = false;
			while(!blocked)
			{
				if(board[checkFile][startRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank, 'Q');
					this.legalMoves.add(newMove);
					checkFile++;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank, 'Q');
						this.legalMoves.add(newMove);
					}
				}
			}

			//Direction 6
			checkFile = startFile - 1;
			blocked = false;
			while(!blocked)
			{
				if(board[checkFile][startRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank, 'Q');
					this.legalMoves.add(newMove);
					checkFile--;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank, 'Q');
						this.legalMoves.add(newMove);
					}
				}
			}

			//Direction 7
			checkRank = startRank + 1;
			blocked = false;
			while(!blocked)
			{
				if(board[checkFile][startRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank, 'Q');
					this.legalMoves.add(newMove);
					checkRank++;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank, 'Q');
						this.legalMoves.add(newMove);
					}
				}
			}
			//Direction 8
			checkRank = startRank - 1;
			blocked = false;
			while(!blocked)
			{
				if(board[checkFile][startRank] == null)
				{
					Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank, 'Q');
					this.legalMoves.add(newMove);
					checkRank--;
				}
				else
				{
					blocked = true;
					if(board[checkFile][checkRank].getColor() != this.color)
					{
						Move newMove = new Move(this.color, startFile, startRank, checkFile, startRank, 'Q');
						this.legalMoves.add(newMove);
					}
				}
			}
		}
	}


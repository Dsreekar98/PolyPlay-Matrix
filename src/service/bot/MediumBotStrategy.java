package service.bot;

import java.util.ArrayList;
import java.util.Collections;

import Models.Board;
import Models.CELLSTATE;
import Models.Move;
import Models.Player;

public class MediumBotStrategy implements BotStrategy {
	
	class Pair{
		int x;
		int y;
		public Pair(int x, int y)
		{
			this.x=x;
			this.y=y;
		}
	}
	
	@Override
	public Move makeMove(Board board, Player player) {
		ArrayList<Pair> availableMoves=new ArrayList<>();
		for(int i=0;i<board.getSize();i++)
		{
			for(int j=0;j<board.getSize();j++)
			{
				if(board.getBoard().get(i).get(j).getCellState().equals(CELLSTATE.EMPTY))
				{
					availableMoves.add(new Pair(i,j));
				}
			}
		}
		Collections.shuffle(availableMoves);
		Pair m=availableMoves.get(0);
		board.getBoard().get(m.x).get(m.y).setPlayer(player);
		board.getBoard().get(m.x).get(m.y).setCellState(CELLSTATE.FILLED);
		
		return new Move(m.x,m.y,player);
	}
	

}

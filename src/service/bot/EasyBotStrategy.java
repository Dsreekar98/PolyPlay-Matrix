package service.bot;

import Models.Board;
import Models.CELLSTATE;
import Models.Move;
import Models.Player;
import Models.Cell;
import java.util.*;

import Exceptions.GameOverException;

public class EasyBotStrategy implements BotStrategy {

	@Override
	public Move makeMove(Board board,Player player){
		
		List<List<Cell>> matrix=board.getBoard();
		
		for(int i=0;i<matrix.size();i++)
		{
			for(int j=0;j<matrix.get(i).size();j++)
			{
				if(matrix.get(i).get(j).getCellState().equals(CELLSTATE.EMPTY))
				{
					board.getBoard().get(i).get(j).setPlayer(player);
					board.getBoard().get(i).get(j).setCellState(CELLSTATE.FILLED);
					return new Move(i,j,player);
				}
			}
		}
		
		
		 return null;
	}

}

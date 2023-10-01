package service.bot;

import Exceptions.GameOverException;
import Models.Board;
import Models.Move;
import Models.Player;

public interface BotStrategy {
	public Move makeMove(Board board,Player player) ;
	
}

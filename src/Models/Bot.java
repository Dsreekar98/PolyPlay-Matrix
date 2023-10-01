package Models;

import Exceptions.GameOverException;
import service.bot.BotStrategy;
import service.bot.BotStrategyFactory;

public class Bot extends Player {
	

	private BOTDIFFICULTYLEVEL botDifficultyLevel;
	private BotStrategy botStrategy;

	public Bot(String name, char symbol, PLAYERTYPE playerType, int id, BOTDIFFICULTYLEVEL botDifficultyLevel) {
		super(name, symbol, playerType, id);
		this.botDifficultyLevel = botDifficultyLevel;
	}
	
	@Override
	public Move makeMove(Board board)
	{
		
		BotStrategy botStrategy = BotStrategyFactory.getBotStrategy();
//		Move m1= BotStrategyFactory.getBotStrategy(board).makeMove(board);
//		m1.setPlayer(this);
//		board.getBoard().get(m1.getCell().getRow()).get(m1.getCell().getCol()).setCellState(CELLSTATE.FILLED);
//		board.getBoard().get(m1.getCell().getRow()).get(m1.getCell().getCol()).setPlayer(this);
		return botStrategy.makeMove(board,this);
		
	}
	
	

}

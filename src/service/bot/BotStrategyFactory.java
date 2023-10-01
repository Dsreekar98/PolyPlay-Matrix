package service.bot;

import Models.BOTDIFFICULTYLEVEL;
import Models.Board;

public class BotStrategyFactory {
	
	public static BotStrategy getBotStrategy(BOTDIFFICULTYLEVEL difficultyLevel) {
		switch(difficultyLevel) {
		case EASY:
			return new EasyBotStrategy();
		case MEDIUM:
			return new MediumBotStrategy();
		default:
			return new EasyBotStrategy();
		}	
	}

}

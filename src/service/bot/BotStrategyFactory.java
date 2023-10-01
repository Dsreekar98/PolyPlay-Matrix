package service.bot;

import Models.Board;

public class BotStrategyFactory {
	
	public static BotStrategy getBotStrategy() {
		return new EasyBotStrategy();
	}

}

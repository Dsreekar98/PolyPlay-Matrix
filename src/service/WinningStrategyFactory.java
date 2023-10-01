package service;

public class WinningStrategyFactory {
	public static WinningStrategy getWinningStrategy(WinningStrategies strategy, int dimension) {
		return new OrderOneWinningStrategy(dimension);
		
	}

}

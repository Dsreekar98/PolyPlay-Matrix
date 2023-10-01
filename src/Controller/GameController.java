package Controller;

import java.util.List;

import Models.BOTDIFFICULTYLEVEL;
import Models.Board;
import Models.GAMESTATUS;
import Models.Game;
import Models.Move;
import Models.Player;
import service.WinningStrategies;
import service.WinningStrategy;
import service.WinningStrategyFactory;

public class GameController {
	public Game createGame(int dimension, List<Player> players, WinningStrategies winningStrategy,
			BOTDIFFICULTYLEVEL level) {
		try {
			return Game.builder().setDimension(dimension).setPlayers(players)
					.setWinningStrategy(WinningStrategyFactory.getWinningStrategy(winningStrategy, dimension))
					.setBotStrategy(level).build();
		} catch (Exception e) {
			System.out.println("Could not start the game " + e.getMessage());
		}
		return null;
	}

	public void displayBoard(Game game) {
		game.getCurrentBoard().printBoard();
	}

	public GAMESTATUS getGamestatus(Game game) {
		return game.getGameStatus();
	}

	public Player getGameWinner(Game game) {
		return game.getWinner();
	}

	public Move executeMove(Game game, Player player) {

		Move m1 = player.makeMove(game.getCurrentBoard());
		while (m1 == null) {
			System.out.println("Move already Played, try another move");
			m1 = player.makeMove(game.getCurrentBoard());
		}
		updateGameMoves(game, m1);
		game.setNumberOfSymbol(game.getNumberOfSymbol() + 1);
		updateGameStatus(game);
		updateBoardStates(game);
		return m1;

	}

	private void updateGameStatus(Game game) {
		int numberOfSymbols = game.getNumberOfSymbol();
		int dimension = game.getCurrentBoard().getSize();
		if (numberOfSymbols == (dimension * dimension)) {
			game.setGameStatus(GAMESTATUS.DRAW);
		}

	}

	private void updateGameMoves(Game game, Move move) {
		game.getMoves().add(move);
	}

	private void updateBoardStates(Game game) {
		game.getBoardState().add(new Board(game.getCurrentBoard()));
	}

	public Player checkWinner(Game game, Move lastPlayedMove) {
		Player player = game.getWinningStrategy().checkWinner(game.getCurrentBoard(), lastPlayedMove);
		if (player != null) {
			game.setWinner(player);
			game.setGameStatus(GAMESTATUS.COMPLETED);
			return player;
		}
		return null;
	}

	public void undoMove(Game game, Move movePlayed) {
		game.getMoves().remove(game.getMoves().size()-1);
		game.getBoardState().remove(game.getBoardState().size()-1);
		game.setCurrentBoard(game.getBoardState().get(game.getBoardState().size()-1));
		// TODO Auto-generated method stub

	}

	public void replayGame(Game game) {
		for (int i = 0; i < game.getBoardState().size(); i++) {
			game.getBoardState().get(i).printBoard();
		}
		// TODO

	}

}

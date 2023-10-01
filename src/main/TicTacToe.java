package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Controller.GameController;
import Models.BOTDIFFICULTYLEVEL;
import Models.Bot;
import Models.GAMESTATUS;
import Models.Game;
import Models.Move;
import Models.PLAYERTYPE;
import Models.Player;
import service.WinningStrategies;

public class TicTacToe {
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);

		GameController gameController = new GameController();
		System.out.println("Enter the dimension of the game");
		int dimension = sc.nextInt();

		System.out.println("Will there be any bot in the game? Y/N");
		String isBotPresent = sc.next();

		List<Player> players = new ArrayList<>();
		int iteratorNumber = dimension - 1;

		if (isBotPresent.equalsIgnoreCase("Y")) {
			iteratorNumber = iteratorNumber - 1;
		}

		for (int i = 1; i <= iteratorNumber; i++) {
			System.out.println("Name of the player " + i);
			String name = sc.next();

			System.out.println("Symbol of the player " + i);
			String symbol = sc.next();

			players.add(new Player(name, symbol.charAt(0), PLAYERTYPE.HUMAN, i));

		}

		if (isBotPresent.equalsIgnoreCase("Y")) {
			System.out.println("Name of the Bot ");
			String name = sc.next();

			System.out.println("Symbol of the Bot ");
			String symbol = sc.next();

			players.add(new Bot(name, symbol.charAt(0), PLAYERTYPE.BOT, dimension, BOTDIFFICULTYLEVEL.EASY));
		}

		Collections.shuffle(players);

		Game game = gameController.createGame(dimension, players, WinningStrategies.ORDERONE_WINNINGSTRATEGY);
		int playerIndex = -1;

		while (gameController.getGamestatus(game).equals(GAMESTATUS.INPROGRESS)) {
			String isUndoRequired = "NO";
			playerIndex++;
			playerIndex = playerIndex % players.size();
			if (!players.get(playerIndex).getPlayerType().equals(PLAYERTYPE.BOT)) {
				System.out.println("Current board status");
				gameController.displayBoard(game);
			}
			Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
			if (!players.get(playerIndex).getPlayerType().equals(PLAYERTYPE.BOT)) {
				System.out.println("Do you want to undo your move? Y/N");
				isUndoRequired = sc.next();
			}

			if (isUndoRequired.equalsIgnoreCase("Y")) {
				gameController.undoMove(game, movePlayed);
			}
			Player winner = gameController.checkWinner(game, movePlayed);
			if (winner != null) {

				System.out.println("Winner is: " + winner.getName());
				break;
			}
		}
		if(gameController.getGamestatus(game).equals(GAMESTATUS.DRAW))
		{
			System.out.println("DRAW");
		}
		System.out.println("Final Board status");
		gameController.displayBoard(game);
		System.out.println("Do you want a reply? Y/N");
		String isReply = sc.next();

		// TODO

	}

}

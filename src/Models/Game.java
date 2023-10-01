package Models;

import java.util.*;

import Exceptions.InvalidBotCountException;
import Exceptions.invalidBoardSizeException;
import Exceptions.invalidPlayerNumber;
import Exceptions.invalidSymbolException;
import service.OrderOneWinningStrategy;
import service.WinningStrategy;

public class Game {
	private Board currentBoard;
	private List<Player> players;
	private Player currentPlayer;
	private GAMESTATUS gameStatus;
	private Player winner;
	private List<Move> moves;
	private List<Board> boardState;
	private WinningStrategy winningStrategy;
	private BotPlayingStrategy botPlayingStrategy;
	private int numberOfSymbol;

	public static Builder builder() {
		return new Builder();
	}

	private Game(Board currentBoard, List<Player> players, WinningStrategy winningStrategy) {
		super();
		this.currentBoard = currentBoard;
		this.players = players;
		this.gameStatus = GAMESTATUS.INPROGRESS;
		this.moves = new ArrayList<Move>();
		this.boardState = new ArrayList<Board>();
		this.winningStrategy = winningStrategy;
		this.numberOfSymbol=0;
	}
	
		

	public int getNumberOfSymbol() {
		return numberOfSymbol;
	}

	public void setNumberOfSymbol(int numberOfSymbol) {
		this.numberOfSymbol = numberOfSymbol;
	}

	public void setGameStatus(GAMESTATUS gameStatus) {
		this.gameStatus = gameStatus;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public void setBoardState(List<Board> boardState) {
		this.boardState = boardState;
	}

	public Board getCurrentBoard() {
		return currentBoard;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public GAMESTATUS getGameStatus() {
		return gameStatus;
	}

	public Player getWinner() {
		return winner;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public List<Board> getBoardState() {
		return boardState;
	}

	public WinningStrategy getWinningStrategy() {
		return winningStrategy;
	}

	public BotPlayingStrategy getBotPlayingStrategy() {
		return botPlayingStrategy;
	}



	public static class Builder {
		private int dimension;
		private List<Player> players;
		private WinningStrategy winningStrategy;

		public Builder setDimension(int dimension) {
			this.dimension = dimension;
			return this;
		}

		public Builder setPlayers(List<Player> players) {
			this.players = players;
			return this;

		}

		public Builder setWinningStrategy(WinningStrategy winningStrategy) {
			this.winningStrategy = winningStrategy;
			return this;
		}

		public void validateBotCount() throws InvalidBotCountException {
			int botCount = 0;
			for (Player p : players) {
				if (p.getPlayerType().equals(PLAYERTYPE.BOT)) {
					botCount++;
				}
			}
			if (botCount > 1) {
				throw new InvalidBotCountException("More than 1 Bots");
			}
		}
		
		public void validatePlayerNumber() throws invalidPlayerNumber {
			if(players.size()!=dimension-1)
			{
				throw new invalidPlayerNumber("invalid Player Number");
			}
		}

		public void validateBoardSize() throws invalidBoardSizeException {
			if (dimension < 3 || dimension > 10) {
				throw new invalidBoardSizeException("invalid Board Size");
			}
		}
		
		public void validateDuplicateSymbol() throws invalidSymbolException {
			HashSet<Character> hs=new HashSet<>();
			for(Player p1:players)
			{
				hs.add(p1.getSymbol());
			}
			if(hs.size()!=players.size())
			{
				throw new invalidSymbolException("duplicate Exception");
			}
		}
		
		public void validate() throws Exception {
			validateBoardSize();
			validateBotCount();
			validateDuplicateSymbol();
			validatePlayerNumber();
		}
		
		public Game build() throws Exception{
			validate();
			return new Game(new Board(dimension),players,new OrderOneWinningStrategy(dimension));
		}

	}

}

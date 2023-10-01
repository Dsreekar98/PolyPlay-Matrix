package Models;

import java.util.Scanner;

import Exceptions.MovePlayedException;

public class Player {
	private String name;
	private char symbol;
	private PLAYERTYPE playerType;
	private int id;

	
	public Player(String name, char symbol, PLAYERTYPE playerType, int id) {
		super();
		this.name = name;
		this.symbol = symbol;
		this.playerType = playerType;
		this.id = id;
	}
	public Player(Player p1)
	{
		super();
		this.name=p1.getName();
		this.symbol=p1.getSymbol();
		this.playerType=p1.getPlayerType();
		this.id=p1.getId();
	}
	

	public Player() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public PLAYERTYPE getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PLAYERTYPE playerType) {
		this.playerType = playerType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Move makeMove(Board board) {
		Scanner sc = new Scanner(System.in);
		System.out.println(this.getName()+" enter row for your move");
		int row = sc.nextInt();
		while(row>=board.getSize())
		{
			System.out.println("Cell Out of bound, re-enter the move");
			row=sc.nextInt();
			
		}
		System.out.println(this.getName()+" enter col for your move");
		int col = sc.nextInt();
		while(row>=board.getSize())
		{
			System.out.println("Cell Out of bound, re-enter the move");
			col=sc.nextInt();
			
		}

		if (board.getBoard().get(row).get(col).getCellState() == CELLSTATE.EMPTY) {
			board.getBoard().get(row).get(col).setCellState(CELLSTATE.FILLED);
			board.getBoard().get(row).get(col).setPlayer(this);
			return new Move(row, col,this);
		}
		return null;
		//throw new MovePlayedException("Move already Played");
	}

}

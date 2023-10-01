package Models;

public class Cell {

	private CELLSTATE cellState;
	private Player player;
	private int row;
	private int col;
	public Cell(int row, int col) {
		super();
		this.cellState = cellState.EMPTY;
		this.row = row;
		this.col = col;
	}
	
	
	public CELLSTATE getCellState() {
		return cellState;
	}


	public void setCellState(CELLSTATE cellState) {
		this.cellState = cellState;
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public void display() {
		if(player==null)
		{
			System.out.print("| |");
		}
		else
		{
			System.out.print("|"+player.getSymbol()+"|");
		}
	}
	
}

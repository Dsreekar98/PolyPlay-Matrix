package Models;

public class Move {
	private Cell cell;
	private Player player;
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Move(int x, int y)
	{
		this.cell=new Cell(x,y);
	}
	public Move(int x, int y,Player player)
	{
		this.cell=new Cell(x,y);
		this.player=player;
	}
}

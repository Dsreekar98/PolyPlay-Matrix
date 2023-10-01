package Models;
import java.util.*;
public class Board {
	private int size;
	private List<List<Cell>> board;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<List<Cell>> getBoard() {
		return board;
	}
	public void setBoard(List<List<Cell>> board) {
		this.board = board;
	}
	public Board(int size) {
		this.size = size;
		
		this.board=new ArrayList<>();
		for(int i=0;i<size;i++)
		{
			this.board.add(new ArrayList<>());
			for(int j=0;j<size;j++)
			{
				this.board.get(i).add(new Cell(i,j));
			}
		}
	}
	
	public void printBoard() {
		for(int i=0;i<size;i++)
		{
			List<Cell> cells=board.get(i);
			for(Cell c:cells) {
				c.display();
			}
			System.out.println();
		}
		
	}
	public Board(Board b1)
	{
		this.board=b1.board;
		this.size=b1.size;
	}

}

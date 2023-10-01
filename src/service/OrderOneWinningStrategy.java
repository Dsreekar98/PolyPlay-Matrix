package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Models.Board;
import Models.Move;
import Models.Player;

public class OrderOneWinningStrategy implements WinningStrategy{
	
	private int dimension;
	private List<HashMap<Character, Integer>> rowMap;
	private List<HashMap<Character,Integer>> colMap;
	private HashMap<Character,Integer> leftDiagonal;
	private HashMap<Character,Integer> rightDiagonal;
	private HashMap<Character,Integer> edgeMap;
	
	public OrderOneWinningStrategy(int dimension) {
		this.dimension=dimension;
		rowMap=new ArrayList<>();
		colMap=new ArrayList<>();
		leftDiagonal=new HashMap<>();
		rightDiagonal=new HashMap<>();
		edgeMap=new HashMap<>();
		for(int i=0;i<dimension;i++)
		{
			rowMap.add(new HashMap<>());
			colMap.add(new HashMap<>());
		}
	}

	@Override
	public Player checkWinner(Board board, Move lastMove) {
		rowMap.get(lastMove.getCell().getRow()).
		put(lastMove.getPlayer().getSymbol(),rowMap.get(lastMove.getCell().getRow()).getOrDefault(lastMove.getPlayer().getSymbol(), 0)+1);
		
		//System.out.println("row count "+rowMap.get(lastMove.getCell().getRow()).get(lastMove.getPlayer().getSymbol()));
		if(rowMap.get(lastMove.getCell().getRow()).get(lastMove.getPlayer().getSymbol())==dimension)
		{
			return lastMove.getPlayer();
		}
		
		colMap.get(lastMove.getCell().getCol()).
		put(lastMove.getPlayer().getSymbol(),colMap.get(lastMove.getCell().getCol()).getOrDefault(lastMove.getPlayer().getSymbol(), 0)+1);
		//System.out.println("col count "+colMap.get(lastMove.getCell().getCol()).get(lastMove.getPlayer().getSymbol()));
		if(colMap.get(lastMove.getCell().getCol()).get(lastMove.getPlayer().getSymbol())==dimension)
		{
			return lastMove.getPlayer();
		}
		
		if(lastMove.getCell().getCol()==lastMove.getCell().getRow())
		{
			leftDiagonal.put(lastMove.getPlayer().getSymbol(), leftDiagonal.getOrDefault(lastMove.getPlayer().getSymbol(), 0)+1);
			if(leftDiagonal.get(lastMove.getPlayer().getSymbol())==dimension)
			{
				return lastMove.getPlayer();
			}
		}
		
		if((lastMove.getCell().getCol()+lastMove.getCell().getRow())==dimension-1)
		{
			rightDiagonal.put(lastMove.getPlayer().getSymbol(), rightDiagonal.getOrDefault(lastMove.getPlayer().getSymbol(), 0)+1);
			if(rightDiagonal.get(lastMove.getPlayer().getSymbol())==dimension)
			{
				return lastMove.getPlayer();
			}
		}
		if(lastMove.getCell().getRow()==0 || lastMove.getCell().getRow()==dimension-1)
		{
			if(lastMove.getCell().getCol()==0 || lastMove.getCell().getCol()==dimension-1)
			{
				edgeMap.put(lastMove.getPlayer().getSymbol(),edgeMap.getOrDefault(lastMove.getPlayer().getSymbol(),0)+1);
				if(edgeMap.get(lastMove.getPlayer().getSymbol())==4)
				{
					return lastMove.getPlayer();
				}
					
			}
		}
			
		return null;
	}
	

}

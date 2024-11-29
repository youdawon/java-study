package com.algorithms;

public class Scoreboard {

	private int numEntries = 0;

	private GameEntry[] board;

	public Scoreboard(int capacity){
		board = new GameEntry[capacity];
	}

	private GameEntry[] getBoard(){
		return board;
	}

	public void add(GameEntry e){

		int newScore = e.getScore();

		if(numEntries < board.length || newScore > board[numEntries-1].getScore()){
			if(numEntries < board.length)
				numEntries++;

			int j = numEntries - 1;

			while(j>0 && board[j-1].getScore() < newScore){
				board[j] = board[j-1];
				j--;
			}
			board[j] = e;
		}
	}

	public GameEntry remove(int i){

		if(i < 0 || i > board.length)
			throw new ArrayIndexOutOfBoundsException();

		GameEntry temp = board[i];
		for(int j=i; j<numEntries-1; j++){
			board[j] = board[j+1];
		}

		board[i] = null;
		numEntries -= 1;

		return board[i];
	}

	public static void main(String[] args){
		Scoreboard scoreboard = new Scoreboard(10);

		scoreboard.add(new GameEntry("Micky", 600));
		scoreboard.add(new GameEntry("Jack", 630));
		scoreboard.add(new GameEntry("Paul", 700));
		scoreboard.add(new GameEntry("Laura", 1000));



		for(GameEntry ge : scoreboard.getBoard()){
			if(ge != null)
				System.out.println(ge.toString());
		}

		scoreboard.remove(2);

		for(GameEntry ge : scoreboard.getBoard()){
			if(ge != null)
				System.out.println("after removing : "  + ge.toString());
		}
	}

}

package com.bbc.bowling;

import java.util.HashMap;
import java.util.List;


public class Player {
	
	private String name;
    private BowlingGame playerGame;
	private int totalScore;
	private HashMap<Integer, List<Integer>> frameThrows = new HashMap<Integer, List<Integer>>();
	
	public Player(){
		
	}
	
	public Player(String name, BowlingGame playerGame, int totalScore, HashMap<Integer, List<Integer>> frameThrows){
		this.name = name;
		this.playerGame = playerGame;
		this.totalScore = totalScore;
		this.frameThrows = frameThrows;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public BowlingGame getGame(){
		return playerGame;
	}
	
	public void setGame(BowlingGame playerGame){
		this.playerGame = playerGame;
	}
	
	public int getTotalScore(){
		return totalScore;
	}
	
	public void setTotalScore(int totalScore){
		this.totalScore = totalScore;
	}
	
	public HashMap<Integer, List<Integer>> getFrameThrows(){
		return frameThrows;
	}
	
	public void setFrameThrows(HashMap<Integer, List<Integer>> frameThrows){
		this.frameThrows = frameThrows;
	}
	

}


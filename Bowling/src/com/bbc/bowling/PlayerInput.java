package com.bbc.bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class PlayerInput {
	
	public static void main(String args[]){
		
		Scanner userInput = new Scanner(System.in);
		
		//initialise a hashmap that will contain player names
		HashMap<Integer, Player> players = new HashMap<Integer, Player>();
		
		//initialise an array of frames
		int[] frameArray = {2, 3, 4, 5, 6, 7, 8, 9};
		
		//asks the user how many players for this bowling game
		int numPlayers;
		System.out.println("How many players?");
		
		while(true){
			//if the user enters a non-integer value an error message appears
			if(!userInput.hasNextInt()){
				System.out.println("That is not a number");
				userInput.next();
				continue;
			}
			

			
			numPlayers = userInput.nextInt();
			
			//if the user enters a number below 0 or above 6 an error message appears
			if(numPlayers > 6 || numPlayers < 0){
				System.out.println("Maximum of 6 players per game");
				continue;
			}
			break;
		}
		

	
		
		//asks for the names of all the players and adds them to the list players
			
		for(int i=1;i <= numPlayers;i++){
			String tempName;
			Player tempPlayer = new Player();
			System.out.println("Player " + i + ":");
				
			tempName = userInput.next();
			tempPlayer.setName(tempName);
			players.put(i,tempPlayer);
		}
		
		//frame 1 scores
		for(int key : players.keySet()){
			Player temp = new Player();
					
			BowlingGame g = new BowlingGame();
			
			HashMap<Integer, List<Integer>> frameThrows = new HashMap<Integer, List<Integer>>();
			List<Integer> currentFrameThrows = new ArrayList<Integer>();
			
			int firstBowl;
			int secondBowl;

			temp = players.get(key);
			String playerName = temp.getName();
			
		    //asks the player to enter the score for their first throw in frame 1
			System.out.println(playerName + " frame 1 first bowl score:");
			
			while(true){
				//if the user enters a non-integer value an error message appears
				if(!userInput.hasNextInt()){
					System.out.println("That is not a number");
					userInput.next();
					continue;
				}
								
				firstBowl = userInput.nextInt();
				
				//if the user enters a number below 0 or above 10 an error message appears
				if(firstBowl < 0 || firstBowl > 10){
					System.out.println("Not a valid score");
					continue;
				}
				break;
			}

			g.add(firstBowl);
			currentFrameThrows.add(firstBowl);		
			
			//if the first throw is not a strike then the program asks the user to enter the score for the second throw
			if (firstBowl != 10){
			   System.out.println(playerName + " frame 1 second bowl score:"); 
			   
			   while(true){
					//if the user enters a non-integer value an error message appears
					if(!userInput.hasNextInt()){
						System.out.println("That is not a number");
						userInput.next();
						continue;
					}
									
					secondBowl = userInput.nextInt();
					
					//if the user enters a number below 0 or above the difference between 10 and the first throw score, an error message appears
					if(secondBowl < 0 || secondBowl > (10 - firstBowl)){
						System.out.println("Not a valid score");
						continue;
					}
					break;
				}
			   
			   g.add(secondBowl);
			   currentFrameThrows.add(secondBowl);
			}
					
			frameThrows.put(1, currentFrameThrows);		
					
			temp.setGame(g);
			temp.setFrameThrows(frameThrows);
		    players.put(key, temp);
					
					

					
		}
		
		//print scoreboard
		System.out.println("Scoreboard: ");
		
		for(int key : players.keySet()){
			
			Player currentPlayer = new Player();
			
			BowlingGame currentGame = new BowlingGame();
			
			HashMap<Integer, List<Integer>> frameThrows = new HashMap<Integer, List<Integer>>();
			List<Integer> frame1Throws = new ArrayList<Integer>();
			
			currentPlayer = players.get(key);
			currentGame = currentPlayer.getGame();
			frameThrows = currentPlayer.getFrameThrows();
			frame1Throws = frameThrows.get(1);
			
	       
			//if player doesn't strike or score a spare
			if(frame1Throws.size() != 1 && (frame1Throws.get(0) + frame1Throws.get(1) != 10)){
				
				String playerName = currentPlayer.getName();
				
			    //prints the players first and second throw score as well as the accumulated score
				System.out.println(" ");
			    System.out.println(playerName.toUpperCase());
			    System.out.printf("%-15s %15s %15s %15s %n", "       ", "First Throw", "Second Throw", "Current Score");
			    System.out.println("----------------------------------------------------------------");
			    System.out.printf("%-15s %15s %15s %15s %n", "Frame 1", frame1Throws.get(0), frame1Throws.get(1), currentGame.score());
			   
		    }
			
			else{
				
				String playerName = currentPlayer.getName();
				
				System.out.println(" ");
				System.out.println(playerName.toUpperCase());
				
				//if player strikes
				if(frame1Throws.size() == 1){
					System.out.printf("%-15s %15s %15s %15s %n", "       ", "First Throw","Second Throw", "Current Score");
					System.out.println("----------------------------------------------------------------");
					System.out.printf("%-15s %15s %15s %15s %n", "Frame 1", frame1Throws.get(0), "-", "-");
				}
				
				//if player scores a spare
				if(frame1Throws.size() == 2){
					System.out.printf("%-15s %15s %15s %15s %n", "       ", "First Throw", "Second Throw", "Current Score");
					System.out.println("----------------------------------------------------------------");
					System.out.printf("%-15s %15s %15s %15s %n", "Frame 1", frame1Throws.get(0), frame1Throws.get(1), "-");
					
				}
				
				
				
			}
		}
		
		
		// frame 2 - 9 scores
		for(int i : frameArray){
			for(int key : players.keySet()){
				Player temp = new Player();
				
				BowlingGame g = new BowlingGame();
				
				HashMap<Integer, List<Integer>> frameThrows = new HashMap<Integer, List<Integer>>();
				List<Integer> currentFrameThrows = new ArrayList<Integer>();
				
				int firstBowl;
				int secondBowl;

				temp = players.get(key);
				g = temp.getGame();
				String playerName = temp.getName();
				frameThrows = temp.getFrameThrows();
				
				//asks the player to enter the first throw score for frame i
				System.out.println(playerName + " frame " + i + " first bowl score:"); 
				
				while(true){
					//if the user enters a non-integer value an error message appears
					if(!userInput.hasNextInt()){
						System.out.println("That is not a number");
						userInput.next();
						continue;
					}
					

					
					firstBowl = userInput.nextInt();
					
					//if the user enters a number below 0 or above 10 an error message appears
					if(firstBowl > 10 || firstBowl < 0){
						System.out.println("Not a valid score, must be between 0 and 10");
						continue;
					}
					break;
				}
				
				
				
				g.add(firstBowl);
				currentFrameThrows.add(firstBowl);
				
				//if the player didn't score a strike, the program asks for the score for the second throw in frame i
				if (firstBowl != 10){
				   System.out.println(playerName + " frame " + i + " second bowl score:"); 
				   
				   while(true){
						//if the user enters a non-integer value an error message appears
						if(!userInput.hasNextInt()){
							System.out.println("That is not a number");
							userInput.next();
							continue;
						}
						

						
						secondBowl = userInput.nextInt();
						
						//if the user enters a number below 0 or above the difference between 10 and the first throw's score, an error message appears
						if(secondBowl < 0 || secondBowl > (10 - firstBowl)){
							System.out.println("Not a valid score");
							continue;
						}
						break;
					}
				   
				   g.add(secondBowl);
				   currentFrameThrows.add(secondBowl);
				}
				
				
				frameThrows.put(i, currentFrameThrows);
				
				
				temp.setGame(g);
				temp.setFrameThrows(frameThrows);
				players.put(key, temp);
				
			
			}
			
			//prints scoreboard after every frame
			
			System.out.println("Scoreboard: ");
			
			for(int key : players.keySet()){
				Player currentPlayer = new Player();
				
				BowlingGame currentGame = new BowlingGame();
				
				HashMap<Integer, List<Integer>> frameThrows = new HashMap<Integer, List<Integer>>();
				List<Integer> currentFrameThrows = new ArrayList<Integer>();
				List<Integer> oldFrameThrows = new ArrayList<Integer>();
				
				currentPlayer = players.get(key);
				currentGame = currentPlayer.getGame();
				frameThrows = currentPlayer.getFrameThrows();
				currentFrameThrows = frameThrows.get(i);
				
				String playerName = currentPlayer.getName();
				
				//prints the player's name in capital letters
				System.out.println(" ");
				System.out.println(playerName.toUpperCase());
				
				//prints the scores for the frames leading up to the current frame
				for(int j=1;j < i; j++){
					oldFrameThrows = frameThrows.get(j);
					
					if(j==1){
					    System.out.printf("%-15s %15s %15s %15s %n", "       ", "First Throw", "Second Throw", "Score");
					}
					System.out.println("----------------------------------------------------------------");
					
					if(oldFrameThrows.size() != 1){
						System.out.printf("%-15s %15s %15s %15s %n", "Frame " + j, oldFrameThrows.get(0), oldFrameThrows.get(1), currentGame.frameScore(j));
						
					}
					else{
						System.out.printf("%-15s %15s %15s %15s %n", "Frame " + j, oldFrameThrows.get(0), "-", currentGame.frameScore(j));
					}
					
					
					
				}
				
				//if player doesn't strike or score a spare
				if(currentFrameThrows.size() != 1 && (currentFrameThrows.get(0) + currentFrameThrows.get(1) != 10)){
					
				    //prints the players first and second throw score as well as the accumulated score
					System.out.println("----------------------------------------------------------------");
					System.out.printf("%-15s %15s %15s %15s %n", "Frame " + i, currentFrameThrows.get(0), currentFrameThrows.get(1), currentGame.score());
				
			    }
				
				else{
					
										
					//if player strikes
					if(currentFrameThrows.size() == 1){
						
						
						System.out.println("----------------------------------------------------------------");
						System.out.printf("%-15s %15s %15s %15s %n", "Frame " + i, currentFrameThrows.get(0), "-", "-");
						
				
					
					}	
					//if player scores a spare
					if(currentFrameThrows.size() == 2){
						
						System.out.println("----------------------------------------------------------------");
						System.out.printf("%-15s %15s %15s %15s %n", "Frame " + i, currentFrameThrows.get(0), currentFrameThrows.get(1), "-");
						
					
					}
				}
			}
		}
		
		
		//frame 10 scores
		for(int key : players.keySet()){
			Player temp = new Player();
			
			BowlingGame g = new BowlingGame();
			
			HashMap<Integer, List<Integer>> frameThrows = new HashMap<Integer, List<Integer>>();
			List<Integer> frame10Throws = new ArrayList<Integer>();
			
			int firstBowl;
			int secondBowl;

			temp = players.get(key);
			g = temp.getGame();
			String playerName = temp.getName();
			frameThrows = temp.getFrameThrows();
			
			//the program asks for the player's frame 10 first throw score
			System.out.println(playerName + " frame 10 first bowl score:");  
			
			while(true){
				//if the user enters a non-integer value an error message appears
				if(!userInput.hasNextInt()){
					System.out.println("That is not a number");
					userInput.next();
					continue;
				}
				

				
				firstBowl = userInput.nextInt();
				
				//if the user enters a number below 0 or above 10 an error message appears
				if(firstBowl < 0 || firstBowl > 10){
					System.out.println("Not a valid score, score must be between 0 and 10");
					continue;
				}
				break;
			}
			
						
			g.add(firstBowl);
			frame10Throws.add(firstBowl);
			
			//if the player didn't score a strike, the program asks for the frame 10 second throw score
			if (firstBowl != 10){
			   System.out.println(playerName + " frame 10 second bowl score:"); //add strike check
			   
			   while(true){
					//if the user enters a non-integer value an error message appears
					if(!userInput.hasNextInt()){
						System.out.println("That is not a number");
						userInput.next();
						continue;
					}
					

					
					secondBowl = userInput.nextInt();
					
					//if the user enters a number below 0 or above the difference between 10 and the first throw score an error message appears
					if(secondBowl < 0 || secondBowl > (10 - firstBowl)){
						System.out.println("Not a valid score");
						continue;
					}
					break;
				}
			   
			   g.add(secondBowl);
			   frame10Throws.add(secondBowl);
			   
			 		   
			   //if player scores a spare in frame 10 they are awarded one bonus shot
			   if(firstBowl != 10 && (firstBowl + secondBowl) == 10){
				   
				   int spareBonus;
				   
				   System.out.println(playerName + " frame 10 spare bonus:");
				   
				   while(true){
						//if the user enters a non-integer value an error message appears
						if(!userInput.hasNextInt()){
							System.out.println("That is not a number");
							userInput.next();
							continue;
						}
											
						spareBonus = userInput.nextInt();
						
						//if the user enters a number below 0 or above 10 an error message appears
						if(spareBonus < 0 || spareBonus > 10){
							System.out.println("Not a valid score");
							continue;
						}
						break;
					}
				   
				   g.add(spareBonus);
				   frame10Throws.add(spareBonus);
			   }
			}
			
			//if the player scores a strike in the final frame then they get 2 extra shots
			if (firstBowl == 10) {
				int bonus1;
				int bonus2;
				System.out.println(playerName + " frame 10 strike bonus 1:");
				
				while(true){
					//if the user enters a non-integer value an error message appears
					if(!userInput.hasNextInt()){
						System.out.println("That is not a number");
						userInput.next();
						continue;
					}
										
					bonus1 = userInput.nextInt();
					
					//if the user enters a number below 0 or above 6 an error message appears
					if(bonus1 < 0 || bonus1 > 10){
						System.out.println("Not a valid score, score must be between 0 and 10");
						continue;
					}
					break;
				}

				g.add(bonus1);
				frame10Throws.add(bonus1);
				
				System.out.println(playerName + " frame 10 strike bonus 2:");
				
				while(true){
					//if the user enters a non-integer value an error message appears
					if(!userInput.hasNextInt()){
						System.out.println("That is not a number");
						userInput.next();
						continue;
					}
					

					
					bonus2 = userInput.nextInt();
					
					//if the user enters a number below 0 or above 10 an error message appears
					if(bonus2 < 0 || bonus2 > 10){
						System.out.println("Not a valid score, score must be between 0 and 10");
						continue;
					}
					break;
				}
				
				g.add(bonus2);
				frame10Throws.add(bonus2);
			}

			
			frameThrows.put(10, frame10Throws);
			
			temp.setGame(g);
			temp.setFrameThrows(frameThrows);
			players.put(key, temp);
			
			
		}

		//prints final scoreboard
		
		System.out.println("Scoreboard: ");
		
        for(int key : players.keySet()){
        				
			Player currentPlayer = new Player();
			
			BowlingGame currentGame = new BowlingGame();
			
			HashMap<Integer, List<Integer>> frameThrows = new HashMap<Integer, List<Integer>>();
			List<Integer> frame10Throws = new ArrayList<Integer>();
			List<Integer> oldFrameThrows = new ArrayList<Integer>();
			
			currentPlayer = players.get(key);
			currentGame = currentPlayer.getGame();
			frameThrows = currentPlayer.getFrameThrows();
			frame10Throws = frameThrows.get(10);
			
			String playerName = currentPlayer.getName();
			
			System.out.println(" ");
			System.out.println(playerName.toUpperCase());
			
			//prints the scores for the frames leading up to the current frame
			for(int j=1;j < 10; j++){
				oldFrameThrows = frameThrows.get(j);
				
				if(j==1){
				    System.out.printf("%-15s %15s %15s %15s %n", "      ", "First Throw", "Second Throw", "Score");
				}
				System.out.println("-----------------------------------------------------------------------");
				
				
				if(oldFrameThrows.size() != 1){
					System.out.printf("%-15s %15s %15s %15s %n", "Frame " + j, oldFrameThrows.get(0), oldFrameThrows.get(1), currentGame.frameScore(j));
				
				}
				else{
					System.out.printf("%-15s %15s %15s %15s %n", "Frame " + j, oldFrameThrows.get(0), "-", currentGame.frameScore(j));
				}
	
				
			}
	       
			//if player strikes or scores a spare
			if(frame10Throws.size() == 3){
				
			    //prints the players first and second throw score as well as the accumulated score
				
				System.out.printf("%-15s %15s %15s %15s %15s %n", "        ", "           ", "            ", "Third Throw", "Final Score");
				System.out.println("--------------------------------------------------------------------------------");
				System.out.printf("%-15s %15s %15s %15s %15s %n", "Frame 10", frame10Throws.get(0), frame10Throws.get(1), frame10Throws.get(2), currentGame.score());
				
			
			    
			    currentPlayer.setTotalScore(currentGame.score());
		    }
			
			else{
				
				//if player does not strike or score a spare
				if(frame10Throws.size() == 2){
					
					System.out.printf("%-15s %15s %15s %15s %n", "        ", "           ", "            ", "Final Score");
					System.out.println("-----------------------------------------------------------------------");
					System.out.printf("%-15s %15s %15s %15s %n", "Frame 10", frame10Throws.get(0), frame10Throws.get(1), currentGame.score());
					
					
					currentPlayer.setTotalScore(currentGame.score());
				}			
				
				
			}
		}	
		
        int maxScore = 0;
        String winner;
        
        //finds the highest score
        for(int key : players.keySet()){
        	
        	Player playerWin = new Player();
        	
        	playerWin = players.get(key);
        	
        	if(playerWin.getTotalScore() > maxScore){
        		maxScore = playerWin.getTotalScore();
        	}      	
        	
        }
        
        //prints the winner(s) of the bowling game by printing out the name of every player with the maxScore
        System.out.println(" ");
        System.out.println("WINNER: ");
        
        for(int key : players.keySet()){
        	
        	Player playerFinal = new Player();
        	
        	playerFinal = players.get(key);
        	
        	if(playerFinal.getTotalScore() == maxScore){
        		winner = playerFinal.getName();
        		System.out.println(winner.toUpperCase());
        	}
        }
		
	}

}
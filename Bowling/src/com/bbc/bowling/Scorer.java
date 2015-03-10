package com.bbc.bowling;

public class Scorer {
	
	 private int ball;
	 
	 //initialise an array to hold all the player throws (21 is the maximum number of throws a player can bowl in a game)
	 private int[] bowlingThrows = new int[21];
	 private int currentThrow = 0;
	 
	 //adds the throw to the array bowlingThrows
	 public void addThrow(int bowledPins)
	  {
	    bowlingThrows[currentThrow++] = bowledPins;
	  }

	  //returns the score for a particular frame
	  /* 
	   * iterates through all the frames leading up to the one specified
	   * and adds up the score.
	   * If the player scores a strike in a frame then the next two throw's scores need to
	   * be added to that frames scores
	   * If the player bowls a spare in a frame then the next throw's score needs to be 
	   * added to that frame scores
	   * else the score for a frame is only dependent upon the two throws in that frame
	    */
	 
	  public int frameScore(int frame)
	  {
	    ball = 0;
	    int score=0;
	    for (int i = 0; i < frame; i++){
	    	
	        if (strike()){
	            score += 10 + nextTwoBalls();
	        }
	        else if (spare()){
	            score += 10 + nextBall();
	        }
	        else {
	            score += twoBallsInFrame();
	        }
	    }

	    return score;
	  }

	  //checks if the player bowled a strike
	  private boolean strike()
	  {
	    if (bowlingThrows[ball] == 10)
	    {
	      ball++;
	      return true;
	    }
	    return false;
	  }
     
	  //checks if the player bowled a spare
	  private boolean spare()
	  {
	    if ((bowlingThrows[ball] + bowlingThrows[ball+1]) == 10)
	    {
	      ball += 2;
	      return true;
	    }
	    return false;
	  }

	  private int nextTwoBalls()
	  {
	    return bowlingThrows[ball] + bowlingThrows[ball+1];
	  }

	  private int nextBall()
	  {
	    return bowlingThrows[ball];
	  }

	  private int twoBallsInFrame()
	  {
	    return bowlingThrows[ball++] + bowlingThrows[ball++];
	  }

	  

}


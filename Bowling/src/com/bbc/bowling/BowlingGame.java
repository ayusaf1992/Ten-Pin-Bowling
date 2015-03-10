package com.bbc.bowling;


public class BowlingGame {
	
	  private int currentFrame = 0;
	  private boolean firstThrow = true;
	  private Scorer scorer = new Scorer();

	  //returns the current score of the bowling game
	  public int score()
	  {
	    return frameScore(currentFrame);
	  }
    
	  //
	  public void add(int bowledPins)
	  {
	    scorer.addThrow(bowledPins);
	    alterCurrentFrame(bowledPins);
	  }

	  //checks if the first throw is a strike and if so advances frame and it is again a first throw
	  //if the first throw is not a strike the frame is not advanced and it is the second throw
	  private void alterCurrentFrame(int bowledPins)
	  {
	    if (firstThrow == true){
	        if (strikeFrame(bowledPins) == false){
	            firstThrow = false;
	        }
	    }
	    
	    else{
	        firstThrow = true;
	        forwardFrame();
	    }
	  }

	  //checks if player bowled a strike and frame is advanced 
	  private boolean strikeFrame(int bowledPins)
	  {
	    if (bowledPins == 10){
	      forwardFrame();
	      return true;
	    }
	    else {
	      return false;
	    }
	  }  

	  //advances the frame, never exceeding frame 10
	  private void forwardFrame()
	  {
	    currentFrame = Math.min(10, currentFrame + 1);
	  }

	  //returns the score for a specified frame
	  public int frameScore(int frame)
	  {
	    return scorer.frameScore(frame);
	  }

	  
}

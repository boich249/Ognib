import java.util.Random;

public class Board {
	
	final int ROWS = 5;
    final int COLUMNS = 5;
    final int LENGTH = ROWS*COLUMNS;
	final int MIN_VALUE = 1; // represents the minimum value in the board
	final int MAX_VALUE = 100; // represents the maximum value in the board
	int board[] = new int [LENGTH];
	String userName = "Computer", winType = "";
	
	public void Create()
	{
		
	      Random rand = new Random();
	      int randomNumber;    // an integer to be chosen at random
	      boolean isDuplicate; // to indicate is randomNumber has already been picked or not

	      // for every cell i in the board
	      for (int i = 0; i < board.length; ++i)
	      {
	            do
	            {     // Generate a random number between MAX_VALUE and MIN_VALUE inclusive
	                  randomNumber = rand.nextInt((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE;

	                  isDuplicate = false; // Initially, let's assume this is not a duplicate

	                  // check if this random value has already been inserted in the array in a previous cell
	                  for (int j = 0; j < i; ++j) {
	                        if (board[j] == randomNumber) {
	                              isDuplicate = true;
	                        }
	                  }
	            }
	            while (isDuplicate); // pick a new random number until we pick a non-duplicate
	            board[i] = randomNumber;  // this random number is not a duplicate, assign it to the ith cell
	      }

	      // the array now contains all unique random values
	      //for (int i = 0; i < board.length; ++i) {
	        //    System.out.print(board[i] + " ");
	      //}
	      
	      int temp = 0;
			
	      for (int iSwitch = 0; iSwitch < board.length - 1; iSwitch++){
				
	    	  for (int  iScan = iSwitch + 1;  iScan < board.length; iScan++){
					
					if (board[iScan] < board[iSwitch]){
						
						temp = board[iSwitch];
						board[iSwitch] = board[iScan];
						board[iScan] = temp;
					}
	    	  }
	      }
	}
	
	public void Printer()
	{
		System.out.print("\n" + userName + "'s board:");
		
		int count = 0;
		
		for (int j = 0; j < ROWS; j++)
		{
			
			System.out.print("\n ");
			
			for (int i = 0; i < COLUMNS; i++)
			{
				System.out.print("---------");
			}
			
			System.out.print("-\n |");
			
			for (int i = 0; i < COLUMNS; i++, count++)
			{
				if (board[count] < 10)
					System.out.print("    " + board[count] + "   |");
				else if (board[count] < 100)
					System.out.print("   " + board[count] + "   |");
				else if (board[count] >= 100)
					System.out.print("  " + board[count] + "   |");
			}
			
		}
		
		System.out.print("\n -");
		for (int i = 0; i < COLUMNS; i++)
		{
			System.out.print("---------");
		}
		System.out.println();
	
	}
	
	
	public void Call (int call)
	{
		
		for(int i = 0; i < LENGTH; i++)
		{
			if (board[i] == call)
			{
				board[i] = 0;
				break;
			}
		}
	}
	
	
	public boolean Win()
	{
        boolean win = false;
		int eval, eval2;
        
        // evaluates for column win
        
        
        for (int j = 0; j < COLUMNS; j++)
        {
        	eval = 0;
        	
        	for(int i = 0; i < LENGTH; i++)
        	{
        		if (((i + COLUMNS) % COLUMNS) == j)
        			eval += board[i];
        	}
        	if (eval == 0)
        	{
        		win = true;
        		winType = "Column";
        		break;
        	}
        }
        
        
        // evaluates for row win
        
        for (int i = 0; i < LENGTH; i++)
        {
        	eval = 0;
        	
        	if ((i % COLUMNS) == 0)
        	{
        		for(int j = 0; j < COLUMNS; j++)
        		{
        		
        			eval += board[i + j];
        		}
        		
            	if (eval == 0)
            	{
              		win = true;
            		winType += " Row";
            		break;
            	}
            		
        	}

        }
        
        
        // evaluates for diagonal win
        // the variable eval represents top left to bottom right, eval2 from top right to bottom left
        
        
        eval = 0;
        eval2 = 0;
        
        for (int i = 0; i < ROWS; i++ )
        {
        	
        	eval += board[i * ROWS + i];
        	eval2 += board[i * ROWS + (COLUMNS - (i + 1))];
        }
        
    	if (eval == 0 || eval2 == 0)
    	{
    		win = true;
    		winType += " Diagonal";
    	}
    		
    	
    	return win;
	}
	
}

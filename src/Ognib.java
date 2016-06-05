import java.util.Random;
import java.util.Scanner;

public class Ognib {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Board player = new Board();
		Board comp = new Board();
		player.Create();
		comp.Create();
		
		int randomNumber, call;
		Random rand = new Random();
		Scanner userIn = new Scanner(System.in);
		
		
		
		// Welcomes user and asks for user's name
		
		System.out.print("Welcome to Ognib, let's play!\nPlease enter your name: ");
		player.userName = userIn.next();
		
		
		
		
		// THE GAME BEGINS!
		
		
		// Play continues until there is a winner

		gamePlay:
		do
		{
			
			// Displays the user's board
			
			player.Printer();
			
			
			// Comp has 3 turns to call a number
			
			for (int c = 0; c < 3; c++)
			{
				
				do
					randomNumber = rand.nextInt(comp.LENGTH - 1);    // Selects a random index on comp's board that has not already been selected
				while (comp.board[randomNumber] == 0);
				
				call = comp.board[randomNumber];                     // Assigns the value of the chosen index to the variable call
				comp.board[randomNumber] = 0;                        // Assigns the chosen index a value of zero 
				
				player.Call(call);                                   // Checks if the player's board has the called token

				System.out.println("Computer's Value: " + call);     // Displays the comp's call
				
				if (comp.Win() || player.Win())                      // Will end the game if there is a winner
					break gamePlay;
				
			}
			
			
			// Player's turn to call a value
			
			System.out.print(player.userName + "'s Value: ");       // Prompts user to call a value
			call = userIn.nextInt();                                // Assigns the value to the variable call
			
			player.Call(call);                                      // Checks if the player's board has the called token
			comp.Call(call);                                        // Checks if comp's board has the called token
			
			
			
		}
		
		while (!player.Win() && !comp.Win());                       // Play ends when there is a winner
		
		
		// Game play is over
		
		
		userIn.close();                                             // We don't need anymore user input
		
		
		// Displays a message that there is a winner
		
		System.out.println("\n" + player.winType + comp.winType + " win detected...\n\n"
				+ " ======== Final Results! ========");
		
		
		// Displays the final boards, and announces the winner
		
		String winPlayer = "", winComp = "", announce = "";         // Will contain the winning announcements
		
		if (player.Win())
			winPlayer = player.userName + " has a winning board!";
		else
			winComp = "Computer has a winning board!";
		
		
		player.Printer();                                           // Displays the player's final board
		System.out.println(winPlayer);                              // Will display if the player has won
		
		
		comp.Printer();                                             // Displays the comp's final board
		System.out.println(winComp);                                // Will display if the comp has won
		
		
		// Announces the winner
		
		if (comp.Win() && player.Win())                             // If it's a tie
			announce = "Its a tie!!!";
		else if (player.Win())                                      // If the player won
			announce = player.userName + " is the winner!!!";
		else                                                        // If comp won
			announce = "Computer is the winner :(";

		System.out.println(announce);
		
		
		
	}

}

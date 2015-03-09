import java.io.FileNotFoundException;
import java.util.*;

public class MainGame {
	public static void main(String[] args) throws FileNotFoundException {
		Computer c = new Computer();
		Scanner in = new Scanner(System.in);

		boolean gameState = true;
		char playerValue;
		char computerValue = 0;
		
		System.out.println("1: Beginner \n2: Veteran");
		int gameDifficultly = checkInt(1,2);
		
		if(gameDifficultly == 2){
			c.readFile();
		}
		
		
		while (gameState) {
			System.out
					.println("Rock Paper Sciccors\n1: Rock\n2: Paper\n3: Sciccors\n4: Quit");
			int usersChoice = checkInt(1, 4);
			if (usersChoice == 4) {
				gameState = false;
			} else {

				playerValue = c.assignValue(usersChoice);
				c.updateHash(playerValue);
				computerValue = c.beatPredict(c.prediction());

				int winOrLose = c.winLogic(playerValue, computerValue);
				System.out.println("Player's choice " + playerValue
						+ " vs Computer's prediction " + computerValue);
				if (winOrLose == 1) {
					System.out.println("You win!!!");
				} else if (winOrLose == -1) {
					System.out.println("You lose");
				} else {
					System.out.println("Its a tie");
				}
			}

		}
		c.writeFile();
	}

	public static int checkInt(int low, int high) {
		Scanner into = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		while (!valid) {
			if (into.hasNextInt()) {
				validNum = into.nextInt();
				if (validNum >= low && validNum <= high) {
					valid = true;
				} else {
					System.out.print("Invalid- Retry: ");
				}
			} else {
				// clear buffer of junk input
				into.next();
				System.out.print("Invalid input- Retry: ");
			}
		}
		return validNum;
	}

}
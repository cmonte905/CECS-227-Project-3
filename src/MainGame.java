import java.io.FileNotFoundException;
import java.util.*;

/**
 * Project 3: Rock Paper scissors game that uses hashmaps to store players moves
 * for predictions later on
 * 
 * @author Cesar
 */
public class MainGame {
	/**
	 * Main Method of program.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Computer c = new Computer();
		Scanner in = new Scanner(System.in);

		boolean gameState = true;
		char playerValue;
		char computerValue = 0;
		int gamePoints = 0;
		System.out.println("1: Beginner \n2: Veteran");
		int gameDifficultly = checkInt(1, 2);
		// Computer class will have more items in the hashmap if this is
		// selected
		if (gameDifficultly == 2) {
			c.readFile();
		}
		// Keeps the game going until user has chosen to quit
		while (gameState) {
			System.out
					.println("Rock Paper Sciccors\n1: Rock\n2: Paper\n3: Sciccors\n4: Quit");
			int usersChoice = checkInt(1, 4);
			if (usersChoice == 4) {
				gameState = false;
			} else {

				playerValue = c.assignValue(usersChoice);
				computerValue = c.beatPredict(c.prediction());

				c.updateHash(playerValue);
				int winOrLose = c.winLogic(playerValue, computerValue);
				System.out.println("Player's choice " + playerValue
						+ " vs Computer's prediction " + computerValue);
				if (winOrLose == 1) {
					gamePoints++;
					System.out.println("You win!!!\nCurrent wins: "
							+ gamePoints);
				} else if (winOrLose == -1) {
					System.out.println("You lose");
				} else {
					System.out.println("Its a tie");
				}
			}

		}
		// When the game has ended, user has the option to write
		// their data onto a .dat file for better predictions
		// the next time they play and use said file
		System.out.println("Would you like to write patterns to a file?\n y/n");
		char writeFile = in.next().charAt(0);
		if (writeFile == 'y' || writeFile == 'Y') {
			c.writeFile();
		} else {
			System.out.println("Did not save game data");
		}

	}

	/**
	 * Method that checks for a valid input of an integer. Takes lowest possible
	 * and highest possible values and makes sure it is valid
	 * 
	 * @param low
	 *            Lowest possible integer value
	 * @param high
	 *            Highest possible integer value
	 * @return Returns the integer if valid
	 */
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
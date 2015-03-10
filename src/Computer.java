import java.io.*;
import java.util.*;

/**
 * Class that has most of the game's states and checks, such as winning and
 * losing and assigning users input to characters
 */
public class Computer {
	HashMap<Pattern, Integer> patternHash;
	private String holdPat;
	private File gameData = new File("gameData.dat");

	/**
	 * Constructor of computer
	 */
	public Computer() {
		patternHash = new HashMap<Pattern, Integer>();
		holdPat = "";
	}

	/**
	 * Method that picks a random choice for the computer
	 * 
	 * @return Character assignment that was randomly chosen
	 */
	public char randomPredict() {
		Random rand = new Random();
		int cChoice = rand.nextInt(3) + 1;

		return assignValue(cChoice);
	}

	/**
	 * Method that checks the hashmap for patterns. Will only check for a
	 * similar hash set if the string that holds the players moves for this
	 * session is greater that 4. If there is a pattern present, then it returns
	 * that character, if there is no pattern, then it chooses a random
	 * character
	 * 
	 * @return Character that has been predicted to be the players next move
	 */
	public char prediction() {
		if (holdPat.length() < 4) {
			return randomPredict();
		} else {
			int max = -1;
			Pattern p1 = new Pattern(holdPat.substring(holdPat.length() - 3,
					holdPat.length()) + "R");
			Pattern p2 = new Pattern(holdPat.substring(holdPat.length() - 3,
					holdPat.length()) + "P");
			Pattern p3 = new Pattern(holdPat.substring(holdPat.length() - 3,
					holdPat.length()) + "S");
			Pattern pMax = null;

			if (patternHash.containsKey(p1)) {
				if (patternHash.get(p1) > max) {
					max = patternHash.get(p1);
					pMax = p1;
				}
			}
			if (patternHash.containsKey(p2)) {

				if (patternHash.get(p2) > max) {
					max = patternHash.get(p2);
					pMax = p2;
				}
			}
			if (patternHash.containsKey(p3)) {
				if (patternHash.get(p3) > max) {
					max = patternHash.get(p3);
					pMax = p3;
				}
			}
			if (max == -1) {
				return randomPredict();
			} else {
				return pMax.getPattern()[pMax.getPattern().length - 1];
			}
		}

	}

	/**
	 * Method that tries to beat the player by taking a prediction and
	 * countering the move that was given in
	 * 
	 * @param bP
	 *            the players next predicted move
	 * @return Returns a character that beats the predicted move.
	 */
	public char beatPredict(char bP) {

		if (bP == 'R') {
			return 'P';
		} else if (bP == 'P') {
			return 'S';
		} else {
			return 'R';
		}

	}

	/**
	 * Method that gives the users/computers integer choice a character value
	 * 
	 * @param c
	 *            Integer to be converted to a character
	 * @return Letter representation of said choice
	 */
	public char assignValue(int c) {
		char value = 0;
		switch (c) {
		case 1:
			value = 'R';
			break;
		case 2:
			value = 'P';
			break;
		case 3:
			value = 'S';
			break;
		}
		return value;
	}

	/**
	 * Method that adds in players moves into the hashmap if more than four
	 * moves have been made
	 * 
	 * @param Player
	 *            Player's choice
	 */
	public void updateHash(char Player) {

		holdPat += Player;
		//Only writes items into hashmap if user has inputted more than 4 moves
		if (holdPat.length() > 4) {

			holdPat = holdPat.substring(holdPat.length() - 4, holdPat.length());

			Pattern p = new Pattern(holdPat);

			if (patternHash.containsKey(p)) {
				patternHash.put(p, patternHash.get(p) + 1);
			} else {
				patternHash.put(p, 1);
			}
		}
	}

	/**
	 * Method that reads from a .dat file if the user has selected the more
	 * difficult version of the game. Reads previous hashmaps that were created
	 * to have more predictions
	 */
	public void readFile() {
		if (gameData.exists()) {
			try {
				ObjectInputStream intoFile = new ObjectInputStream(
						new FileInputStream(gameData));

				patternHash = (HashMap<Pattern, Integer>) intoFile.readObject();
				intoFile.close();

			} catch (IOException e) {
				System.out.println("Can not read file");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not find the class");
			}
		}
	}

	/**
	 * Method that writes the current hashmap into a .dat file to be read later
	 * on if the next user wishes to make the game more difficulty
	 */
	public void writeFile() {

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(gameData));

			out.writeObject(patternHash);
			out.close();
		} catch (IOException e) {
			System.out.println("Error processing the save.");
		}

	}

	/**
	 * Method that checks to see who wins the round
	 * 
	 * @param Player
	 *            Players choice
	 * @param Comp
	 *            Computers choice
	 * @return Returns an integer to determine the winner
	 */
	public int winLogic(char Player, char Comp) {

		if (Player == 'R' && Comp == 'S') {
			return 1;

		} else if (Player == 'S' && Comp == 'R') {
			return -1;

		} else if (Player == 'R' && Comp == 'P') {
			return -1;

		} else if (Player == 'P' && Comp == 'R') {
			return 1;

		} else if (Player == 'P' && Comp == 'S') {
			return -1;

		} else if (Player == 'S' && Comp == 'P') {
			return 1;

		} else {
			return 0;

		}
	}

}

import java.util.*;

public class MainGame {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		boolean gameState = true;
		char playerValue;
		char computerValue = 0;
		int cChoice = 0;
		System.out
				.println("Rock Paper Sciccors\n1: Rock\n2: Paper\n3: Sciccors");
		int usersChoice = in.nextInt();

		while (gameState) {

			cChoice = rand.nextInt(3) + 1;

			playerValue = assignValue(usersChoice);
			computerValue = assignValue(cChoice);
			boolean validIn = true;
			while (validIn) {
				System.out.println("Would you like to continue? y/n");
				String cont = in.next();
				if (cont.contentEquals("y") || cont.contentEquals("Y")) {
					validIn = false;
				} else {
					validIn = true;
				}
			}

		}
	}

	public static char assignValue(int c) {
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

}

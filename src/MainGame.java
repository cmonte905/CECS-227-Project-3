import java.util.*;

public class MainGame {
	public static void main(String[] args) {
		Computer c = new Computer();
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		boolean gameState = true;
		char playerValue;
		char computerValue = 0;
		int cChoice = 0;
		char cont = ' ';
		String pre = null;
		String prediction = "";
		while (gameState) {
			System.out
					.println("Rock Paper Sciccors\n1: Rock\n2: Paper\n3: Sciccors");
			int usersChoice = in.nextInt();
			cChoice = rand.nextInt(3) + 1;

			playerValue = assignValue(usersChoice);
			computerValue = assignValue(cChoice);
			int winOrLose = winLogic(playerValue, computerValue);
			if (winOrLose == 1) {
				System.out.println("You win!!!");
			} else if (winOrLose == -1) {
				System.out.println("You lose");
			} else {
				System.out.println("Its a tie");
			}
			prediction += playerValue;
			if(prediction.length() > 4){
				for(int i = (prediction.length()-4); i < prediction.length();i++){
					pre += prediction.charAt(i);
					
					pre = null;
				}
				
				
				System.out.println("Will do something");
			}
			

			boolean validIn = true;
			while (validIn) {
				System.out.println("Would you like to continue? y/n");
				 cont = in.next().charAt(0);
				if (cont == 'y' || cont == 'Y') {
					validIn = true;
					validIn = false;
				} else {
					gameState = false;
					validIn = false;
				}
			}
			System.out.println(prediction);
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

	public static int winLogic(char Player, char Comp) {

		if (Player == 'R' && Comp == 'S') {
			return 1;
			// player wins rock beats sci
		} else if (Player == 'S' && Comp == 'R') {
			return -1;
			// computer wins rock beats sci
		} else if (Player == 'R' && Comp == 'P') {
			return -1;
			// computer wins Paper beats rock
		} else if (Player == 'P' && Comp == 'R') {
			return 1;
			// player wins Paper beats rock
		} else if (Player == 'P' && Comp == 'S') {
			return -1;
			// computer wins Sci beats paper
		} else if (Player == 'S' && Comp == 'P') {
			return 1;
			// player wins Sci beats paper
		} else {
			return 0;
			// tie
		}
	}

}
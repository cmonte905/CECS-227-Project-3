import java.io.*;
import java.util.*;

public class Computer implements Serializable{
	HashMap<Pattern, Integer> patHash;
	private String holdPat;

	public Computer() {
		patHash = new HashMap<Pattern, Integer>();
		holdPat = "";
	}

	public char randomPredict() {
		Random rand = new Random();
		int cChoice = rand.nextInt(3) + 1;

		return assignValue(cChoice);
	}

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

			if (patHash.containsKey(p1)) {
				if (patHash.get(p1) > max) {
					max = patHash.get(p1);
					pMax = p1;
				}
			}
			if (patHash.containsKey(p2)) {

				if (patHash.get(p2) > max) {
					max = patHash.get(p2);
					pMax = p2;
				}
			}
			if (patHash.containsKey(p3)) {
				if (patHash.get(p3) > max) {
					max = patHash.get(p3);
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
	public char beatPredict(char bP){
		
		if(bP =='R'){
			return 'P';
		}
		else if(bP =='P'){
			return 'S';
		}
		else{
			return 'R';
		}
		
		
	}

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

	public void updateHash(char Player) {

		holdPat += Player;
		if (holdPat.length() > 4) {

			holdPat = holdPat.substring(holdPat.length() - 4, holdPat.length());

			Pattern p = new Pattern(holdPat);

			if (patHash.containsKey(p)) {
				patHash.put(p, patHash.get(p) + 1);
			} else {
				patHash.put(p, 1);
			}
		}
	}

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

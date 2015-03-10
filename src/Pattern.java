import java.io.Serializable;
import java.util.*;

/**
 * Class that deals with anything pattern related
 */
public class Pattern implements Serializable {
	private char[] patternValue;

	/**
	 * Constructor that constructs the character array
	 * 
	 * @param pattern
	 *            String that is getting stored into an array of characters
	 */
	public Pattern(String pattern) {
		this.patternValue = pattern.toCharArray();
	}

	/**
	 * Method that returns the character array
	 * 
	 * @return The character array of the pattern
	 */
	public char[] getPattern() {
		return patternValue;
	}

	/**
	 * Method that creates a hash of the character array.
	 * 
	 * @return an integer that represents the hash of character array
	 */
	@Override
	public int hashCode() {
		return Arrays.hashCode(patternValue);

	}

	@Override
	/**
	 * Method that checks the objects to see if they are the same kind of object
	 * @return Boolean
	 */
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Pattern)) {
			return false;
		}
		Pattern pa = (Pattern) o;

		return Arrays.equals(patternValue, pa.patternValue);
	}

}

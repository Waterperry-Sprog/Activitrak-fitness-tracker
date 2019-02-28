package backend;

public class Operations {

	/**
	 * @author tb791 (ref this code)
	 * converts a string to an int based on the chars in the string. 
	 * CANNOT detect when it is passed an invalid string (does not throw exceptions)
	 * @param input the (sanitized) input string
	 * @return the int that is represented by the input string
	 */
	public static int toInt(String input) {
		int returnValue = 0;
		for(int i = 0; i < input.length(); i++){
			returnValue *= 10;
			returnValue += ( input.charAt(i) - '0' );
		}
		
		return returnValue;
	}
	
}

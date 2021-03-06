package backend;

import java.util.Vector;

import javax.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.util.Collections;

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
	
	public static boolean isEven(int x) {
		return (x%2 == 0)?(true):(false);
	}
	
	private static Vector<UserData> listToSort = new Vector<UserData>();
	private static Vector<Integer> intsToSort = new Vector<Integer>();
	
	public static Vector<UserData> sort(Vector<UserData> inputList) {
		if (inputList == null || inputList.size() == 0) {
			return null;
		}
		listToSort = inputList;
		quickSortUserData(0, listToSort.size() - 1);
		return listToSort;
	}
	
	public static Vector<Integer> sortNumbers(Vector<Integer> inputList) {
		if (inputList == null || inputList.size() == 0) {
			return null;
		}
		intsToSort = inputList;
		quickSortNumbers(0, intsToSort.size() - 1);
		return intsToSort;
	}
	
	public static String hashPassword(String password){
		return sha256(password).toLowerCase();
	}
	
	private static String sha256(String password) {
		String result = "";
		byte[] hash = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			hash = digest.digest(password.getBytes("UTF-8"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return convertByteToHex(hash); // make it printable
	}
	
	private static String convertByteToHex(byte[] data) {
		return DatatypeConverter.printHexBinary(data);
    }
	
	private static void quickSortNumbers(int lowerIndex, int higherIndex) {
		int i = lowerIndex;
		int j = higherIndex;
		// calculate pivot number, using middle number to reduce sort times for sorted lists
		int pivot = intsToSort.get( lowerIndex + (higherIndex-lowerIndex) / 2);
		// Divide into two arrays
		while (i <= j) {
			//swap two numbers from each side of pivot.
			//find next value to be swapped from lower half
			while ( intsToSort.get(i) > pivot ) {
				i++;
			}
			//find next value to be swapped from upper half
			while ( pivot > intsToSort.get(j) ) {
				j--;
			}
			if (i <= j) {
				Collections.swap(intsToSort, i, j);
				i++;
				j--;
			}
		}
		// call quickSort() method recursively to divide array up into smaller arrays.
		if (lowerIndex < j)
		quickSortNumbers(lowerIndex, j);
		if (i < higherIndex)
		quickSortNumbers(i, higherIndex);
		return;
	}
	
	private static void quickSortUserData(int lowerIndex, int higherIndex) {
		int i = lowerIndex;
		int j = higherIndex;
		// calculate pivot number, using middle number to reduce sort times for sorted lists
		int pivot = listToSort.get( lowerIndex + (higherIndex-lowerIndex) / 2).getUserGoalProgress();
		// Divide into two arrays
		while (i <= j) {
			//swap two numbers from each side of pivot.
			//find next value to be swapped from lower half
			while ( listToSort.get(i).getUserGoalProgress() > pivot ) {
				i++;
			}
			//find next value to be swapped from upper half
			while ( pivot > listToSort.get(j).getUserGoalProgress() ) {
				j--;
			}
			if (i <= j) {
				Collections.swap(listToSort, i, j);
				i++;
				j--;
			}
		}
		// call quickSort() method recursively to divide array up into smaller arrays.
		if (lowerIndex < j)
		quickSortUserData(lowerIndex, j);
		if (i < higherIndex)
		quickSortUserData(i, higherIndex);
		return;
	}

	
	public static boolean validateAsField(String fieldType, String fieldValue) {
		boolean returnValue = true;
		if(fieldType.contentEquals("number")) {
			char[] parse = fieldValue.toCharArray();
			for (char c : parse) {
				if( c < 48 || c > 57 ) {
					returnValue = false;
				}
			}
		}
		return returnValue;
	}
}
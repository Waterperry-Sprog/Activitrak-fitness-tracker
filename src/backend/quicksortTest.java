package backend;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.Test;

class quicksortTest {

	@Test
	void testToInt() {
		int[] expected = {123,0,234534,0,1};
		String[] sa = {"123","000","234534","0","1"};
		int[] result = new int[5];
		for(int i = 0; i<5; i++) {
			result[i] = Operations.toInt(sa[i]);
		}
		assertArrayEquals(expected, result);
	}

	@Test
	void testIsEven() {
		int[] values = {5,6,0,-3,1123};
		boolean[] expected = {false, true, true, false, false};
		boolean[] result = {true,false,false,true,true};
		for(int i = 0; i<5; i++) {
			result[i] = Operations.isEven(values[i]);
		}
		assertArrayEquals(expected, result);
	}

	@Test
	void testSort() {
		String name = "";
		//set up vector array to be sorted.
		Vector<UserData> input = new Vector<UserData>();
		for(int x = 100; x>0; x-=10) {
			name = name + "n";
			input.add(new UserData(name, x));
		}
		
		//set up expected array of names.
		String[] expArr = new String[10];
		String expName = "";
		for(int i = 0; i<10; i++) {
			expName = expName + "n";
			expArr[i] = expName;
		}
		
		String[] resArr = new String[10];
		Vector<UserData> result = Operations.sort(input);
		for(int i = 0; i<10; i++) {
			resArr[i] = result.get(i).getUsername();
		}
		assertArrayEquals(expArr, resArr);
	}

	@Test
	void testHashPassword() {
		String[] passwords = {"asdf", "hello", "password", "12b13i4bl13iu4", "@:123`~}["};
		String[] expected = {"f0e4c2f76c58916ec258f246851bea091d14d4247a2fc3e18694461b1816e13b",
				"2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824",
				"5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
				"add4a65359e6cbf515e22c26e0e568652ecd2a33e7d260c6f5d727b4eacef765",
				"89cd0186973eeebdfce7f315154f8d4a4da49ca33a905de66f349bbe49b4cc48"};
		String[] result = new String[5];
		for(int i = 0; i<5; i++) {
			result[i] = Operations.hashPassword(passwords[i]);
		}
		assertArrayEquals(expected, result);
	}
}

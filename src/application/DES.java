package application;

import java.math.BigInteger;

public class DES {
	private static int[] permutedChoice1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43,
			35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45,
			37, 29, 21, 13, 5, 28, 20, 12, 4 };
	private static int[] permutedChoice2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7,
			27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29,
			32 };
	private static int[] fPermute = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27,
			3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };
	private static String[] keys = new String[16];
	private static String key = "AC128F4BB5CD937A";
	private static int noShifts[] = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
	private static int[] initialPermutation = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54,
			46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19,
			11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
	private static int[] finalPermutation = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46,
			14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59,
			27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };
	private static int[] expansion = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16,
			17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
	private static int[] s1 = { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13, 1, 10, 6,
			12, 11, 9, 5, 3, 8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11,
			3, 14, 10, 0, 6, 13 };
	private static int[] s2 = { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0,
			1, 10, 6, 9, 11, 5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6,
			7, 12, 0, 5, 14, 9 };
	private static int[] s3 = { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5,
			14, 12, 11, 15, 1, 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, 15,
			14, 3, 11, 5, 2, 12 };
	private static int[] s4 = { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7,
			2, 12, 1, 10, 14, 9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4,
			5, 11, 12, 7, 2, 14 };
	private static int[] s5 = { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0,
			15, 10, 3, 9, 8, 6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15,
			0, 9, 10, 4, 5, 3 };
	private static int[] s6 = { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1,
			13, 14, 0, 11, 3, 8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11,
			14, 1, 7, 6, 0, 8, 13 };
	private static int[] s7 = { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3,
			5, 12, 2, 15, 8, 6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5,
			0, 15, 14, 2, 3, 12 };
	private static int[] s8 = { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5,
			6, 11, 0, 14, 9, 2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12,
			9, 0, 3, 5, 6, 11 };

	private static String convertToBinary(String plain) {
		StringBuilder bForm = new StringBuilder();
		for (char c : plain.toCharArray()) {
			int val = (int) c;
			bForm.append(String.format("%8s", Integer.toBinaryString(val)).replace(' ', '0'));
		}
		return bForm.toString();
	}

	private static String convertToString(String binary) {
		StringBuilder sForm = new StringBuilder();
		int val = 0;
		for (int i = 0; i < binary.length(); i += 8) {
			val = Integer.parseInt(binary.substring(i, i + 8), 2);
			sForm.append((char) val);
		}
		return sForm.toString();
	}

	private static void generateKeys() {
		String originalKey = new BigInteger(key, 16).toString(2);
		StringBuilder k = new StringBuilder();
		StringBuilder c;
		StringBuilder d;
		for (int i : permutedChoice1) {
			k.append(originalKey.charAt(i - 1));
		}
		c = new StringBuilder(k.substring(0, k.length() / 2));
		d = new StringBuilder(k.substring(k.length() / 2));
		for (int i = 0; i < 16; i++) {
			c = new StringBuilder(c.substring(noShifts[i]) + c.substring(0, noShifts[i]));
			d = new StringBuilder(d.substring(noShifts[i]) + d.substring(0, noShifts[i]));
			k = new StringBuilder(c.toString() + d.toString());
			keys[i] = k.toString();
			c = new StringBuilder(k.substring(0, k.length() / 2));
			d = new StringBuilder(k.substring(k.length() / 2));
		}

	}

	private static String initialPermute(String block) {
		StringBuilder b = new StringBuilder();
		for (int i : initialPermutation)
			b.append(block.charAt(i - 1));
		return b.toString();
	}
	private static String finalPermute(String block) {
		StringBuilder b = new StringBuilder();
		System.out.println(block);
		for (int i : finalPermutation)
			b.append(block.charAt(i - 1));
		System.out.println(b.toString());
		return b.toString();
	}
	private static boolean bitOf(char in) {
		return (in == '1');
	}

	private static char charOf(boolean in) {
		return (in) ? '1' : '0';
	}

	private static String sBox(String b, int num) {
		String r = b.substring(0, 1).concat(b.substring(5));
		int rN = Integer.parseInt(r, 2);
		String c = b.substring(1, 5);
		int cN = Integer.parseInt(c, 2);
		String val = null;
		switch (num) {
		case 0:
			val = String.format("%4s", Integer.toBinaryString(s1[15 * rN + cN + 1])).replace(' ', '0');
			break;
		case 1:
			val = String.format("%4s", Integer.toBinaryString(s2[15 * rN + cN + 1])).replace(' ', '0');
			break;
		case 2:
			val = String.format("%4s", Integer.toBinaryString(s3[15 * rN + cN + 1])).replace(' ', '0');
			break;
		case 3:
			val = String.format("%4s", Integer.toBinaryString(s4[15 * rN + cN + 1])).replace(' ', '0');
			break;
		case 4:
			val = String.format("%4s", Integer.toBinaryString(s5[15 * rN + cN + 1])).replace(' ', '0');
			break;
		case 5:
			val = String.format("%4s", Integer.toBinaryString(s6[15 * rN + cN + 1])).replace(' ', '0');
			break;
		case 6:
			val = String.format("%4s", Integer.toBinaryString(s7[15 * rN + cN + 1])).replace(' ', '0');
			break;
		case 7:
			val = String.format("%4s", Integer.toBinaryString(s8[15 * rN + cN + 1])).replace(' ', '0');
			break;
		default:
			break;
		}
		return val;
	}

	private static String desFunction(String in, String key) {
		StringBuilder k = new StringBuilder();
		for (int p : permutedChoice2) {
			k.append(key.charAt(p - 1));
		}
		StringBuilder block = new StringBuilder();
		StringBuilder nBlock = new StringBuilder();
		for (int i : expansion)
			block.append(in.charAt(i - 1));
		for (int i = 0; i < block.length(); i++)
			nBlock.append(charOf(bitOf(block.charAt(i)) ^ bitOf(k.charAt(i))));

		block = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			block.append(sBox(nBlock.substring(i * 6, i * 6 + 6), i));
		}
		nBlock = new StringBuilder();
		for (int i : fPermute)
			nBlock.append(block.charAt(i - 1));
		return nBlock.toString();
	}

	public static String encrypt(String plain, String key) {
		DES.key = key;
		generateKeys();
		String bPlain = convertToBinary(plain);
		StringBuilder cipher = new StringBuilder();
		String bForm = "";
		for (int k = 0; k < bPlain.length(); k += 64) {
			if (k + 64 > bPlain.length()) {
				bForm = String.format("%64s", bPlain.substring(k)).replace(' ', '0');
			} else
				bForm = bPlain.substring(k, k + 64);
			bForm=initialPermute(bForm);
			StringBuilder l = new StringBuilder(bForm.substring(0, bForm.length() / 2));
			StringBuilder r = new StringBuilder(bForm.substring(bForm.length() / 2));
			StringBuilder nl;
			StringBuilder nr;
			for (int i = 0; i < 16; i++) {
				nl = new StringBuilder(r);
				nr = new StringBuilder();
				for (int j = 0; j < l.length(); j++)
					nr.append(charOf(bitOf(l.charAt(j)) ^ bitOf(desFunction(r.toString(), keys[i]).charAt(j))));
				l = nl;
				r = nr;
			}
			cipher.append(finalPermute(r.toString() + l.toString()));
			//cipher.append(r.toString() + l.toString());
		}
		return convertToString(cipher.toString());
	}

	public static String decrypt(String plain, String key) {
		DES.key = key;
		generateKeys();
		String bPlain = convertToBinary(plain);
		StringBuilder cipher = new StringBuilder();
		String bForm = "";
		for (int k = 0; k < bPlain.length(); k += 64) {
			if (k + 64 > bPlain.length()) {
				bForm = String.format("%64s", bPlain.substring(k)).replace(' ', '0');
			} else
				bForm = bPlain.substring(k, k + 64);
			bForm=initialPermute(bForm);
			StringBuilder l = new StringBuilder(bForm.substring(0, bForm.length() / 2));
			StringBuilder r = new StringBuilder(bForm.substring(bForm.length() / 2));
			StringBuilder nl;
			StringBuilder nr;
			for (int i = 0; i < 16; i++) {
				nl = new StringBuilder(r);
				nr = new StringBuilder();
				for (int j = 0; j < l.length(); j++)
					nr.append(charOf(bitOf(l.charAt(j)) ^ bitOf(desFunction(r.toString(), keys[15 - i]).charAt(j))));
				l = nl;
				r = nr;
			}
			cipher.append(finalPermute(r.toString() + l.toString()));
			//cipher.append(r.toString() + l.toString());
		}
		return convertToString(cipher.toString());
	}
}

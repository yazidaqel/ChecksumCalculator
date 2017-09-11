package com.yaqel.checksum.utils;

import java.util.ArrayList;

public final class Hex {

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	private Hex() {

	}

	public static String toString(byte[] by, int offset, int length) {
		char[] buf = new char[length * 2];

		int j = 0;
		for (int i = offset; i < offset + length; i++) {
			int k = by[i];
			buf[j++] = HEX_DIGITS[k >>> 4 & 0xF];
			buf[j++] = HEX_DIGITS[k & 0xF];
		}
		return new String(buf);
	}

	public static String toString(byte[] by) {
		return toString(by, 0, by != null ? by.length : 0);
	}

	public static byte[] fromString(String hex) {
		int len = hex != null ? hex.length() : 0;
		if (hex != null) {
			byte[] buf = new byte[(len + 1) / 2];
			int i = 0;
			int j = 0;
			if (len % 2 != 0) {
				buf[j++] = (byte) fromChar(hex.charAt(i++));
			}
			while (i < len) {
				buf[j++] = (byte) (fromChar(hex.charAt(i++)) << 4 | fromChar(hex.charAt(i++)));
			}
			return buf;
		} else
			return new byte[0];
	}

	public static int fromChar(char ch) {

		if (ch >= '0' && ch <= '9')
			return ch - '0';
		else if (ch >= 'A' && ch <= 'F')
			return ch - 'A' + 10;
		else if (ch >= 'a' && ch <= 'f')
			return ch - 'a' + 10;
		else
			throw new IllegalArgumentException("invalid hex digit [" + ch + "]");
	}

	public static char toChar(int digit) {
		if ((digit < 0) || (digit > 15)) {
			throw new IllegalArgumentException("invalid hex digit [" + digit + "]");
		}
		return HEX_DIGITS[digit];
	}

	public static String padleft(String s, int len, char c) {
		String str = s.trim();
		StringBuilder strBuilder = new StringBuilder(len);
		int fill = len - str.length();
		strBuilder.append(str);
		while (fill-- > 0) {
			strBuilder.append(c);
		}
		return new String(strBuilder);
	}

	public static int hexToInteger(String hex) {
		return Integer.parseInt(hex, 16);
	}

	public static String integerToHex(int value) {
		String hexStr = Integer.toHexString(value);
		if (hexStr.length() % 2 != 0) {
			hexStr = padleft(hexStr, hexStr.length() + 1, '0');
		}
		return hexStr.toUpperCase();
	}

	public static String formatAtc(int atc) {
		return padleft(integerToHex(atc), 4, '0').toUpperCase();
	}

	public static String hexToAsciiToString(String hexS) {
		ArrayList<String> hex = new ArrayList<>();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < hexS.length(); i = i + 2) {
			hex.add(hexS.substring(i, i + 2));
		}
		for (int i = 0; i < hex.size(); i++) {
			int ascii = toInt(hex.get(i));
			String dec = Character.toString((char) ascii);
			stringBuilder.append(dec);
		}

		return new String(stringBuilder);
	}

	public static int toInt(String hex) {
		return Integer.parseInt(hex.toLowerCase(), 16);
	}

	public static String intToBinary(int number, int desiredSize) {
		return String.format("%" + desiredSize + "s", Integer.toBinaryString(number)).replace(' ', '0');
	}

	public static String intToBinary(int number) {
		String result = Integer.toBinaryString(number);
		StringBuilder stringBuilder = new StringBuilder();
		while (result.length() != 4) {
			stringBuilder.append("0" + result);
		}

		return new String(stringBuilder);

	}

	public static int binaryToInt(String binary) {

		return Integer.parseInt(binary, 2);
	}

	public static final String toStringWithSeparator(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
		StringBuilder localStringBuffer = new StringBuilder();
		for (int i = 0; i < paramInt2; i++) {
			String str = Integer.toHexString(0xFF & paramArrayOfByte[paramInt1 + i]).toUpperCase();
			if (str.length() == 1) {
				localStringBuffer.append("0");
			}
			localStringBuffer.append(str);
			if (i < paramInt2 - 1) {
				localStringBuffer.append(" ");
			}
		}
		return localStringBuffer.toString();
	}

	public static String asciiToHex(String str) {
		char[] chars = str.toCharArray();
		StringBuilder hex = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString();
	}

	public static byte[] addPadding(byte[] data, int blockSize) {
		byte[] result;
		int length = data.length;
		if (length % blockSize == 0)
			return data;
		else {
			int l = ((length / blockSize) + 1) * blockSize;
			result = new byte[l];
			System.arraycopy(data, 0, result, 0, length);
		}
		return result;
	}

	/**
	 * Concatenates a list of byte arrays.
	 *
	 * @param arrays
	 *            the byte arrays to be concatenated
	 * @return the concatenated byte array
	 */
	public static byte[] concat(byte[]... arrays) {
		// Determine the length of the result array
		int totalLength = 0;
		for (int i = 0; i < arrays.length; i++) {
			totalLength += arrays[i].length;
		}

		// create the result array
		byte[] result = new byte[totalLength];

		// copy the source arrays into the result array
		int currentIndex = 0;
		for (int i = 0; i < arrays.length; i++) {
			System.arraycopy(arrays[i], 0, result, currentIndex, arrays[i].length);
			currentIndex += arrays[i].length;
		}

		return result;
	}

	public static byte getBit(int position, byte id) {
		return (byte) ((id >> position) & 1);
	}

}
package com.nxp.trustid.model.util;

public class Utils1 {
	private static final char[] DIGITS_UPPER = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
			'C', 'D', 'E', 'F' };
	private static final int RADIX_HEX = 16;
	private static final String PARAMETER_NAME_STRING = "parameterName";
	private static final String EXCEPTION_MESSAGE_PARAMETER_NOT_NULL = "The parameter %s must not be null.";
	private static final String EXCEPTION_MESSAGE_PARAMETER_NOT_SET = "The parameter %s is not set.";
	private static final String EXCEPTION_MESSAGE_PARAMETER_NEGATIVE = "The parameter %s must not be negative.";
	private static final String EXCEPTION_MESSAGE_LENGTH_AND_OFFSET_MUST_NOT_EXCEED_ARRAY_LENGTH = "The value (offset + length) must not exceed the length of the array.";

	public static String getHexString(byte... array) {
		int length = array.length;
		char[] out = new char[length << 1];
		int i = 0;

		for (int j = 0; i < length; j += 2) {
			out[j] = DIGITS_UPPER[(240 & array[i]) >>> 4];
			out[j + 1] = DIGITS_UPPER[15 & array[i]];
			++i;
		}

		return String.valueOf(out);
	}

	public static String getHexString(byte[] array, int offset, int length) {
		checkParameterNotNull(array, "array");
		checkParameterNotNegative(offset, "offset");
		checkParameterNotNegative(length, "length");
		if (array.length < offset + length) {
			throw new IllegalArgumentException("The value (offset + length) must not exceed the length of the array.");
		} else {
			char[] out = new char[length << 1];
			int i = offset;

			for (int j = 0; i < offset + length; j += 2) {
				out[j] = DIGITS_UPPER[(240 & array[i]) >>> 4];
				out[j + 1] = DIGITS_UPPER[15 & array[i]];
				++i;
			}

			return String.valueOf(out);
		}
	}

	public static byte[] getByteArray(String hexStr) {
		if (hexStr.length() % 2 != 0) {
			throw new IllegalArgumentException("the length of the parameter \'hexStr\' must be a multiple of 2");
		} else {
			byte[] bArray = new byte[hexStr.length() / 2];

			for (int i = 0; i < hexStr.length() / 2; ++i) {
				int off = 2 * i;
				byte firstNibble = Byte.parseByte(hexStr.substring(off++, off), 16);
				byte secondNibble = Byte.parseByte(hexStr.substring(off++, off), 16);
				int finalByte = secondNibble | firstNibble << 4;
				bArray[i] = (byte) finalByte;
			}

			return bArray;
		}
	}

	public static byte[] getTwoBytesArray(int param) {
		byte[] result = new byte[] { (byte) (param >> 8 & 255), (byte) (param & 255) };
		return result;
	}

	public static byte[] getThreeBytesArray(int param) {
		byte[] result = new byte[] { (byte) (param >> 16 & 255), (byte) (param >> 8 & 255), (byte) (param & 255) };
		return result;
	}

	public static byte[] flipByteOrder(byte[] data) {
		byte[] result = new byte[data.length];

		for (int i = 0; i < data.length; ++i) {
			result[i] = data[data.length - 1 - i];
		}

		return result;
	}

	public static long now() {
		return System.currentTimeMillis();
	}

	public static boolean arrayCompare(byte[] a1, int offsetA1, byte[] a2, int offsetA2, int length) {
		if (areArrayCompareParametersInvalid(a1, a2, offsetA1, offsetA2, length)) {
			return false;
		} else {
			for (int i = 0; i < length; ++i) {
				if (a1[offsetA1 + i] != a2[offsetA2 + i]) {
					return false;
				}
			}

			return true;
		}
	}

	private static boolean areArrayCompareParametersInvalid(byte[] a1, byte[] a2, int offsetA1, int offsetA2,
			int length) {
		return a1 != null && a2 != null ? (offsetA1 >= 0 && offsetA2 >= 0 && length >= 0
				? areOffsetsExceedingTotalLen(a1, a2, offsetA1, offsetA2, length) : true) : true;
	}

	private static boolean areOffsetsExceedingTotalLen(byte[] a1, byte[] a2, int offsetA1, int offsetA2, int length) {
		boolean offsetA1ExceedsTotalLen = offsetA1 >= a1.length;
		boolean offsetA2ExceedsTotalLen = offsetA2 >= a2.length;
		if (!offsetA1ExceedsTotalLen && !offsetA2ExceedsTotalLen) {
			boolean offsetA1PlusLenExceedsTotalLen = offsetA1 + length > a1.length;
			boolean offsetA2PlusLenExceedsTotalLen = offsetA2 + length > a2.length;
			return offsetA1PlusLenExceedsTotalLen || offsetA2PlusLenExceedsTotalLen;
		} else {
			return true;
		}
	}

	public static boolean hasApduStatusWord(byte[] apdu, byte[] statusWord) {
		checkParameterNotNull(apdu, "apdu");
		checkParameterNotNull(statusWord, "statusWord");

		assert apdu.length >= 2;

		assert statusWord.length == 2;

		return arrayCompare(apdu, apdu.length - 2, statusWord, 0, 2);
	}

	public static <T> T checkParameterNotNull(T parameterValue, String parameterName) throws IllegalArgumentException {
		if (parameterName == null) {
			throw new IllegalArgumentException(
					String.format("The parameter %s must not be null.", new Object[] { "parameterName" }));
		} else if (parameterValue == null) {
			throw new IllegalArgumentException(
					String.format("The parameter %s is not set.", new Object[] { parameterName }));
		} else {
			return parameterValue;
		}
	}

	public static int checkParameterNotNegative(int parameterValue, String parameterName)
			throws IllegalArgumentException {
		if (parameterName == null) {
			throw new IllegalArgumentException(
					String.format("The parameter %s must not be null.", new Object[] { "parameterName" }));
		} else if (parameterValue < 0) {
			throw new IllegalArgumentException(
					String.format("The parameter %s must not be negative.", new Object[] { "parameterName" }));
		} else {
			return parameterValue;
		}
	}

	public static int convertToLSBFirstAID(int aid) {
		byte[] actualAid = flipByteOrder(getThreeBytesArray(aid));
		String actualAidString = getHexString(actualAid);
		Integer temp1 = Integer.valueOf(Integer.parseInt(actualAidString, 16));
		return temp1.intValue();
	}
}

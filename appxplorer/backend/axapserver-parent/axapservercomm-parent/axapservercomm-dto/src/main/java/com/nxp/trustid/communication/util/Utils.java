package com.nxp.trustid.communication.util;

import java.util.Objects;

/**
 * Utility methods.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
@SuppressWarnings("MagicNumber")
public class Utils {

  private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
  private static final int RADIX_HEX = 16;

  private Utils() {
    // empty
  }

  /**
   * Converts an array of bytes into a {@link String} representing the hexadecimal values of each byte in order.<br> <i>Note</i>:
   * Implementation took from <i>Apache commons</i>.
   *
   * @param array a byte[] to convert to Hex characters
   * @return a {@link String} containing hexadecimal representation
   */
  public static String getHexString(final byte... array) {
    final int length = array.length;
    final char[] out = new char[length << 1];

    for (int i = 0, j = 0; i < length; i++, j += 2) {
      out[j] = DIGITS_UPPER[(0xF0 & array[i]) >>> 4];
      out[j + 1] = DIGITS_UPPER[0x0F & array[i]];
    }

    return String.valueOf(out);
  }

  /**
   * Returns a byte[] of the passed hex string.
   *
   * @param hexStr hex string to convert
   * @return byte[] representation of the hex string
   */
  public static byte[] getByteArray(final String hexStr) {
    if ((hexStr.length() % 2) != 0) {
      throw new IllegalArgumentException("the length of the parameter 'hexStr' must be a multiple of 2");
    }

    final byte[] bArray = new byte[hexStr.length() / 2];

    for (int i = 0; i < (hexStr.length() / 2); i++) {
      int off = 2 * i;

      final byte firstNibble = Byte.parseByte(hexStr.substring(off, ++off), RADIX_HEX); // [x,y]
      final byte secondNibble = Byte.parseByte(hexStr.substring(off, ++off), RADIX_HEX);

      final int finalByte = (secondNibble) | (firstNibble << 4);
      bArray[i] = (byte) finalByte;
    }
    return bArray;
  }

  @SuppressWarnings({"MagicNumber", "squid:MethodCyclomaticComplexity"})
  public static String aidAsHexString(final int aid) {
    final String aidAsHexString = Integer.toHexString(aid);

    if (aid < 0x10) {
      return "00000" + aidAsHexString;
    }

    if (aid < 0x0100) {
      return "0000" + aidAsHexString;
    }

    if (aid < 0x1000) {
      return "000" + aidAsHexString;
    }

    if (aid < 0x010000) {
      return "00" + aidAsHexString;
    }

    if (aid < 0x100000) {
      return "0" + aidAsHexString;
    }

    return aidAsHexString;
  }

  /**
   * This method compares the content of two byte arrays. It returns {@code true} if, and only if the arrays are not {@code null} and the
   * contents beginning at the specified offsets are equal for the specified length.
   *
   * @param a1       first array
   * @param offsetA1 offset in the first array
   * @param a2       second array
   * @param offsetA2 offset in the second array
   * @param length   length to compare over
   * @return {@code true} if the compared part is equal, otherwise {@code false}
   */
  public static boolean arrayCompare(final byte[] a1, final int offsetA1, final byte[] a2, final int offsetA2, final int length) {
    if (areArrayCompareParametersInvalid(a1, a2, offsetA1, offsetA2, length)) {
      return false;
    }

    for (int i = 0; i < length; i++) {
      if (a1[offsetA1 + i] != a2[offsetA2 + i]) {
        return false;
      }
    }

    return true;
  }

  @SuppressWarnings("squid:MethodCyclomaticComplexity")
  private static boolean areArrayCompareParametersInvalid(final byte[] a1, final byte[] a2, final int offsetA1, final int offsetA2, final
  int length) {
    if ((a1 == null) || (a2 == null)) {
      return true;
    }

    if ((offsetA1 < 0) || (offsetA2 < 0) || (length < 0)) {
      return true;
    }

    final boolean offsetA1ExceedsTotalLen = offsetA1 >= a1.length;
    final boolean offsetA2ExceedsTotalLen = offsetA2 >= a2.length;
    final boolean offsetA1PlusLenExceedsTotalLen = (offsetA1 + length) > a1.length;
    final boolean offsetA2PlusLenExceedsTotalLen = (offsetA2 + length) > a2.length;

    return offsetA1ExceedsTotalLen || offsetA2ExceedsTotalLen || offsetA1PlusLenExceedsTotalLen || offsetA2PlusLenExceedsTotalLen;
  }

  public static boolean hasApduStatusWord(final byte[] apdu, final byte[] statusWord) {
    Objects.requireNonNull(apdu, "apdu is null");
    Objects.requireNonNull(statusWord, "statusWord is null");

    assert apdu.length >= 2;
    assert statusWord.length == 2;

    return arrayCompare(apdu, apdu.length - 2, statusWord, 0, 2);
  }
}

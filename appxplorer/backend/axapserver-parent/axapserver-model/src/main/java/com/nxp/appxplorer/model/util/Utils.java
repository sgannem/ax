package com.nxp.trustid.model.util;

/**
 * Utility methods.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class Utils {

  private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

  private Utils() {
    // empty
  }

  /**
   * This method is equivalent to a call to {@code getLong(data, 0, data.length)}.
   *
   * @param data from which the long should be fetched
   * @return the extracted {@code long} value
   */
  public static long getLong(final byte[] data) {
    return getLong(data, 0, data.length);
  }

  /**
   * This method extracts a {@code long} value from a passed {@code byte[]}.
   *
   * @param data   data from which the long should be fetched
   * @param offset offset within the data {@code byte[]}
   * @param length length beginning at {@code offset} to be extracted (maximum {@link Long#SIZE} bytes)
   * @return the extracted {@code long} value
   */
  public static long getLong(final byte[] data, final int offset, final int length) {
    final int numberOfBytesOfALong = (Long.SIZE / Byte.SIZE);

    if (length > numberOfBytesOfALong) {
      throw new IllegalArgumentException("can not parse more than " + numberOfBytesOfALong + " bytes when converting into a long");
    }

    if ((offset + length) > data.length) {
      throw new IllegalArgumentException("offset and length would exceed the length of the byte[] parameter data");
    }

    if ((offset < 0) || (length < 0)) {
      throw new IllegalArgumentException("neither offset nor length may be <0");
    }

    long resultingLong = 0L;

    for (int i = ((offset + length) - 1), j = 0; i >= offset; i--, j++) {
      resultingLong |= (((long) ((data[i]) & 0xFF)) << (j * Byte.SIZE));
    }

    return resultingLong;
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
   * Converts the first {@code numberOfBytes} bytes of a passed integer value (represented as {@code long}) into a byte[].
   *
   * @param data          integer to be converted
   * @param numberOfBytes number LSB of bytes to be converted
   * @return the resulting byte array
   * @throws IllegalArgumentException if the passed parameter numberOfBytes is smaller than 1 or greater than 8
   */
  public static byte[] getByteArray(final long data, final int numberOfBytes) {
    if ((numberOfBytes < 1) || (numberOfBytes > 8)) {
      throw new IllegalArgumentException("parameter numberOfBytes must not be smaller than 1 or greater than 8");
    }

    final byte[] res = new byte[numberOfBytes];

    for (int i = 0, j = (numberOfBytes - 1); i < numberOfBytes; i++, j--) {
      res[i] = (byte) (data >> (j * Byte.SIZE));
    }

    return res;
  }
}

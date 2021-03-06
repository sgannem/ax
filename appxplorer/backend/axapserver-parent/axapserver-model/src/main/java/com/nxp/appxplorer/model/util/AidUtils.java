package com.nxp.trustid.model.util;

import static java.util.Objects.requireNonNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility methods for handling AIDs.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class AidUtils {

  private static final int RADIX_HEX = 16;

  private AidUtils() {
    // empty
  }

  @SuppressWarnings("MagicNumber")
  public static String asHexString(final int aid) {
    final String aidAsHexString = Integer.toHexString(aid);
    final String prefix;

    if (aid < 0x000010) {
      prefix = "00000";
    } else if (aid < 0x000100) {
      prefix = "0000";
    } else if (aid < 0x001000) {
      prefix = "000";
    } else if (aid < 0x010000) {
      prefix = "00";
    } else if (aid < 0x100000) {
      prefix = "0";
    } else {
      prefix = "";
    }

    return prefix + aidAsHexString;
  }

  public static String asHexString(final List<Integer> aids) {
    return aids.stream().map(AidUtils::asHexString).collect(Collectors.joining(","));
  }

  public static String asHexString(final int[] aids) {
    return Arrays.stream(aids).mapToObj(AidUtils::asHexString).collect(Collectors.joining(","));
  }

  public static int aidFromString(final String aidAsString) {
    requireNonNull(aidAsString);

    if (aidAsString.startsWith("0x")) {
      return Integer.parseInt(aidAsString.substring(2), RADIX_HEX);
    }

    return Integer.parseInt(aidAsString);
  }
}

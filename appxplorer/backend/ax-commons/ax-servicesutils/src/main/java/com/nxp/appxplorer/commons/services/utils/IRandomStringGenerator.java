package com.nxp.appxplorer.commons.services.utils;

/**
 * Generates a random string.
 *
 */
public interface IRandomStringGenerator {

  String numericString(int length);

  String randomString(int length, String allowedCharacters);
}

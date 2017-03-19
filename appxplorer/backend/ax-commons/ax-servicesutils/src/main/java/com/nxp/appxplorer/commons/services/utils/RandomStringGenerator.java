package com.nxp.appxplorer.commons.services.utils;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.inject.Singleton;

/**
 * Implementation of {@link IRandomStringGenerator} using apache.commons.lang3.
 *
 */
@Singleton
public class RandomStringGenerator implements IRandomStringGenerator {

  @Override
  public String numericString(final int length) {
    return RandomStringUtils.randomNumeric(length);
  }

  @Override
  public String randomString(final int length, final String allowedCharacters) {
    return RandomStringUtils.random(length, allowedCharacters);
  }
}

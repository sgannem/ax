package com.nxp.appxplorer.services.model;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * This class used to build Password Hash to store hashed password to store into
 * signed up user(s). Builds a {@link User}.
 *
 */
public class UserBuilder {

    private static final int PBKDF2_DESIRED_LENGTH = 512;
    private static final String PBKDF2_ALGORITHM_NAME = "PBKDF2WithHmacSHA512";
    private static final int PBKDF2_ITERATIONS = 8192;
    private static final int PBKDF2_LENGTH_OF_SALT_IN_BYTES = 16;

    public static final SecureRandom SECURE_RANDOM;
    private static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
    private static final String SECURITY_PROVIDER_SECURE_RANDOM = "SUN";

    private UserBuilder() {
	// empty
    }

    static {
	try {
	    SECURE_RANDOM = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM, SECURITY_PROVIDER_SECURE_RANDOM);
	} catch (final NoSuchAlgorithmException | NoSuchProviderException e) {
	    throw new IllegalStateException(e);
	}
    }

    // public static User newInstance(final String username, final String
    // password) {
    // final byte[] salt = new byte[PBKDF2_LENGTH_OF_SALT_IN_BYTES];
    // SECURE_RANDOM.nextBytes(salt);
    // final byte[] passwordHash = pbkdf2(password, salt, PBKDF2_ITERATIONS);
    //
    // return User.newInstance(username, passwordHash, salt, PBKDF2_ITERATIONS);
    // }

    public static byte[] pbkdf2(final String password, final byte[] salt, final int iterations) {
	final KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterations, PBKDF2_DESIRED_LENGTH);

	try {
	    final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM_NAME);
	    return secretKeyFactory.generateSecret(keySpec).getEncoded();
	} catch (final InvalidKeySpecException | NoSuchAlgorithmException e) {
	    throw new IllegalStateException("failed to compute pbkdf2", e);
	}
    }

    public static byte[] pbkdf2(final String password, final byte[] salt, final int iterations, final int desiredLength,
	    final String algorithmName) {
	final KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterations, PBKDF2_DESIRED_LENGTH);

	try {
	    final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM_NAME);
	    return secretKeyFactory.generateSecret(keySpec).getEncoded();
	} catch (final InvalidKeySpecException | NoSuchAlgorithmException e) {
	    throw new IllegalStateException("failed to compute pbkdf2", e);
	}
    }
}

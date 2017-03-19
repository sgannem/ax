package com.nxp.appxplorer.commons.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenGeneration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenGeneration.class);
	
	public static void main(String[] args) {
		getBearerToken();
	}

	public static String getBearerToken() {
		Random random = new SecureRandom();
		String token = new BigInteger(130, random).toString(32);
		LOGGER.info("#Got the bearer token:"+token);
		System.out.println("#bearer token:"+token);
		return token;
	}

}

package com.payvang.InvoiceRetrival.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;
import com.payvang.InvoiceRetrival.Exceptions.CustomException;

public class Hasher {

	private static Logger logger = LoggerFactory.getLogger(Hasher.class.getName());

	public Hasher() {
	}

	public static String getHash(String input) throws CustomException {
		String response = null;
		logger.info("Calculated Hash Param:" + input);
		MessageDigest messageDigest = MessageDigestProvider.provide();
		messageDigest.update(input.getBytes());
		MessageDigestProvider.consume(messageDigest);

		response = new String(Hex.encodeHex(messageDigest.digest()));
		logger.info("Calculated Hash :" + response.toUpperCase());
		System.out.println(response.toUpperCase());
		return response.toUpperCase();
	}
}

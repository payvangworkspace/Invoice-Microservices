package com.payvang.InvoiceRetrival.Util;

import java.util.EmptyStackException;
import java.util.Stack;

import com.payvang.InvoiceRetrival.Exceptions.CustomException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestProvider {

	private static Stack<MessageDigest> stack = new Stack<MessageDigest>();

	public static MessageDigest provide() throws CustomException {
		MessageDigest digest = null;
		try {
			digest = stack.pop();
		} catch (EmptyStackException emptyStackException) {
			try {
				digest = MessageDigest.getInstance(ConfigurationConstants.HASHING_ALGORITHAM.getValue());
			} catch (NoSuchAlgorithmException noSuchAlgorithmException) {
//				throw new SystemException(ErrorType.INTERNAL_SYSTEM_ERROR, noSuchAlgorithmException,
//						"Hashing algoritham not found");
				noSuchAlgorithmException.printStackTrace();
			}
		}

		return digest;
	}

	public static void consume(MessageDigest digest) {
		stack.push(digest);
	}
}

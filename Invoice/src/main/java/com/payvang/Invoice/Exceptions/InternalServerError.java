package com.payvang.Invoice.Exceptions;

public class InternalServerError extends RuntimeException {
	public InternalServerError() {
		super("Internal Server error occured");
	}

}

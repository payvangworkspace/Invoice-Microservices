package com.payvang.InvoiceRetrival.Exceptions;

public class InternalServerError extends RuntimeException {
	public InternalServerError() {
		super("Internal Server error occured");
	}

}

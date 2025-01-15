package com.payvang.InvoiceRetrival.Exceptions;

public class InvoiceNotFoundException extends RuntimeException {
	public InvoiceNotFoundException() {
		super("Invoice Not Found on Server");
	}

}

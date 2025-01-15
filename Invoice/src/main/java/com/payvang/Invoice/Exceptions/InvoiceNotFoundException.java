package com.payvang.Invoice.Exceptions;

public class InvoiceNotFoundException extends RuntimeException {
	public InvoiceNotFoundException() {
		super("Invoice Not Found on Server");
	}

}

package com.payvang.InvoiceRetrival.Exceptions;

public class UserNotFound extends RuntimeException{
public UserNotFound() {
	super("No User Exist");
}
}

package com.payvang.Invoice.Exceptions;

public class UserNotFound extends RuntimeException{
public UserNotFound() {
	super("No User Exist");
}
}

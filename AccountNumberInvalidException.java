package com.braindata.bankmanagement.exceptions;

public class AccountNumberInvalidException extends RuntimeException{

	public AccountNumberInvalidException(String msg)
	{
		super(msg);
	}
}

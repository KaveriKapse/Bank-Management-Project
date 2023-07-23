package com.braindata.bankmanagement.serviceImpl;

import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.exceptions.AccountNumberInvalidException;
import com.braindata.bankmanagement.exceptions.AdharNumberInvalidException;
import com.braindata.bankmanagement.exceptions.AgeInvalidException;
import com.braindata.bankmanagement.exceptions.BalanceInvalidException;
import com.braindata.bankmanagement.exceptions.GenderInvalidException;
import com.braindata.bankmanagement.exceptions.MobileNumberInvalidException;
import com.braindata.bankmanagement.model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Sbi implements Rbi{

	Scanner sc = new Scanner(System.in);
	Account ac = new Account();
	
	@Override
	public void createAccount() {
		
		System.out.println("\n--Creating Account--");
		System.out.print("Enter Account Number : ");
		
		try {
			long acno = new Scanner(System.in).nextLong();
			int length=0;
			while(acno!=0)
			{
				acno/=10;
				length++;
			}
		    if(length==12)
		    	ac.setAccNo(acno);
		    else
		    {
		    	//System.out.println("Enter valid Account number of 12 digits....");
		    	//createAccount();
		    	throw new AccountNumberInvalidException("Enter valid Account number of 12 digits....");
		    }
		}
		catch(InputMismatchException n)
		{
			System.out.println("Enter numbers only...");
			createAccount();			
		}
		
		System.out.print("Enter Name : ");
		ac.setName(new Scanner(System.in).next());
		
		System.out.print("Enter Mobile No. : ");
		String mobNo = new Scanner(System.in).next();
		if(mobNo.length()==10)
			ac.setMobNo(mobNo);
		else
		{
			//System.out.println("Enter valid Mobile Number of 10 digits...");
			//createAccount();
			throw new MobileNumberInvalidException("Enter valid Mobile Number of 10 digits...");
		}
		
		
		System.out.print("Enter Adhar No. : ");
		String aNo = new Scanner(System.in).next();
		
		if(aNo.length()==12)
				ac.setAdharNo(aNo);
		else
		{
			//System.out.println("Enter valid Adhar Number of 12 digits...");
			//createAccount();
			throw new AdharNumberInvalidException("Enter valid Adhar Number of 12 digits...");
		}			
		
		System.out.print("Enter Gender : ");
		String gen = new Scanner(System.in).next();
		if((gen.equalsIgnoreCase("Male") )|| (gen.equalsIgnoreCase("Female")) || (gen.equalsIgnoreCase("F")) || (gen.equalsIgnoreCase("M")) || (gen.equalsIgnoreCase("f")) || (gen.equalsIgnoreCase("m")) || (gen.equalsIgnoreCase("male")) || (gen.equalsIgnoreCase("female")))
			ac.setGender(gen);	
		else
		{
			//System.out.println("Enter valid Gender...");
			//createAccount();
			throw new GenderInvalidException("Enter valid Gender...");
		}
		
		System.out.print("Enter Age : ");
		int age = new Scanner(System.in).nextInt();
		if(age>=18)
			ac.setAge(age);
		else
		{
			//System.out.println("Your Age is not fit for creating Account...");
			//createAccount();
			throw new AgeInvalidException("Your Age is not fit for creating Account...");
		}
		
		System.out.print("Enter Balance : ");
		double bal = new Scanner(System.in).nextDouble();
		if(bal>=500)
			ac.setBalance(bal);
		else
		{
			//System.out.println("Balance should be 500 or more.....");
			//createAccount();
			throw new BalanceInvalidException("Balance should be 500 or more...");
		}
	}

	@Override
	public void displayAllDetails() {
	
		System.out.println("\n--Details of Account--");
		System.out.println("Account No. - "+ac.getAccNo()+"\n"
				+"Name - "+ac.getName()+"\n"
				+"Mobile No. - "+ac.getMobNo()+"\n"
				+"AdharNo - "+ac.getAdharNo()+"\n"
				+"Gender - "+ac.getGender()+"\n"
				+"Age - "+ac.getAge()+"\n"
				+"Balance - "+ac.getBalance());
	}

	
	@Override
	public void depositeMoney() {
	
		System.out.print("\n--Enter amount to deposite : ");
		double depositeAmount = sc.nextDouble();
		double totalBalance=ac.getBalance()+depositeAmount;
		System.out.println("Amount credited to your account..." );
		ac.setBalance(totalBalance);
		System.out.println("Current Account Balance - "+ac.getBalance());
	}
	
	

	@Override
	public void withdrawal() {
		System.out.print("\n--Enter amount for withdrawal : ");
		double withdrawalAmount =sc.nextDouble();
		double remBalance= ac.getBalance()-withdrawalAmount;
		ac.setBalance(remBalance);
		System.out.println("Amount debited from your account...");
		System.out.println("Current Account Balance - "+ac.getBalance());
	}

	@Override
	public void balanceCheck() {
		System.out.println("\n--Checking Account Balance--");
		System.out.println("Account Balance -" +ac.getBalance() );
		
	}

}
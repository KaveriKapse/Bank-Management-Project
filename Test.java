package com.braindata.bankmanagement.client;

import com.braindata.bankmanagement.exceptions.AccountNumberInvalidException;
import com.braindata.bankmanagement.exceptions.AdharNumberInvalidException;
import com.braindata.bankmanagement.exceptions.AgeInvalidException;
import com.braindata.bankmanagement.exceptions.BalanceInvalidException;
import com.braindata.bankmanagement.exceptions.GenderInvalidException;
import com.braindata.bankmanagement.exceptions.MobileNumberInvalidException;
import com.braindata.bankmanagement.service.*;


import com.braindata.bankmanagement.serviceImpl.*;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Rbi bank = new Sbi();
		
		System.out.println("***BANK MANAGEMENT SYSTEM***");
		
		String ch;
		
		do
		{
			
			System.out.print("\n--Select Option--\n"
					+ "1.Create Account\n"
					+ "2.Display All Details\n"
					+ "3.Deposite Money\n"
					+ "4.Withdraw Money\n"
					+ "5.Balance Check\n"
					+ "6.Exit---");
			int choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					
					try {
						bank.createAccount();
					}
					catch(AccountNumberInvalidException a)
					{
						System.out.println(a.getMessage());
						bank.createAccount();
					}
					catch(AdharNumberInvalidException an)
					{
						System.out.println(an.getMessage());
						bank.createAccount();
					}
					catch(MobileNumberInvalidException m)
					{
						System.out.println(m.getMessage());
						bank.createAccount();
					}
					catch(AgeInvalidException age)
					{
						System.out.println(age.getMessage());
						bank.createAccount();
					}
					catch(GenderInvalidException g)
					{
						System.out.println(g.getMessage());
						bank.createAccount();
					}
					catch(BalanceInvalidException be)
					{
						System.out.println(be.getMessage());
						bank.createAccount();
					}
				break;
		
				case 2:bank.displayAllDetails();
				break;
		
				case 3:bank.depositeMoney();
				break;
		
				case 4:bank.withdrawal();
				break;
		
				case 5:bank.balanceCheck();
				break;
				
				case 6:System.exit(0);
				break;
		
				default : System.out.println("Enter valid Choice!!!");
			}
			
			System.out.print("\nDo you want to continue ? ");
			ch= sc.next();
		}while(ch.equalsIgnoreCase("Y") || ch.equalsIgnoreCase("y"));
		
	}
}

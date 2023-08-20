package com.braindata.bankmanagement.serviceImpl;


import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.braindata.bankmanagement.config.HibernateUtil;
import com.braindata.bankmanagement.exceptions.AccountNumberInvalidException;
import com.braindata.bankmanagement.exceptions.AdharNumberInvalidException;
import com.braindata.bankmanagement.exceptions.AgeInvalidException;
import com.braindata.bankmanagement.exceptions.BalanceInvalidException;
import com.braindata.bankmanagement.exceptions.GenderInvalidException;
import com.braindata.bankmanagement.exceptions.MobileNumberInvalidException;
import com.braindata.bankmanagement.model.Account;
import com.braindata.bankmanagement.service.Rbi;

public class Sbi implements Rbi{

	Scanner sc = new Scanner(System.in);
	Account ac = new Account();
	@Override
	public void createAccount() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("\n--Creating Account--");
		System.out.print("Enter Account Number : ");
		
		
			String acno = sc.next();
			 if(acno.length()==12)
			    	ac.setAccNo(acno);
			    else
			    {
			    	//System.out.println("Enter valid Account number of 12 digits....");
			    	//createAccount();
			    	throw new AccountNumberInvalidException("Enter valid Account number of 12 digits....");
			    }
					
			System.out.print("Enter Name : ");
			ac.setName(sc.next());
			
			System.out.print("Enter Mobile No. : ");
			String mobNo = sc.next();
			if(mobNo.length()==10)
				ac.setMobNo(mobNo);
			else
			{
				//System.out.println("Enter valid Mobile Number of 10 digits...");
				//createAccount();
				throw new MobileNumberInvalidException("Enter valid Mobile Number of 10 digits...");
			}
			
			
			System.out.print("Enter Adhar No. : ");
			String aNo = sc.next();
			
			if(aNo.length()==12)
					ac.setAdharNo(aNo);
			else
			{
				//System.out.println("Enter valid Adhar Number of 12 digits...");
				//createAccount();
				throw new AdharNumberInvalidException("Enter valid Adhar Number of 12 digits...");
			}			
			
			System.out.print("Enter Gender : ");
			String gen = sc.next();
			if((gen.equalsIgnoreCase("Male") )|| (gen.equalsIgnoreCase("Female")) || (gen.equalsIgnoreCase("F")) || (gen.equalsIgnoreCase("M")) || (gen.equalsIgnoreCase("f")) || (gen.equalsIgnoreCase("m")) || (gen.equalsIgnoreCase("male")) || (gen.equalsIgnoreCase("female")))
				ac.setGender(gen);	
			else
			{
				//System.out.println("Enter valid Gender...");
				//createAccount();
				throw new GenderInvalidException("Enter valid Gender...");
			}
			
			System.out.print("Enter Age : ");
			int age = sc.nextInt();
			if(age>=18)
				ac.setAge(age);
			else
			{
				//System.out.println("Your Age is not fit for creating Account...");
				//createAccount();
				throw new AgeInvalidException("Your Age is not fit for creating Account...");
			}
			
			System.out.print("Enter Balance : ");
			double bal = sc.nextDouble();
			if(bal>=500)
				ac.setBalance(bal);
			else
			{
				//System.out.println("Balance should be 500 or more.....");
				//createAccount();
				throw new BalanceInvalidException("Balance should be 500 or more...");
			}
			session.save(ac);
			session.beginTransaction().commit();
			
	}

	@Override
	public void displayAllDetails() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		System.out.println("Enter account number :");
		String accno = sc.next();
		Account acc= (Account) session.getNamedQuery("getSingleData").setParameter(0, accno).getSingleResult();
		
			System.out.println("Account No -" +acc.getAccNo()+"\n"
					+"Name -"+acc.getName()+"\n"
					+"Mobile Number -"+acc.getMobNo()+"\n"
					+"Adhar Number -"+acc.getAdharNo()+"\n"
					+"Gender -"+acc.getGender()+"\n"
					+"Age -"+acc.getAge()+"\n"
					+"Balance -"+acc.getBalance()+"\n");
		}

	@Override
	public void depositeMoney() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		System.out.print("Enter account number in which U want to deposite money:");
		String accno = sc.next();
		Account ac=(Account) session.getNamedQuery("getSingleData").setParameter(0, accno).getSingleResult();
		
		System.out.print("Enter Amount to deposite:");
		double depositAmount = sc.nextDouble();
		
		double totalAmount = ac.getBalance()+depositAmount;
		
		Query<Account> query1= session.getNamedQuery("depositWithdrawMoney");
		query1.setParameter(0, totalAmount);
		query1.setParameter(1,accno);
		query1.executeUpdate();
		tx.commit();
		System.out.println("Amount credited to your account...");
		
	}

	@Override
	public void withdrawal() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		System.out.print("Enter account number from which U want to withdraw money :");
		String accno = sc.next();
		Account ac=(Account) session.getNamedQuery("getSingleData").setParameter(0, accno).getSingleResult();
		
		System.out.print("Enter amount for withdrawal :");
		double withdrawalAmount = sc.nextDouble();
		double remBalance = ac.getBalance();
		
		if(remBalance>500 && (remBalance-withdrawalAmount)>500) {
			 remBalance = ac.getBalance()-withdrawalAmount;
			 Query<Account> query =session.getNamedQuery("depositWithdrawMoney");
				query.setParameter(0, remBalance);
				query.setParameter(1, accno);
				query.executeUpdate();
				tx.commit();
			 System.out.println("Amount debited from your Account...");
		}	
		else
			System.out.println("You cannot withdraw more Amount....\nBalance need to maintain 500 or more");
		
		
		
		
	}

	@Override
	public void balanceCheck() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		System.out.print("Enter account number for which U want to check balance :");
		String accno = sc.next();
		Account ac=(Account) session.getNamedQuery("balanceCheck").setParameter(0, accno).getSingleResult();
		System.out.println("Account balance -" +ac.getBalance());
		
		
	}
		
}

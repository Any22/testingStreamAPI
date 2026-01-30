package com.unitTesting.streamunitTestdemo.model;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {
	private String customerId;
	private static int id;
	// for making it thread safe (recommended)
	//private static final AtomicInteger id = new AtomicInteger(1);
	private String customerName;
	private int customerAge;

	private AccountType accountType ;

	static {
		id=01;
	}

	public Customer( String customerName,int customerAge, AccountType accountType) {
		this.customerId= "NAB-Cus-" + id++;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAge = customerAge;
		this.accountType = accountType;

	}


	public Customer() {
		// TODO Auto-generated constructor stub
	}


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Customer.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public int getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(int customerAge) {
		this.customerAge = customerAge;
	}

	@Override
	public String toString() {
		return this.customerName;
	}
}

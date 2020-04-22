/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener
 */

package log;

public class SuspectEntry {
	private String name;
	private String phoneNumber;
	private String passportNumber;
	private String cardNumber;
	
	public SuspectEntry(String name, String phoneNumber, String passportNumber, String cardNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.passportNumber = passportNumber;
		this.cardNumber = cardNumber;
	}
	
	public String getPassPort() {
		return passportNumber;
	}
	
	public String getCardNum() {
		return cardNumber;
	}
	
	public String getPhoneNum() {
		return phoneNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(Object e) {
		if(this == e) {
			return true;
		}
		
		else if(e instanceof SuspectEntry) {
			SuspectEntry b= (SuspectEntry) e;
			
			if(this.getPassPort().equals(b.getPassPort())) {
				return true;
			}
		}
		
		return false;
	}
		
}

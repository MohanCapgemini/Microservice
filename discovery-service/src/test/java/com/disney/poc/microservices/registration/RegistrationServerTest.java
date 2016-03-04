package com.disney.poc.microservices.registration;

import junit.framework.TestCase;

import org.junit.Test;

public class RegistrationServerTest extends TestCase {
	
	@Test
	public void test_main(){
		try {
			RegistrationServer.main(null);
		} catch (Exception e) {
			//ignore any exception
		}
	}

}

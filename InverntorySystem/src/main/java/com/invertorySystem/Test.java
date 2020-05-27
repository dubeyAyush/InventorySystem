package com.invertorySystem;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
	
	 public static void main(String[] args) {
		 //my-secret-key
		 //$2a$10$CRLgH5uSfsDhRpsIDjOWluBf9xRDvgSKDq8ShjGsIFQdqkZ6hd9Za
		 System.out.println(new BCryptPasswordEncoder().encode("ayushDubey"));
		 //System.out.println(new BCryptPasswordEncoder().matches("ayushDubey", "$2a$10$2y6wFxr5ZWb8KEOxuT9wTOT3mSQymLHsntiDy5ZotoCp9gEN3HT4q"));
	}

}

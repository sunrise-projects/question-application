package com.questionfactory.persistence.service.impl;

import com.questionfactory.persistence.service.Hello;

public class HelloImpl implements Hello {

	public void sayHello(String name) {
		System.out.println("Hello "+name);
	}
	
}

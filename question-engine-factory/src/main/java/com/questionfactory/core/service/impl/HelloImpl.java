package com.questionfactory.core.service.impl;

import com.questionfactory.core.service.Hello;

public class HelloImpl implements Hello {

	public void sayHello(String name) {
		System.out.println("Hello "+name);
	}
	
}

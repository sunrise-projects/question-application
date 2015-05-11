package com.question.persistence.factory.client;

import com.question.persistence.factory.spi.Persistence;
import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Persistence  p = PersistenceFactory.getInstance(PersistenceType.FILE);
		
		
		

	}

}

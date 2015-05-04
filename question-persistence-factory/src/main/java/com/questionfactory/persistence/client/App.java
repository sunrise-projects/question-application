package com.questionfactory.persistence.client;

import com.questionfactory.persistence.factory.Persistence;
import com.questionfactory.persistence.factory.PersistenceFactory;
import com.questionfactory.persistence.factory.PersistenceType;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Persistence  p = PersistenceFactory.buildPersistence(PersistenceType.FILE);
		
		
		

	}

}

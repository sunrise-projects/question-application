package com.questionfactory.core.factory;

import com.questionfactory.persistence.factory.Persistence;
import com.questionfactory.persistence.factory.PersistenceFactory;
import com.questionfactory.persistence.factory.PersistenceType;

public class TestFactoryPattern {
    public static void main(String[] args) {
    	Persistence  persistence = PersistenceFactory.buildPersistence(PersistenceType.FILE);
        System.out.println(QuestionFactory.buildQuestion(QuestionType.SIMPLE, persistence));
        System.out.println(QuestionFactory.buildQuestion(QuestionType.ADVANCE, persistence));
    }
}

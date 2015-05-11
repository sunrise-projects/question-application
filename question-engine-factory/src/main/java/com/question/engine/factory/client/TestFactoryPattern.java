package com.question.engine.factory.client;

import com.question.engine.factory.spi.QuestionFactory;
import com.question.engine.factory.spi.QuestionType;
import com.question.persistence.factory.spi.Persistence;
import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

public class TestFactoryPattern {
    public static void main(String[] args) {
    	Persistence persistence = PersistenceFactory.getInstance(PersistenceType.FILE);
        System.out.println(QuestionFactory.getInstance(QuestionType.SIMPLE, persistence));
        System.out.println(QuestionFactory.getInstance(QuestionType.ADVANCE, persistence));
    }
}

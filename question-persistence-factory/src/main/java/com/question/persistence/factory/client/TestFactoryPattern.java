package com.question.persistence.factory.client;

import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

public class TestFactoryPattern {
    public static void main(String[] args) {
        System.out.println(PersistenceFactory.getInstance(PersistenceType.FILE));
        System.out.println(PersistenceFactory.getInstance(PersistenceType.MYSQL));

    }
}

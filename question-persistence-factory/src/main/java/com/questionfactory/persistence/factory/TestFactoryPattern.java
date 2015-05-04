package com.questionfactory.persistence.factory;

public class TestFactoryPattern {
    public static void main(String[] args) {
        System.out.println(PersistenceFactory.buildPersistence(PersistenceType.FILE));
        System.out.println(PersistenceFactory.buildPersistence(PersistenceType.MYSQL));

    }
}

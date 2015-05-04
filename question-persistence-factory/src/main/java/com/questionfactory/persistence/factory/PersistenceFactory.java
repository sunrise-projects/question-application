package com.questionfactory.persistence.factory;

public class PersistenceFactory {
    public static Persistence buildPersistence(PersistenceType type) {
        Persistence persistence = null;
        switch (type) {
        case FILE:
        	persistence = new FilePersistence();
            break;
            
        case MYSQL:
        	persistence = new MySQLPersistence();
            break;
            
        default:
            // throw some exception
            break;
        }
        return persistence;
    }
}
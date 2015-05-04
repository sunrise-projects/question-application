package com.questionfactory.core.factory;

import com.questionfactory.persistence.factory.Persistence;

public class QuestionFactory {
    public static Question buildQuestion(QuestionType type, Persistence persistence) {
        Question question = null;
        switch (type) {
 
        case SIMPLE:
        	question = new SimpleQuestion(persistence);
            break;
 
        case ADVANCE:
        	question = new AdvanceQuestion(persistence);
            break;
 
        default:
            // throw some exception
            break;
        }
        return question;
    }
}
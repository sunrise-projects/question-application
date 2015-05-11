package com.question.engine.factory.spi;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.persistence.factory.spi.Persistence;
public abstract class Question {
	
    public Question(QuestionType type, Persistence persistence) {
        this.type = type;
        this.persistence = persistence;
        initialize();
    }
 
    private void initialize() {
        // Do one time processing here
    }
 
    // Do subclass level processing in this method
    protected abstract void construct();
 
    private QuestionType type = null;
    private Persistence persistence = null;
    
    
    public Persistence getPersistence() {
		return persistence;
	}

	public void setPersistence(Persistence persistence) {
		this.persistence = persistence;
	}

	public QuestionType getType() {
        return type;
    }
 
    public void setType(QuestionType type) {
        this.type = type;
    }
    
    public abstract <T> QuestionBucket getFirstQuestion(T input, String memberNumber);
    
    public abstract AnswerBucket checkAnswer(String answer, String memberNumber, String sessionId, int questionNumber);
    
    public abstract QuestionBucket getNextQuestion(String memberNumber, String sessionId);
    
    public abstract QuestionBucket getWrongAnswer(String memberNumber, String sessionId);
    
}
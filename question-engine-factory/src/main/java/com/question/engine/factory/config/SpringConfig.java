package com.question.engine.factory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.question.engine.factory.impl.simple.actions.CheckAnswerNegativeAction;
import com.question.engine.factory.impl.simple.actions.CheckAnswerPositiveAction;
import com.question.engine.factory.impl.simple.actions.NextQuestionNegativeAction;
import com.question.engine.factory.impl.simple.actions.NextQuestionPositiveAction;
import com.question.engine.factory.impl.simple.actions.StartQANegativeAction;
import com.question.engine.factory.impl.simple.actions.StartQAPositiveAction;
import com.question.engine.factory.impl.simple.actions.WrongAnswerNegativeAction;
import com.question.engine.factory.impl.simple.actions.WrongAnswerPositiveAction;
import com.question.engine.factory.impl.simple.framework.SpringRuleEngine;
import com.question.engine.factory.impl.simple.rules.CheckAnswerRule;
import com.question.engine.factory.impl.simple.rules.NextQuestionRule;
import com.question.engine.factory.impl.simple.rules.StartQARule;
import com.question.engine.factory.impl.simple.rules.WrongAnswerRule;
import com.question.engine.factory.impl.simple.services.SerializedQAPersistence;


@Configuration
public class SpringConfig {
 
    
    @Bean(name="QAPersistenceService")
    public SerializedQAPersistence qaPersistenceService() {
    	SerializedQAPersistence v = new SerializedQAPersistence();
        return v;
    }

    //------------------------------ wrong answer rules
    
    @Bean(name="WrongAnswerPositiveAction")
    public WrongAnswerPositiveAction wrongAnswerPositiveAction() {
    	WrongAnswerPositiveAction v = new WrongAnswerPositiveAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="WrongAnswerNegativeAction")
    public WrongAnswerNegativeAction wrongAnswerNegativeAction() {
    	WrongAnswerNegativeAction v = new WrongAnswerNegativeAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="WrongAnswerRule")
    public WrongAnswerRule wrongAnswerRule() {
    	WrongAnswerRule v = new WrongAnswerRule();
    	v.setNegativeOutcomeStep(wrongAnswerNegativeAction());
    	v.setPositiveOutcomeStep(wrongAnswerPositiveAction());
        return v;
    }
    
    @Bean(name="WrongAnswerProcessor")
    public SpringRuleEngine wrongAnswerProcessor() {
    	SpringRuleEngine v = new SpringRuleEngine();
    	v.setFirstStep(wrongAnswerRule());
        return v;
    }
    
    
    //------------------------------ check answer rules
    
    @Bean(name="CheckAnswerPositiveAction")
    public CheckAnswerPositiveAction checkAnswerPositiveAction() {
    	CheckAnswerPositiveAction v = new CheckAnswerPositiveAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="CheckAnswerNegativeAction")
    public CheckAnswerNegativeAction checkAnswerNegativeAction() {
    	CheckAnswerNegativeAction v = new CheckAnswerNegativeAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="CheckAnswerRule")
    public CheckAnswerRule checkAnswerRule() {
    	CheckAnswerRule v = new CheckAnswerRule();
    	v.setNegativeOutcomeStep(checkAnswerNegativeAction());
    	v.setPositiveOutcomeStep(checkAnswerPositiveAction());
        return v;
    }
    
    @Bean(name="CheckAnswerProcessor")
    public SpringRuleEngine checkAnswerProcessor() {
    	SpringRuleEngine v = new SpringRuleEngine();
    	v.setFirstStep(checkAnswerRule());
        return v;
    }
    
    //------------------------------ next question rules
    
    @Bean(name="NextQuestionPositiveAction")
    public NextQuestionPositiveAction nextQuestionPositiveAction() {
    	NextQuestionPositiveAction v = new NextQuestionPositiveAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="NextQuestionNegativeAction")
    public NextQuestionNegativeAction nextQuestionNegativeAction() {
    	NextQuestionNegativeAction v = new NextQuestionNegativeAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="NextQuestionRule")
    public NextQuestionRule nextQuestionRule() {
    	NextQuestionRule v = new NextQuestionRule();
    	v.setNegativeOutcomeStep(nextQuestionNegativeAction());
    	v.setPositiveOutcomeStep(nextQuestionPositiveAction());
        return v;
    }
    
    @Bean(name="NextQuestionProcessor")
    public SpringRuleEngine nextQuestionProcessor() {
    	SpringRuleEngine v = new SpringRuleEngine();
    	v.setFirstStep(nextQuestionRule());
        return v;
    }
    
    //------------------------------ inital rules
    
        

    @Bean(name="StartQAPositiveAction")
    public StartQAPositiveAction startQAPositiveAction() {
    	StartQAPositiveAction v = new StartQAPositiveAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="StartQANegativeAction")
    public StartQANegativeAction startQANegativeAction() {
    	StartQANegativeAction v = new StartQANegativeAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="StartQARule")
    public StartQARule startQARule() {
    	StartQARule v = new StartQARule();
    	v.setNegativeOutcomeStep(startQANegativeAction());
    	v.setPositiveOutcomeStep(startQAPositiveAction());
        return v;
    }
    
    @Bean(name="StartQAProcessor")
    public SpringRuleEngine startQAProcessor() {
    	SpringRuleEngine v = new SpringRuleEngine();
    	v.setFirstStep(startQARule());
        return v;
    }
    
   
 
}
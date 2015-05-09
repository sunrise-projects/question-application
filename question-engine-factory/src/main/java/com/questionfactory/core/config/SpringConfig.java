package com.questionfactory.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.questionfactory.core.service.Hello;
import com.questionfactory.core.service.impl.HelloImpl;
import com.questionfactory.core.simple.actions.CheckAnswerNegativeAction;
import com.questionfactory.core.simple.actions.CheckAnswerPositiveAction;
import com.questionfactory.core.simple.actions.NextQuestionNegativeAction;
import com.questionfactory.core.simple.actions.NextQuestionPositiveAction;
import com.questionfactory.core.simple.actions.StartQANegativeAction;
import com.questionfactory.core.simple.actions.StartQAPositiveAction;
import com.questionfactory.core.simple.actions.WrongAnswerNegativeAction;
import com.questionfactory.core.simple.actions.WrongAnswerPositiveAction;
import com.questionfactory.core.simple.framework.SpringRuleEngine;
import com.questionfactory.core.simple.rules.CheckAnswerRule;
import com.questionfactory.core.simple.rules.NextQuestionRule;
import com.questionfactory.core.simple.rules.StartQARule;
import com.questionfactory.core.simple.rules.WrongAnswerRule;
import com.questionfactory.core.simple.services.SerializedQAPersistence;


@Configuration
public class SpringConfig {
 
    @Bean(name="helloBean")
    public Hello helloWorld() {
        return new HelloImpl();
    }
    
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
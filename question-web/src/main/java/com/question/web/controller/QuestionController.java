package com.question.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.web.services.QuestionService;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	
	//http://localhost:9999/question-web/rest/questions/getFirstQuestion/1/
	//http://localhost:9999/question-web/rest/questions/getNextQuestion/1/ff726f72-f068-4109-a8c2-f631b6438709/
	
	@Inject
	private QuestionService questionService;

	@RequestMapping(value = "/getFirstQuestion/{memberNumber}", method = RequestMethod.GET)
	public @ResponseBody
	QuestionBucket getFirstQuestion(@PathVariable String memberNumber) {
		QuestionBucket questionBucket = questionService.get(
				"/rest/questions/getFirstQuestion/"+memberNumber, QuestionBucket.class);
		System.out.println(questionBucket.toString());
		return questionBucket;
	}

	@RequestMapping(value = "/getNextQuestion/{memberNumber}/{sessionId}", method = RequestMethod.GET)
	public @ResponseBody
	QuestionBucket getNextQuestion(@PathVariable("memberNumber") String memberNumber, 
			@PathVariable("sessionId") String sessionId) {
		QuestionBucket questionBucket = questionService.get(
				"/rest/questions/getNextQuestion/"+memberNumber+"/"+sessionId, QuestionBucket.class);
		System.out.println(questionBucket.toString());
		return questionBucket;
	}

	@RequestMapping(value = "/getWrongAnswer/{memberNumber}/{sessionId}", method = RequestMethod.GET)
	public @ResponseBody
	QuestionBucket getWrongAnswer(@PathVariable("memberNumber") String memberNumber, 
			@PathVariable("sessionId") String sessionId) {
		QuestionBucket questionBucket = questionService.get(
				"/rest/questions/getWrongAnswer/"+memberNumber+"/"+sessionId, QuestionBucket.class);
		System.out.println(questionBucket.toString());
		return questionBucket;
	}	
	

	@RequestMapping(value = "/checkAnswer/{answer}/{memberNumber}/{sessionId}/{questionNumber}", method = RequestMethod.GET)
	public @ResponseBody
	AnswerBucket checkAnswer(
			@PathVariable("answer") String answer,
			@PathVariable("memberNumber") String memberNumber, 
			@PathVariable("sessionId") String sessionId,
			@PathVariable("questionNumber") int questionNumber
			) {
		AnswerBucket answerBucket = questionService.get(
				"/rest/questions/checkAnswer/"+answer+"/"+memberNumber+"/"+sessionId+"/"+questionNumber, AnswerBucket.class);
		System.out.println(answerBucket.toString());
		return answerBucket;
	}		
}
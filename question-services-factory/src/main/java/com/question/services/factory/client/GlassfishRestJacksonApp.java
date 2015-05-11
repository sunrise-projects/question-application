package com.question.services.factory.client;

import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.services.factory.spi.Webservices;
import com.question.services.factory.spi.WebservicesFactory;
import com.question.services.factory.spi.WebservicesType;

public class GlassfishRestJacksonApp {

	public static void main(String[] args) {
		
		Webservices service = WebservicesFactory.getInstance(WebservicesType.GLASSSFISH_JERSEY_JACKSON_REST_CLIENT);

		String jsonString = service.get("http://localhost:8080/question-rest","/rest/questions/getFirstQuestion/1", String.class);
		System.out.println(jsonString);
		
		QuestionBucket questionBucket = service.get("http://localhost:8080/question-rest","/rest/questions/getFirstQuestion/1", QuestionBucket.class);
		System.out.println(questionBucket.toString());

		String jsonNextString = service.get("http://localhost:8080/question-rest/rest","/questions/getNextQuestion/1/"+questionBucket.getSessionId(), String.class);
		System.out.println(jsonNextString);
			

	}
}

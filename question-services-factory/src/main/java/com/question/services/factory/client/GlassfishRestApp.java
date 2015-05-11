package com.question.services.factory.client;

import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.services.factory.spi.Webservices;
import com.question.services.factory.spi.WebservicesFactory;
import com.question.services.factory.spi.WebservicesType;

public class GlassfishRestApp {

	public static void main(String[] args) {
		
		Webservices service = WebservicesFactory.getInstance(WebservicesType.GLASSSFISH_JERSEY_REST_CLIENT);
		
		String jsonString = service.get("/rest/questions/getFirstQuestion/1", String.class);
		System.out.println(jsonString);
		
		QuestionBucket questionBucket = service.get("/rest/questions/getFirstQuestion/1", QuestionBucket.class);
		System.out.println(questionBucket.toString());

		String jsonNextString = service.get("/rest/questions/getNextQuestion/1/"+questionBucket.getSessionId(), String.class);
		System.out.println(jsonNextString);
			

	}
}

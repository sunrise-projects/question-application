package com.question.services.factory.client;

import java.io.File;

import com.question.services.factory.model.QuestionBucket;
import com.question.services.factory.spi.Webservices;
import com.question.services.factory.spi.WebservicesFactory;
import com.question.services.factory.spi.WebservicesType;

public class JerseyRestApp {

	public static void main(String[] args) {
		
		Webservices service = WebservicesFactory.getInstance(WebservicesType.JERSEY_REST_CLIENT);
		
		String jsonString = service.get("http://localhost:8080/question-rest/rest/questions/getFirstQuestion/1", String.class);
		System.out.println(jsonString);
		
		QuestionBucket questionBucket = service.get("http://localhost:8080/question-rest/rest/questions/getFirstQuestion/1", QuestionBucket.class);
		System.out.println(questionBucket.toString());

		String jsonNextString = service.get("http://localhost:8080/question-rest/rest/questions/getNextQuestion/1/"+questionBucket.getSessionId(), String.class);
		System.out.println(jsonNextString);
		
		QuestionBucket questionBucket2 = service.put("http://localhost:8080/question-rest/rest/questions/getFirstQuestion/1/inputFile", new File("/usr/home/blight/javaapps/33/question-application/question-engine-factory/src/main/resources/excel/sample2-tab-lx.xlsx"), QuestionBucket.class);
		System.out.println(questionBucket2.toString());
			

	}
}

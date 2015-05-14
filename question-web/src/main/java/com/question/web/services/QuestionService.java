package com.question.web.services;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.services.factory.spi.Webservices;
import com.question.services.factory.spi.WebservicesFactory;
import com.question.services.factory.spi.WebservicesType;

@Service
public class QuestionService {

	private Webservices service = null;

	//final String baseUrl = "http://localhost:8080/question-rest";
	final String baseUrl = "https://portal-librequestion.rhcloud.com/question-rest";
	
	@PostConstruct
	public void initIt() throws Exception {
		System.out
				.println("QuestionService-web Init method after properties are set");

		service = WebservicesFactory.getInstance(WebservicesType.JERSEY_REST_CLIENT);

//		String jsonString = service.get("http://localhost:8080/question-rest/rest/questions/getFirstQuestion/1",
//				String.class);
//		System.out.println(jsonString);
//
//		QuestionBucket questionBucketStr = service.get("http://localhost:8080/question-rest/rest/questions/getFirstQuestion/1",
//				QuestionBucket.class);
//		System.out.println(questionBucketStr);
		
		


	}

	@PreDestroy
	public void cleanUp() throws Exception {
		System.out
				.println("QuestionService-web Spring Container is destroy! Customer clean up");
	}

	public <T> T get(String url, Class<T> clazz) {
		return service.get(baseUrl+url, clazz);
	}
	
	public <T> T put(String url, File fileToUpload, Class<T> clazz) {
		return service.put(baseUrl+url, fileToUpload, clazz);
	}
	

}
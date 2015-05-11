package com.question.services.factory.spi;

import javax.ws.rs.core.MediaType;

import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyRestImpl extends Webservices {

	// http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-jersey-client/

	private Client client = null;

	JerseyRestImpl() {
		super(WebservicesType.JERSEY_REST_CLIENT);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Building JERSEY_REST services");
		try {
			client = Client.create();
		} catch (Exception e) {
			throw new RuntimeException("Error : " + e.getMessage());
		}
	}

	public <T> T get(String url, Class<T> clazz) {
		T resp;
		try {
			resp = clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("Error : " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error : " + e.getMessage());
		}

		WebResource webResource = client
				.resource(url);

		resp = (T)webResource.accept(MediaType.APPLICATION_JSON).get(
				clazz);

		return resp;
	}

	@Override
	public <T> T get(String baseUrl, String pathUrl, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
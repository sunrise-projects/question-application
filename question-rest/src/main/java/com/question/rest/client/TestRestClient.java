package com.question.rest.client;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBException;

public class TestRestClient {

	public static void main(String args[]) throws IOException,
			URISyntaxException, JAXBException {
//		OperationInput input = new OperationInput();
//		input.setFirstNumber(1);
//		input.setSecondNumber(2);
//		try {
//			String restUrl = "http://localhost:8080/jaxrs-poc";
//			Client client = ClientBuilder.newClient(new ClientConfig()).register(JacksonFeature.class);
//			WebTarget webTarget = client.target(restUrl);
//			Response response = webTarget
//					.path("/rest/operations/add")
//					.request(MediaType.APPLICATION_JSON)
//					.post(Entity.json(input),
//							Response.class);
//			OperationOutput app2 = response.readEntity(OperationOutput.class);
//			System.out.println(app2.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}
}

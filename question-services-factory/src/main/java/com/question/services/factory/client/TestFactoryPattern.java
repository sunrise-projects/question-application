package com.question.services.factory.client;

import com.question.services.factory.spi.WebservicesFactory;
import com.question.services.factory.spi.WebservicesType;

public class TestFactoryPattern {
    public static void main(String[] args) {
    	System.out.println(WebservicesFactory.getInstance(WebservicesType.JERSEY_REST_CLIENT));
    	System.out.println(WebservicesFactory.getInstance(WebservicesType.GLASSSFISH_JERSEY_REST_CLIENT));
        System.out.println(WebservicesFactory.getInstance(WebservicesType.SOAP));

    }
}

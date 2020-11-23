package com.myproject.publisher;

import javax.xml.ws.Endpoint;

import com.myproject.service.SakilaActorImpl;

public class SakilaActorPublisher {

	public static void main(String[] args) {
		Endpoint ep = Endpoint.create(new SakilaActorImpl());
		ep.publish("http://localhost:8080/SakilaActor/services/SakilaActorImpl");

	}

}

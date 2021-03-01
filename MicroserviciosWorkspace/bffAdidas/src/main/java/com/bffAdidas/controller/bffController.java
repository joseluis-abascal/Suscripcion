package com.bffAdidas.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bffAdidas.model.SuscripcionRest;

@RestController
public class bffController {
	   @Autowired
	   RestTemplate restTemplate;
	   //listar
	   @RequestMapping(value = "/adidas/subscriptions", method = RequestMethod.GET)
	   public String getSubscriptionList() {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://suscripcion-container:8081/subscription/list", HttpMethod.GET, entity, String.class).getBody();
	   }
	   //busqueda
	   @RequestMapping(value = "/adidas/subscriptions/{id}", method = RequestMethod.GET)
	   public String getSubscription(@PathVariable("id") String id) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://suscripcion-container:8081/subscription/list/"+id, HttpMethod.GET, entity, String.class).getBody();
	   }
	   //alta
	   @RequestMapping(value = "/adidas/createSubscription", method = RequestMethod.POST)
	   public String createSubscription(@RequestBody SuscripcionRest sus) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<SuscripcionRest> entity = new HttpEntity<SuscripcionRest>(sus,headers);
	      
	      return restTemplate.exchange(
	         "http://suscripcion-container:8081/subscription/insert", HttpMethod.POST, entity, String.class).getBody();
	   }
	   //baja
	   @RequestMapping(value = "/adidas/deleteSubscription/{id}", method = RequestMethod.DELETE)
	   public String deleteSubscription(@PathVariable("id") String id) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange(
	         "http://suscripcion-container:8081/subscription/delete/"+id, HttpMethod.DELETE, entity, String.class).getBody();
	   }
}


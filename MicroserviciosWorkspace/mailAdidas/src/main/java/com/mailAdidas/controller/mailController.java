package com.mailAdidas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mailAdidas.model.Respuesta;
import com.mailAdidas.model.SuscripcionRest;

@RestController
@RequestMapping(value = "mail")
public class mailController {
	@Autowired
    private JavaMailSender mailSender;
     
	@PostMapping("/send/{id}")
    public Respuesta sendEmail(@RequestBody SuscripcionRest subscriptionRest,@PathVariable("id") String id) {
		Respuesta respuesta=new Respuesta();
    	String from = "DoNotReply@adidas.com";
    	String to = subscriptionRest.getEmail();
    	 
    	SimpleMailMessage message = new SimpleMailMessage();
    	 
    	message.setFrom(from);
    	message.setTo(to);
    	message.setSubject("Adidas subscription process");
    	message.setText("Hi guy, subscription done! (Subscription Id: "+id+")");
    	 
    	//mailSender.send(message);
    	respuesta.setCodResuesta("OK");
    	respuesta.setMsjRespuesta("Mail enviado");
    	return respuesta;
    }  

}

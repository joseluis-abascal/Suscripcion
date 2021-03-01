package com.suscripcionAdidas.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.suscripcionAdidas.dao.SuscripcionDAO;
import com.suscripcionAdidas.model.Respuesta;
import com.suscripcionAdidas.model.Suscripcion;
import com.suscripcionAdidas.model.SuscripcionRest;

@RestController
@RequestMapping(value = "subscription")
public class SuscripcionController {
	@Autowired
	private SuscripcionDAO suscripcionDAO;
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/insert")
	public Respuesta alta(@RequestBody SuscripcionRest subscriptionRest) {
		Respuesta res=new Respuesta();
		res.setCodResuesta("ERROR");
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		Suscripcion subscription = new Suscripcion();
		String respuesta="";
		
		respuesta=checkFields(subscriptionRest);
		
		if("Registry inserted".equalsIgnoreCase(respuesta)) { 
			subscription.setEmail(subscriptionRest.getEmail());
			subscription.setFirst_name(subscriptionRest.getFirst_name());
			subscription.setGender(subscriptionRest.getGender());		
			try{
				subscription.setDate_of_birth(sdf.parse(subscriptionRest.getDate_of_birth()));			
			}catch(Exception e) {
				//it will never happen
			}
			subscription.setConsent_flag(subscriptionRest.getConsent_flag().charAt(0));
			subscription.setNewsletterid(subscriptionRest.getNewsletter_id());
			
			if(suscripcionDAO.existsByEmailAndNewsletterid(subscriptionRest.getEmail(),subscriptionRest.getNewsletter_id())) {
				respuesta="The email "+subscriptionRest.getEmail()+" is already subscribed to "+subscriptionRest.getNewsletter_id();
			}else {
				suscripcionDAO.save(subscription);
				res.setCodResuesta("OK");
				Suscripcion insertado=suscripcionDAO.findByEmailAndNewsletterid(subscriptionRest.getEmail(),subscriptionRest.getNewsletter_id());
				respuesta += " - "+insertado.getSubscription_id();
				
				  HttpHeaders headers = new HttpHeaders();
			      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			      HttpEntity<SuscripcionRest> entity = new HttpEntity<SuscripcionRest>(subscriptionRest,headers);
			      try {
			    	  restTemplate.exchange("http://mail-container:8082/mail/send/"+insertado.getSubscription_id(), HttpMethod.POST, entity, String.class).getBody();
			      }catch(Exception e) {
			    	  respuesta += " - mail sending error";
			      }
			}
		}
		res.setMsjRespuesta(respuesta);
		return res;
	}
	
	@DeleteMapping("/delete/{item}")
	public Respuesta baja(@PathVariable("item") String item) {
		Respuesta res=new Respuesta();
		int subscriptionId=0;
		try {
			subscriptionId=Integer.parseInt(item);
		}catch(Exception e) {
			//el identificador es alfanumerico
		}
		try {
			if(suscripcionDAO.existsById(subscriptionId)) {
				suscripcionDAO.deleteById(subscriptionId);
				res.setCodResuesta("OK");
				res.setMsjRespuesta("Subscription delete "+item);
			}else {
				res.setCodResuesta("ERROR");
				res.setMsjRespuesta("The subscription "+item+" is not in our database");
			}
		}catch(Exception e) {
			res.setCodResuesta("ERROR");
			res.setMsjRespuesta("Database access error");
		}
		
		return res;
	}
	
	@GetMapping("/list/{item}")
	public SuscripcionRest listar(@PathVariable("item") String item){
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		int subscriptionId=0;
		try {
			subscriptionId=Integer.parseInt(item);
		}catch(Exception e) {
			//el identificador es alfanumerico
		}
		if(suscripcionDAO.existsById(subscriptionId)) {
			Optional<Suscripcion> optSus= suscripcionDAO.findById(subscriptionId);
			Suscripcion suscripcion=optSus.get();
			
			SuscripcionRest susRest = new SuscripcionRest();
			susRest.setSubscription_id(suscripcion.getSubscription_id()+"");
			susRest.setEmail(suscripcion.getEmail());
			susRest.setFirst_name(suscripcion.getFirst_name());
			susRest.setGender(suscripcion.getGender());
			susRest.setDate_of_birth(sdf.format(suscripcion.getDate_of_birth()));
			susRest.setConsent_flag(suscripcion.getConsent_flag()+"");
			susRest.setNewsletter_id(suscripcion.getNewsletterid());
			
			return susRest;
		}	
		return null;
	}
	
	@GetMapping("/list")
	public List<SuscripcionRest> listar(){
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		List<SuscripcionRest> suscripcionesRest = new ArrayList<SuscripcionRest>();
		
		List<Suscripcion> suscripciones= suscripcionDAO.findAll();
		for (Suscripcion suscripcion: suscripciones) {
			SuscripcionRest susRest = new SuscripcionRest();
			susRest.setSubscription_id(suscripcion.getSubscription_id()+"");
			susRest.setEmail(suscripcion.getEmail());
			susRest.setFirst_name(suscripcion.getFirst_name());
			susRest.setGender(suscripcion.getGender());
			susRest.setDate_of_birth(sdf.format(suscripcion.getDate_of_birth()));
			susRest.setConsent_flag(suscripcion.getConsent_flag()+"");
			susRest.setNewsletter_id(suscripcion.getNewsletterid());
			
			suscripcionesRest.add(susRest);
		}
		return suscripcionesRest;
	}

	
	private String checkFields(SuscripcionRest subscriptionRest) {
		String respuesta="Registry inserted";
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		//email
		if(subscriptionRest.getEmail()==null || "".equals(subscriptionRest.getEmail().trim())) {
			respuesta="Email not supplied";
		}else if(subscriptionRest.getEmail().trim().length()>255) {
			respuesta="Check the email length";
		}
		//nombre
		if(subscriptionRest.getFirst_name()!=null && subscriptionRest.getFirst_name().trim().length()>255) {
			respuesta="Check the first name length";
		}
		//genero
		if(subscriptionRest.getGender()!=null && subscriptionRest.getGender().trim().length()>255) {
			respuesta="Check the gender length";
		}
		//fecha_nacimiento
		if(subscriptionRest.getDate_of_birth()==null || "".equals(subscriptionRest.getDate_of_birth().trim())) {
			respuesta="Date of birth not supplied";
		}else{
			try {			
				sdf.parse(subscriptionRest.getDate_of_birth());
				String[] partes=subscriptionRest.getDate_of_birth().trim().split("/");
				if(partes[0].length()>2 || partes[1].length()>2 || partes[2].length()!=4)
					respuesta="Check date of birth format (dd/MM/yyyy)";
			}catch(Exception e) {
				respuesta="Check date of birth format (dd/MM/yyyy)";
			}			
		}
		//consent flag
		if(subscriptionRest.getConsent_flag()==null || "".equals(subscriptionRest.getConsent_flag().trim())) {
			respuesta="Consent flag not supplied";
		}else if(subscriptionRest.getConsent_flag().trim().length()>1) {
			respuesta="Check the consent flag length. Should be 1 character";
		}
		//newsletter id
		if(subscriptionRest.getNewsletter_id()==null || "".equals(subscriptionRest.getNewsletter_id().trim())) {
			respuesta="Newsletter id not supplied";
		}else if(subscriptionRest.getNewsletter_id().trim().length()>255) {
			respuesta="Check the newsletter id length";
		}
		return respuesta;
	}
}

package com.suscripcionAdidas.model;

public class SuscripcionRest {
	private String subscription_id;
	private String email;
	private String first_name;
	private String gender;
	private String date_of_birth;
	private String consent_flag;
	private String newsletter_id;
	
	//Getters y Setters
	public String getSubscription_id() {
		return subscription_id;
	}
	public void setSubscription_id(String subscription_id) {
		this.subscription_id = subscription_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getConsent_flag() {
		return consent_flag;
	}
	public void setConsent_flag(String consent_flag) {
		this.consent_flag = consent_flag;
	}
	public String getNewsletter_id() {
		return newsletter_id;
	}
	public void setNewsletter_id(String newsletter_id) {
		this.newsletter_id = newsletter_id;
	}
	
}

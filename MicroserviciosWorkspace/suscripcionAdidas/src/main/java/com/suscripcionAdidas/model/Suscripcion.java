package com.suscripcionAdidas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Suscripcion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int subscription_id;
	@Column
	private String email;
	@Column
	private String first_name;
	@Column
	private String gender;
	@Column
	private Date date_of_birth;
	@Column
	private char consent_flag;
	@Column
	private String newsletterid;
	
	//Getters y Setters	
	public int getSubscription_id() {
		return subscription_id;
	}
	public void setSubscription_id(int subscription_id) {
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
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public char getConsent_flag() {
		return consent_flag;
	}
	public void setConsent_flag(char consent_flag) {
		this.consent_flag = consent_flag;
	}
	public String getNewsletterid() {
		return newsletterid;
	}
	public void setNewsletterid(String newsletterid) {
		this.newsletterid = newsletterid;
	}
	
	
	
}

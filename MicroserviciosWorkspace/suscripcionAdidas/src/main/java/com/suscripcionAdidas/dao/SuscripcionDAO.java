package com.suscripcionAdidas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suscripcionAdidas.model.Suscripcion;

public interface SuscripcionDAO extends JpaRepository<Suscripcion, Integer> {
	boolean existsByEmailAndNewsletterid(String email, String newsletter_id);
	Suscripcion findByEmailAndNewsletterid(String email, String newsletter_id);
}

package com.myproject.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.myproject.model.Actor;
import com.myproject.service.SakilaActorImpl;

public class ManagerSakilaActor {
	private static final Logger log = Logger.getLogger(ManagerSakilaActor.class);

	SakilaActorImpl sakilaActorImpl = new SakilaActorImpl();

	public void letturaAttori() {
		sakilaActorImpl.findAllActor();
		log.info("Lettura di tutti gli attori.");
	}

	public void cercaPerId(int id) {
		sakilaActorImpl.findsActorById(id);
		log.info("Ricerca effettuata tramite id");

	}

	public void cercaPerNome(@Valid String name) {
		sakilaActorImpl.findsActorByNome(name);
		log.info("Ricerca effettuata tramite nome");

	}

	public void aggiornamento(@Valid @ModelAttribute Actor actor, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			log.info("Errore");
		}

		Actor agg = new Actor();
		agg.setFirstName(actor.getFirstName());
		agg.setActorId(actor.getActorId());
		model.addAttribute("aggiornamento", agg);

		log.info("Input validi.");
		sakilaActorImpl.modificaNome(actor);
		log.info("Attore aggiornato");

	}

	public void inserimento(@Valid @ModelAttribute Actor actor, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			log.info("Errore");
		}

		Actor inser = new Actor();
		inser.setActorId(actor.getActorId());
		inser.setFirstName(actor.getFirstName());
		inser.setLastName(actor.getLastName());
		inser.setLastUpdate(actor.getLastUpdate());
		model.addAttribute("inserimento ", inser);

		log.info("Input validi.");
		sakilaActorImpl.inserisciAttore(actor);
		log.info("Attore inserito");

	}

	public void eliminazione(int id) {
		sakilaActorImpl.cancAttore(id);

		log.info("Cancellazione effettuata");

	}

	public void ordineDecrescente() {
		sakilaActorImpl.orderBy();
		log.info("Criteria API ordine decrescente");

	}

	public void ClausolaWhere() {

		sakilaActorImpl.queryWhere();
		log.info("Criteria API con clausola where");
	}

	public void Sp() {
		sakilaActorImpl.chiamoSp();

		log.info("Ricerca nome tramite Stored procedure");
	}

}

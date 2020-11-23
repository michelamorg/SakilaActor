package com.myproject.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.myproject.dao.ActorDao;
import com.myproject.dao.ActorDaoImpl;
import com.myproject.model.Actor;

@WebService(endpointInterface = "com.myproject.service.SakilaActor")
public class SakilaActorImpl implements SakilaActor {

	private static final Logger log = Logger.getLogger(SakilaActorImpl.class);

	ActorDao actorDao = new ActorDaoImpl();

	@Override
	public List<Actor> findAllActor() {
		List<Actor> list = actorDao.letturaAttori();
		for (Actor a : list) {
			log.info(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName() + " " + a.getLastUpdate());

		}

		log.info("Lettura di tutti gli Attori!");
		return list;
	}

	@Override
	public List<Actor> findsActorById(int id) {
		List<Actor> trovaConId = actorDao.trovaConId(id);
		for (Actor a : trovaConId) {
			log.info(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName() + " " + a.getLastUpdate());

		}
		log.info("trovato attore tramite id!");
		return trovaConId;
	}

	@Override
	public List<Actor> findsActorByNome(String name) {
		List<Actor> trovaConNome = actorDao.TrovaConNome(name);
		for (Actor a : trovaConNome) {
			log.info(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName() + " " + a.getLastUpdate());

		}
		
		
		log.info("trovato attore tramite nome!");
		return trovaConNome;
	}

	@Override
	public void inserisciAttore(Actor actor) {
		actorDao.insertActor(actor);

		log.info("Attore inserito!");
	}

	
	
	public static Timestamp convertStringToTimestamp(Actor actor) {
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			Date date = (Date) formatter.parse(actor.getLastUpdate());
			
			Timestamp timeStampDate = new Timestamp(date.getTime());

			return timeStampDate;
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
			return null;
		}
	}

	
	
	@Override
	public void modificaNome(Actor actor) {
		//List<Actor> list = actorDao.trovaConId(actor.getActorId());
		int mod =actorDao.updateActor(actor);
		if (mod >0) {
			//actorDao.updateActor(actor);
			log.info("esiste almeno un record per la modifica!");
			log.info("record aggiornato.");

		} else {
			log.info("non esiste un record per modificare");
		}

	}

	@Override
	public void cancAttore(int id) {
		int canc=actorDao.deleteActor(id);
		//List<Actor> list = actorDao.trovaConId(id);
		if (canc >0) {
			//actorDao.deleteActor(id);
			log.info("esiste almeno un record per cancellare !");
			log.info("record cancellato.");
		} else {
			log.info("non esiste record da cancellare.");
		}

	}

	@Override
	public void chiamoSp() {
		actorDao.callSp();
		

		log.info("Funzione effettuata con JPA");

	}

	@Override
	public void queryWhere() {
		actorDao.actorWhere();
		log.info("Ho selezionato gli attori con id inferiore a 22!");

	}

	@Override
	public void orderBy() {
		actorDao.orderByDescActor();
		log.info("ordine decrescente!");
	}

}

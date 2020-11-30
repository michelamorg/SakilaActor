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
		log.info("SakilaActorImpl.findAllActor init");
		List<Actor> list = actorDao.letturaAttori();
		for (Actor a : list) {
			log.info(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName() + " " + a.getLastUpdate());

		}
		log.info("SakilaActorImpl.findAllActor end");
		return list;
	}

	@Override
	public List<Actor> findsActorById(int id) {
		log.info("SakilaActorImpl.findsActorById init");
		List<Actor> trovaConId = actorDao.trovaConId(id);

		if (trovaConId == null) {

		} else {

			for (Actor a : trovaConId) {
				log.info(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName() + " " + a.getLastUpdate());

			}

		}
		log.info("SakilaActorImpl.findsActorById end");
		return trovaConId;
	}

	@Override
	public List<Actor> findsActorByNome(String name) {
		log.info("SakilaActorImpl.findsActorByNome init");
		List<Actor> trovaConNome = actorDao.TrovaConNome(name);

		if (trovaConNome == null) {

		} else {

			for (Actor a : trovaConNome) {
				log.info(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName() + " " + a.getLastUpdate());

			}
		}
		log.info("SakilaActorImpl.findsActorByNome end");
		return trovaConNome;
	}

	@Override
	public void inserisciAttore(Actor actor) {
		log.info("SakilaActorImpl.inserisciAttore init");
		actorDao.insertActor(actor);

		log.info("SakilaActorImpl.inserisciAttore end");
	}

	@Override
	public void modificaNome(Actor actor) {
		log.info("SakilaActorImpl.modificaNome init");
		int mod = actorDao.updateActor(actor);
		if (mod > 0) {

			log.info("esiste almeno un record per la modifica");
			log.info("record aggiornato.");

		} else {
			log.info("non esiste un record per modificare");
		}
		log.info("SakilaActorImpl.modificaNome end");
	}

	@Override
	public void cancAttore(int id) {
		log.info("SakilaActorImpl.cancAttore init");
		int canc = actorDao.deleteActor(id);

		if (canc > 0) {

			log.info("esiste almeno un record per cancellare");
			log.info("record cancellato.");
		} else {
			log.info("non esiste record da cancellare");
		}
		log.info("SakilaActorImpl.cancAttore end");
	}

	@Override
	public void chiamoSp() {
		log.info("SakilaActorImpl.chiamoSp init");
		actorDao.callSp();
		log.info("SakilaActorImpl.chiamoSp end");

	}

	@Override
	public void queryWhere() {
		log.info("SakilaActorImpl.queryWhere init");
		actorDao.actorWhere();
		log.info("SakilaActorImpl.queryWhere end");
		log.info("Ho selezionato gli attori con id inferiore a 22");

	}

	@Override
	public void orderBy() {
		log.info("SakilaActorImpl.orderBy init");
		actorDao.orderByDescActor();
		log.info("SakilaActorImpl.orderBy end");
		log.info("Ordine decrescente");
	}

}

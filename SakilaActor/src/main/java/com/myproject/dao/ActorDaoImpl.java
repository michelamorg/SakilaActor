package com.myproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.myproject.conn.DbConn;
import com.myproject.model.Actor;
import com.myproject.utility.UtilDate;

public class ActorDaoImpl implements ActorDao {

	private static final Logger log = Logger.getLogger(ActorDaoImpl.class);

	private Connection conn;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;
	private List<Actor> listActor;
	private List<Actor> trovaConId;
	private List<Actor> trovaConNome;

	private static final String PERSISTENCE_UNIT = "myproject";
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tran;

	public ActorDaoImpl() {
		this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		this.em = emf.createEntityManager();

	}

	@Override
	public List<Actor> letturaAttori() {
		log.info("ActorDaoImpl.letturaAttori init");
		try {
			conn = com.myproject.conn.DbConn.getInstance().getConnection();

			String SQL = "SELECT * FROM ACTOR";

			stm = conn.createStatement();
			rs = stm.executeQuery(SQL);

			listActor = new ArrayList<Actor>();
			while (rs.next()) {

				Actor act = new Actor();
				act.setActorId(rs.getInt("actor_id"));
				act.setFirstName(rs.getString("first_name"));
				act.setLastName(rs.getString("last_name"));
				act.setLastUpdate(rs.getString("last_update"));

				listActor.add(act);
			}
			rs.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {
				stm.close();
			} catch (SQLException e) { //
				log.error(e.getCause());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getCause());
			}

		}
		log.info("listActor: " + listActor);
		log.info("ActorDaoImpl.letturaAttori end");
		return listActor;

	}

	@Override
	public List<Actor> trovaConId(int id) {
		log.info("ActorDaoImpl.trovaConId init");
		try {
			conn = DbConn.getInstance().getConnection();

			String sql = "SELECT * FROM ACTOR WHERE ACTOR_ID=?";

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			trovaConId = new ArrayList<>();
			while (rs.next()) {
				Actor act = new Actor();
				act.setActorId(rs.getInt("actor_id"));
				act.setFirstName(rs.getString("first_name"));
				act.setLastName(rs.getString("last_name"));
				act.setLastUpdate(rs.getString("last_update"));

				trovaConId.add(act);
			}
			rs.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {

				log.error(e.getCause());
			}
			try {
				conn.close();
			} catch (SQLException e) {

				log.error(e.getCause());
			}

		}
		log.info("trovaConId: " + trovaConId);
		log.info("ActorDaoImpl.trovaConId end");
		return trovaConId;
	}

	@Override
	public List<Actor> TrovaConNome(String name) {
		log.info("ActorDaoImpl.TrovaConNome init");
		try {
			conn = DbConn.getInstance().getConnection();

			String sql = "SELECT * FROM ACTOR WHERE FIRST_NAME=?";

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);

			rs = pstm.executeQuery();

			trovaConNome = new ArrayList<>();

			while (rs.next()) {
				Actor act = new Actor();
				act.setActorId(rs.getInt("actor_id"));
				act.setFirstName(rs.getString("first_name"));
				act.setLastName(rs.getString("last_name"));
				act.setLastUpdate(rs.getString("last_update"));

				trovaConNome.add(act);
			}
			rs.close();
		} catch (SQLException e) {
			log.error(e.getMessage());

		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				log.error(e.getCause());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getCause());
			}

		}
		log.info("trovaConNome: " + trovaConNome);
		log.info("ActorDaoImpl.TrovaConNome end");
		return trovaConNome;
	}

	@Override
	public int updateActor(Actor actor) {
		log.info("ActorDaoImpl.updateActor init");
		int modifica = 0;
		try {
			conn = DbConn.getInstance().getConnection();

			String sql = "UPDATE ACTOR SET FIRST_NAME=? WHERE ACTOR_ID=?";

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, actor.getFirstName());
			pstm.setInt(2, actor.getActorId());

			modifica = pstm.executeUpdate();

		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				log.error(e.getCause());

			}
			try {
				conn.close();
			} catch (SQLException e) {

				log.error(e.getCause());
			}

		}
		log.info("modifica: " + modifica);
		log.info("ActorDaoImpl.updateActor end");
		return modifica;
	}

	@Override
	public int deleteActor(int id) {
		log.info("ActorDaoImpl.deleteActor init");
		int canc = 0;
		try {
			conn = DbConn.getInstance().getConnection();

			String sql = "DELETE FROM ACTOR WHERE ACTOR_ID=?";

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);

			canc = pstm.executeUpdate();

		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {

				log.error(e.getCause());
			}
			try {
				conn.close();
			} catch (SQLException e) {

				log.error(e.getCause());
			}

		}
		log.info("canc: " + canc);
		log.info("ActorDaoImpl.deleteActor end");
		return canc;
	}

	@Override
	public int insertActor(Actor actor) {
		log.info("ActorDaoImpl.insertActor init");

		int insert = 0;
		try {
			conn = DbConn.getInstance().getConnection();

			String SQL = "insert into actor(actor_id, first_name, last_name, last_update) " + "values (?, ?, ?, ?)";

			pstm = conn.prepareStatement(SQL);

			pstm.setInt(1, actor.getActorId());
			pstm.setString(2, actor.getFirstName());
			pstm.setString(3, actor.getLastName());
			pstm.setTimestamp(4, UtilDate.convertStringToTimestamp(actor.getLastUpdate()));

			insert = pstm.executeUpdate();

		} catch (SQLException e) {
			log.error(e.getMessage());

		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				log.error(e.getCause());

			}
			try {
				conn.close();
			} catch (SQLException e) {

				log.error(e.getCause());
			}

		}
		log.info("insert: " + insert);
		log.info("ActorDaoImpl.insertActor end");
		return insert;

	}

	@Override
	public void callSp() {
		log.info("ActorDaoImpl.callSp init");

		StoredProcedureQuery sp = em.createStoredProcedureQuery("name_act", Actor.class)
				.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);

		sp.setParameter(1, 5);
		sp.execute();

		String actNome = (String) sp.getOutputParameterValue(2);
		System.out.println(actNome);
		log.info("ActorDaoImpl.callSp end");
		log.info("Funzione eseguita per id 5!");

	}

	@Override
	public void actorWhere() {
		log.info("ActorDaoImpl.actorWhere init");

		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		AbstractQuery<Actor> cq = cb.createQuery(Actor.class);

		Root<Actor> act = cq.from(Actor.class);

		cq.where(cb.lessThan(act.get("actorId"), 22));

		CriteriaQuery<Actor> select = ((CriteriaQuery<Actor>) cq).select(act);

		TypedQuery<Actor> tq = em.createQuery(select);

		List<Actor> list = tq.getResultList();

		System.out.println("Attore con id inferiore a 22");

		for (Actor a : list) {
			log.info(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName() + " " + a.getLastUpdate());
		}

		em.getTransaction().commit();
		log.info("ActorDaoImpl.actorWhere end");
	}

	@Override
	public void orderByDescActor() {
		log.info("ActorDaoImpl.orderByDescActor init");

		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery cq = cb.createQuery(Actor.class);

		Root<Actor> act = cq.from(Actor.class);

		cq.orderBy(cb.desc(act.get("actorId"))); 

		CriteriaQuery<Actor> select = cq.select(act);

		TypedQuery<Actor> q = em.createQuery(select);

		List<Actor> list = q.getResultList();

		log.info("Gli attori in ordine decrescente:");
		for (Actor a : list) {

			log.info(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName() + " " + a.getLastUpdate());

		}

		em.getTransaction().commit();
		log.info("ActorDaoImpl.orderByDescActor end");

	}
}

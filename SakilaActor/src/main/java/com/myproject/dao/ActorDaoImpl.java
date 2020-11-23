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

public class ActorDaoImpl implements ActorDao {
	private static final Logger log = Logger.getLogger(ActorDaoImpl.class);

	private Connection conn;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;
	private List<Actor> listActor;
	private List<Actor> trovaConId;
	private List<Actor> trovaConNome;

//JPA
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
			log.error(e.getCause());
		} finally {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getCause());
			}

		}

		return listActor;

	}

	@Override
	public List<Actor> trovaConId(int id) { 
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
			log.error(e.getCause());
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getCause());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getCause());
			}

		}
		return trovaConId;
	}

	@Override
	public List<Actor> TrovaConNome(String name) {
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
			log.error(e.getCause());

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

		return trovaConNome;
	}

	@Override
	public int updateActor(Actor actor) {
		int modifica = 0;
		try {
			conn = DbConn.getInstance().getConnection();

			String sql = "UPDATE ACTOR SET FIRST_NAME=? WHERE ACTOR_ID=?";

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, actor.getFirstName());
			pstm.setInt(2, actor.getActorId());

			modifica = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getCause());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getCause());
			}

		}
		return modifica;
	}

	@Override
	public int deleteActor(int id) {
		int canc = 0;
		try {
			conn = DbConn.getInstance().getConnection();

			String sql = "DELETE FROM ACTOR WHERE ACTOR_ID=?";

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);

			canc = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getCause());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getCause());
			}

		}
		return canc;
	}

	@Override
	public int insertActor(List<Actor> listActors) {
		int insert = 0;
		try {
			conn = DbConn.getInstance().getConnection();

			String SQL = "insert into actor(actor_id, first_name, last_name, last_update) " + "values (?, ?, ?, ?)";

			pstm = conn.prepareStatement(SQL);
			for (Actor a : listActors) {

				log.info(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName() + " " + a.getLastUpdate());

				pstm.setInt(1, a.getActorId());
				pstm.setString(2, a.getFirstName());
				pstm.setString(3, a.getLastName());
				pstm.setString(4, a.getLastUpdate());

				insert = pstm.executeUpdate();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getCause());
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getCause());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getCause());
			}

		}
		return insert;

	}

	// Jpa
	// NamedStoredProcedureQuery
	@Override
	public void callSp() {

		StoredProcedureQuery sp = em.createStoredProcedureQuery("name_act", Actor.class)
				.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);

		sp.setParameter(1, 5);
		sp.execute();

		String actNome = (String) sp.getOutputParameterValue(2);
		System.out.println(actNome);
		log.info("funzione eseguita per id 5!");

	}

	// CRITERIA api
	@Override
	public void actorWhere() {
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		AbstractQuery<Actor> cq = cb.createQuery(Actor.class);

		Root<Actor> act = cq.from(Actor.class);

		cq.where(cb.lessThan(act.get("actorId"), 22)); // selezionare gli attori con id <a 22

		CriteriaQuery<Actor> select = ((CriteriaQuery<Actor>) cq).select(act);

		TypedQuery<Actor> tq = em.createQuery(select);

		List<Actor> list = tq.getResultList();

		System.out.println("Attore con id inferiore a 22");

		for (Actor a : list) {
			log.info(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName() + " " + a.getLastUpdate());
		}

		em.getTransaction().commit();

	}

	@Override
	public void orderByDescActor() {

		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery cq = cb.createQuery(Actor.class);

		Root<Actor> act = cq.from(Actor.class);

		cq.orderBy(cb.desc(act.get("actorId"))); // ordino in modo decrescente da id

		CriteriaQuery<Actor> select = cq.select(act);

		TypedQuery<Actor> q = em.createQuery(select);

		List<Actor> list = q.getResultList();

		log.info("Gli attori in ordine decrescente:");
		for (Actor a : list) {

			log.info(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName() + " " + a.getLastUpdate());

		}

		em.getTransaction().commit();

	}
}

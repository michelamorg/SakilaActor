package com.myproject.dao;

import java.util.List;

import com.myproject.model.Actor;

public interface ActorDao {

	public List<Actor> letturaAttori();

	public List<Actor> trovaConId(int id);

	public List<Actor> TrovaConNome(String name);

	public int updateActor(Actor actor);

	public int deleteActor(int id);

	public int insertActor(List<Actor> listActors);

	// Jpa
	public void actorWhere();

	public void orderByDescActor();

	public void callSp();

}

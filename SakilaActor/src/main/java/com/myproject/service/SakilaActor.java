package com.myproject.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.myproject.model.Actor;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface SakilaActor {

	@WebMethod
	List<Actor> findAllActor();

	@WebMethod
	List<Actor> findsActorById(int id);

	@WebMethod
	List<Actor> findsActorByNome(String name);

	@WebMethod
	void modificaNome(Actor actor);

	@WebMethod
	void inserisciAttore(List<Actor> listActors);

	@WebMethod
	void cancAttore(int id);

	@WebMethod
	void queryWhere();

	@WebMethod
	public void orderBy();

	@WebMethod
	void chiamoSp();

}

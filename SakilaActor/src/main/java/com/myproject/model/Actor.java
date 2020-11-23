package com.myproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actor", catalog = "dvdrental", schema = "public")

@NamedStoredProcedureQuery(name = "findNameById", procedureName = "name_act", resultClasses = {
		Actor.class }, parameters = {
				@StoredProcedureParameter(name = "v_id", type = Integer.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "nome_attore", type = String.class, mode = ParameterMode.OUT) })

public class Actor {

	@Id
	@Column(name = "actor_id")
	private int actorId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "last_update")
	private String lastUpdate;

}

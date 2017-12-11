package com.hy.pojos;

public class Invite {
	
	private int id;
	private String nomInvite;
	private Groupe groupe;
	private Table table;
	
	
	public Invite() {
		groupe = new Groupe();
		table = new Table();
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomInvite() {
		return nomInvite;
	}
	public void setNomInvite(String nom) {
		this.nomInvite = nom;
	}

	
}

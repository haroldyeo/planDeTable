package com.hy.utils;

import java.util.List;

import com.hy.pojos.Groupe;
import com.hy.pojos.Invite;
import com.hy.pojos.Table;

public class BusinessLogic {
	
	Dao dao = new Dao();
	
	public void create(String entity, Object obj) throws Exception{
		
		String INSERT_QUERY = null;
		
		switch (entity) {
		case "invite": 
			Invite inv = (Invite)obj;
			INSERT_QUERY = "insert into t_invites (nom, groupe_id, table_num) "
							+ " values ("+inv.getNomInvite()+", "+inv.getGroupe().getId()+", "+inv.getTable().getNumeroTable()+")";
		case "groupe": 
			Groupe grp = (Groupe)obj;
			INSERT_QUERY = "insert into t_groupe (nom_groupe) "
							+ " values ("+grp.getNomGroupe()+")";
		case "table": 
			Table tbl = (Table)obj;
			INSERT_QUERY = "insert into t_table (num_table, nom_table, groupe_id) "
							+ " values ("+tbl.getNumeroTable()+", "+tbl.getNomTable()+", "+tbl.getGroupe().getId()+")";
			
			break;

		default:
			break;
		}
		
		// Appel de la m�thode d'ex�cution
		dao.executeQuery(INSERT_QUERY);
	}
	
	public void update(String entity, Object obj) throws Exception{
		
		String UPDATE_QUERY = null;
		
		switch (entity) {
		case "invite": 
			Invite inv = (Invite)obj;
			UPDATE_QUERY = "update t_invites"
							+ " set nom="+inv.getNomInvite()+", groupe_id="+inv.getGroupe().getId()+", table_num="+inv.getTable().getNumeroTable()
							+ " where id="+inv.getId();
		case "groupe": 
			Groupe grp = (Groupe)obj;
			UPDATE_QUERY = "update t_groupe set nom_groupe="+grp.getNomGroupe()+" where id="+grp.getId();
		case "table": 
			Table tbl = (Table)obj;
			UPDATE_QUERY = "update t_table "
							+ " set nom_table="+tbl.getNomTable()+", groupe_id="+tbl.getGroupe().getId()
							+ "where id="+tbl.getNumeroTable();
			
			break;

		default:
			break;
		}
		
		// Appel de la m�thode d'ex�cution
		dao.executeQuery(UPDATE_QUERY);
	}
	
	public void delete(String nomTable, int id) throws Exception{
		
		final String DELETE_QUERY = "delete from " +nomTable+" where id = "+id;
		// Appel de la m�thode d'ex�cution
		dao.executeQuery(DELETE_QUERY);
	}
	
	
	
	/**
	 * Obtenir la liste de tous les invit�s
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Invite> getAllInvites(){
		
		final String GET_ALL_INVITES = "select inv.id as invite_id, inv.nom as nom_invite," 
									+ " grp.id as groupe_id, grp.nom_groupe," 
									+ " tbl.num_table, tbl.nom_table "
									+ " from t_invites inv"
									+ " left join t_groupe grp on inv.groupe_id = grp.id" 
									+ " left join t_table tbl on inv.table_num = tbl.num_table";
				
		
		return (List<Invite>) dao.executeSelect(GET_ALL_INVITES, "all_invites");
	}
}

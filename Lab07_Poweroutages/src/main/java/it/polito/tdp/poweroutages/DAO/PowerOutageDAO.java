package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {
	
	Map<Integer,Nerc> mappaNerc= new TreeMap<Integer,Nerc>();
	List<PowerOutage> listaPower= new ArrayList<PowerOutage>();
	
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				
				nercList.add(n);
				mappaNerc.put(res.getInt("id"),n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutage> trovaValori(int idNerc){
		
		String sql = "SELECT id,nerc_id,customers_affected,date_event_began,date_event_finished FROM poweroutages WHERE nerc_id=?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, idNerc);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
			
				PowerOutage po=new PowerOutage(res.getInt("id"), mappaNerc.get(res.getInt("nerc_id")), res.getTimestamp("date_event_began").toLocalDateTime(), res.getTimestamp("date_event_finished").toLocalDateTime(), res.getInt("customers_affected"));
				listaPower.add(po);
			}

			conn.close();
			return listaPower;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		
	}
	
	

}

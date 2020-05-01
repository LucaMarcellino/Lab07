  
package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;






public class Model {

	private PowerOutageDAO podao;
	private List<PowerOutage> tutti;
	private List<PowerOutage> soluzione;
	private int best;
	private int anni;
	private int ore;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		List<String> lista = new ArrayList<String>();
		List<Nerc> listaNerc = podao.getNercList();
		
		
		
		return listaNerc;
	}
	
	public String cercaEventi( int anni, int ore,Nerc nercs) {
		String s = "";
		List<PowerOutage> parziale = new ArrayList<PowerOutage>();
		soluzione = null;
		best = 0;
		this.anni = anni;
		this.ore = ore;
		
		
	
		
		
		tutti = podao.trovaValori(nercs.getId()) ;
		
		ricorsione(parziale, 0);
		
		if (soluzione != null) {
			s += "Tot people affected:   "+sommaPeople(soluzione)+"\n";
			s += "Tot hours of outages:   "+sommaOre(soluzione)+"\n";
			
			for (PowerOutage po: soluzione)
				s += po.toString();
		} else 
			s = "Nessuna combinazione trovata";
		
		return s;
	}
	
	private void ricorsione(List<PowerOutage> parziale, int L) {
		
		// ad ogni livello L decido se aggiungere l'evento o no
		
		if (L == tutti.size()) {
			int somma = sommaPeople(parziale);
			if (somma > best) {
				best = somma;
				soluzione = new ArrayList<PowerOutage>(parziale);
			}
			return ;
		}
		
		ricorsione(parziale, L+1);  // non aggiungo
		
		
		parziale.add(tutti.get(L));
		
		if (sommaOre(parziale) <= ore && diffAnni(parziale) <= anni)
			ricorsione(parziale, L+1);    // aggiungo
		
		parziale.remove(parziale.size()-1);
		
	}
	
	public int sommaPeople(List<PowerOutage> soluzione) {
        int somma = 0;
		
		for (PowerOutage p: soluzione)
			somma += p.getNumeroPersone();
		
		return somma;
	}
	
	public long sommaOre(List<PowerOutage> parziale) {
		long somma = 0;
		
		for (PowerOutage p: parziale)
			somma += p.getOreTot();
		
		return somma;
	}
	
	public int diffAnni(List<PowerOutage> parziale) {
		int min = 2020;
		int max = 0;
		
		for (PowerOutage p : parziale)
			if (p.anno() < min)
				min = p.anno();
		
		for (PowerOutage p : parziale)
			if (p.anno() > max)
				max = p.anno();
		
		return max - min;
	}
	
}
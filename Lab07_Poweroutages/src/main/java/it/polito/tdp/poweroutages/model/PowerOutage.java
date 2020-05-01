package it.polito.tdp.poweroutages.model;

import java.util.Date;

import java.sql.Time;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class PowerOutage {

	int id;
	LocalDateTime dataInizio;
	LocalDateTime dataFine;
	long oreTot;
	int numeroPersone;
	int idNerc;
	Nerc nerc;
	
	public PowerOutage() {
		
	}
	
	public PowerOutage(int id,Nerc nerc,LocalDateTime dataInizio, LocalDateTime dataFine, int numeroPersone) {
		
		this.id=id;
		this.nerc=nerc;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.numeroPersone = numeroPersone;
		this.oreTot=ChronoUnit.HOURS.between(dataInizio, dataFine);
	}



	
	
	public int anno() {
		return dataInizio.getYear();
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

	public long getOreTot() {
		
		return oreTot;
	}



	public int getNumeroPersone() {
		return numeroPersone;
	}


	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}

	
	

	@Override
	public String toString() {
		return "PowerOutage [dataInizio=" + dataInizio + ", dataFine=" + dataFine + ", oreTot=" + oreTot
				+ ", numeroPersone=" + numeroPersone + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
}

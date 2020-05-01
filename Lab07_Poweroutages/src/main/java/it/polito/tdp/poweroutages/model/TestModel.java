package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		PowerOutage po= new PowerOutage();
		//System.out.println(model.getNercList());
		System.out.print(model.cercaEventi(4, 200, new Nerc(3,"MAAC")));
		
	}

}

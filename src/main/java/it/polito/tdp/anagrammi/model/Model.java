package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.anagrammi.DAO.AnagrammaDAO;

public class Model {
	
	List<String> anagrammi;
	Map<String, Boolean> anagrammiFinal;
	AnagrammaDAO anagrammaDAO;
	
	public Map<String, Boolean> calcolaAnagrammi(String parola) {
		
		anagrammi = new ArrayList<String>();
		anagrammiFinal = new HashMap<String, Boolean>();
		anagrammaDAO = new AnagrammaDAO();
		
		String parziale = "";
		String rimanenti = parola; //ex: eat
		
		anagrammiRicorsiva(parziale,0,rimanenti);
		
		for(String s : anagrammi) {
			if(anagrammaDAO.isCorrect(s))
				anagrammiFinal.put(s, true);
			else
				anagrammiFinal.put(s, false);
		}
		
		return anagrammiFinal;
	}
	
	/**
	 * 
	 * @param parziale (stringa finora costruita)
	 * @param livello (rappresenta il numero di caratteri della soluzione parziale attuale
	 * @param rimanenti (stringa con le lettere ancora da aggiungere)
	 */
	private void anagrammiRicorsiva(String parziale, int livello, String rimanenti) {
		//casi terminali
		if(rimanenti.length()==0) { //abbiamo costruito un anagramma
			anagrammi.add(parziale);
			return;
		}
		
		for(int i=0;i<rimanenti.length();i++) {
			String nuovaParziale = parziale+rimanenti.charAt(i);
			String nuovaRimanenti = rimanenti.substring(0,i)+rimanenti.substring(i+1);
			anagrammiRicorsiva(nuovaParziale, livello+1, nuovaRimanenti);
		}
		
	}
	

}

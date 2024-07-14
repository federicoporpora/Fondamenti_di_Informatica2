package cruciverba.model;

import java.util.Arrays;
import java.util.stream.*;

public class Cruciverba {

	private char[][] griglia;
	private int numRighe, numColonne;
	public static char BIANCA = ' ', NERA = '■';
	
	public Cruciverba(int numRighe, int numColonne) {
		griglia = new char[numRighe][numColonne];
		Arrays.stream(griglia).forEach(arr -> Arrays.fill(arr,NERA));
		this.numRighe = numRighe;
		this.numColonne=numColonne;
	}	
	
	public char getCella(int indiceRiga, int indiceColonna) {
		validaIndiceRiga(indiceRiga); validaIndiceColonna(indiceColonna);
		return griglia[indiceRiga][indiceColonna];
	}
	
	public void setCella(int indiceRiga, int indiceColonna, char c) {
		validaIndiceRiga(indiceRiga); validaIndiceColonna(indiceColonna);
		griglia[indiceRiga][indiceColonna] = c;
	}
	
	public void setParola(int indiceRiga, int indiceColonna, String s, Orientamento or) {
		validaIndiceRiga(indiceRiga); validaIndiceColonna(indiceColonna);
		switch(or) {
			case ORIZZONTALE -> validaIndiceColonna(indiceColonna+s.length()-1);
			case VERTICALE   -> validaIndiceRiga(indiceRiga+s.length()-1);
		}
		for(int i=0; i<s.length(); i++) 
			switch(or) {
			case ORIZZONTALE -> griglia[indiceRiga][indiceColonna+i] = s.charAt(i);
			case VERTICALE   -> griglia[indiceRiga+i][indiceColonna] = s.charAt(i);
		}
	}
	
	private void validaIndiceRiga(int indiceRiga) {
		if (indiceRiga<0 || indiceRiga>=numRighe) throw new IllegalArgumentException("Riga fuori range: " + indiceRiga);
	}
	
	private void validaIndiceColonna(int indiceColonna) {
		if (indiceColonna<0 || indiceColonna>=numColonne) throw new IllegalArgumentException("Colonna fuori range: " + indiceColonna);
	}

	public int getNumRighe() {
		return numRighe;
	}

	public int getNumColonne() {
		return numColonne;
	}
	
	public char[] getRiga(int indiceRiga) {
		validaIndiceRiga(indiceRiga);
		return griglia[indiceRiga];
	}

	public char[] getColonna(int indiceColonna) {
		validaIndiceColonna(indiceColonna);
		var colonna = new char[numRighe];
		for (int i=0; i<numRighe; i++)
			colonna[i] = griglia[i][indiceColonna];
		return colonna;
	}
	
	public String[] paroleInRiga(int indiceRiga) {
		var riga = getRiga(indiceRiga);
		String[] paroleAncheVuote = new String(riga).split(String.valueOf(NERA));
		String[] parole = Stream.of(paroleAncheVuote).filter(p->p.length()>0).toArray(String[]::new);
		return parole;
	}
	
	public String[] paroleInColonna(int indiceColonna) {
		var colonna = getColonna(indiceColonna);
		String[] paroleAncheVuote = new String(colonna).split(String.valueOf(NERA));
		String[] parole = Stream.of(paroleAncheVuote).filter(p->p.length()>0).toArray(String[]::new);
		return parole;
	}
		
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int r=0; r<numRighe; r++) { 
			for(int c=0; c<numColonne; c++) {
				var ch = this.getCella(r,c);
				sb.append(ch);
				sb.append(Cruciverba.BIANCA);
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	
}


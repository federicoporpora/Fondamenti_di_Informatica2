package cruciverba.model;

import java.util.*;


public class Numeratore {
	
	private Cruciverba schema;
	private int numRighe, numColonne;
	private OptionalInt[][] griglia;
	
	public Numeratore(Cruciverba schema) {
		this.schema=schema;
		this.numRighe = schema.getNumRighe();
		this.numColonne = schema.getNumColonne();
		this.griglia = new OptionalInt[numRighe][numColonne];
		numeraCelle();
	}
	
	
	private void numeraCelle() {
		int proxNum = 1;
		for (int i = 0; i < numRighe; i++) {
			for (int j = 0; j < numColonne; j++) {
				if (schema.getCella(i, j) == '■') { //se la cella è nera non la controllo
					griglia[i][j] = OptionalInt.empty();
					continue;
				}
				try { //primo caso da numerare
					validaIndiceRiga(i + 1);
					if (i == 0 && schema.getCella(i + 1, j) != '■') {
						griglia[i][j] = OptionalInt.of(proxNum++);
						continue;
					}
				} catch (IllegalArgumentException e) {}
				try { //secondo caso da numerare
					validaIndiceRiga(i + 1);
					validaIndiceRiga(i - 1);
					if (i != 0 && schema.getCella(i + 1, j) != '■' && schema.getCella(i - 1, j) == '■') {
						griglia[i][j] = OptionalInt.of(proxNum++);
						continue;
					}
				} catch (IllegalArgumentException e) {}
				try { //terzo caso da numerare
					validaIndiceColonna(j + 1);
					if (j == 0 && schema.getCella(i, j + 1) != '■') {
						griglia[i][j] = OptionalInt.of(proxNum++);
						continue;
					}
				} catch (IllegalArgumentException e) {}
				try { //quarto caso da numerare
					validaIndiceColonna(j + 1);
					validaIndiceColonna(j - 1);
					if (j != 0 && schema.getCella(i, j + 1) != '■' && schema.getCella(i, j - 1) == '■') {
						griglia[i][j] = OptionalInt.of(proxNum++);
						continue;
					}
				} catch (IllegalArgumentException e) {}
				griglia[i][j] = OptionalInt.empty(); //se non è da numerare metto empty
			}
		}
	}
	

	public Cruciverba getSchema() {
		return schema;
	}
	
	public int getNumRighe() {
		return numRighe;
	}

	public int getNumColonne() {
		return numColonne;
	}
	
	private void validaIndiceRiga(int indiceRiga) {
		if (indiceRiga<0 || indiceRiga>=numRighe) throw new IllegalArgumentException("Riga fuori range: " + indiceRiga);
	}
	
	private void validaIndiceColonna(int indiceColonna) {
		if (indiceColonna<0 || indiceColonna>=numColonne) throw new IllegalArgumentException("Colonna fuori range: " + indiceColonna);
	}
	
	public OptionalInt getNumeroCella(int indiceRiga, int indiceColonna) {
		validaIndiceRiga(indiceRiga); validaIndiceColonna(indiceColonna);
		return griglia[indiceRiga][indiceColonna];
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int r=0; r<numRighe; r++) { 
			sb.append("|");
			for(int c=0; c<numColonne; c++) {
				var num = griglia[r][c];
				sb.append(num.isEmpty() ? (schema.getCella(r,c)==Cruciverba.NERA ? String.format("%2s", Cruciverba.NERA) : String.format("%2s", Cruciverba.BIANCA)) : 
							String.format("%02d", num.getAsInt()) );
				sb.append("|"); // se non si vuole il separatore verticale: sb.append(Cruciverba.BLANK);
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	
	public OptionalInt[] getRiga(int indiceRiga) {
		validaIndiceRiga(indiceRiga);
		return griglia[indiceRiga];
	}

	public OptionalInt[] getColonna(int indiceColonna) {
		validaIndiceColonna(indiceColonna);
		var colonna = new OptionalInt[numRighe];
		for (int i=0; i<numRighe; i++)
			colonna[i] = griglia[i][indiceColonna];
		return colonna;
	}
	
	public SortedMap<Integer,String> orizzontali(){
		return mappaParole(Orientamento.ORIZZONTALE);
	}
	
	public SortedMap<Integer,String> verticali(){
		return mappaParole(Orientamento.VERTICALE);
	}
	
	private SortedMap<Integer, String> mappaParole(Orientamento orientamento) {
		var mappa = new TreeMap<Integer, String>();
		if (orientamento.equals(Orientamento.ORIZZONTALE)) {
			for (int i = 0; i < schema.getNumRighe(); i++) {
				int[] numeriUtili = numeriUtili(getRiga(i), schema.getRiga(i));
				var parole = new ArrayList<String>();
				var temp = schema.paroleInRiga(i);
				for (String parola : temp) parole.add(parola);
				for (int j = 0; j < parole.size(); j++) if (parole.get(j).length() < 2) parole.remove(j);
				for (int j = 0; j < parole.size(); j++) mappa.put(numeriUtili[j], parole.get(j));
			}
		} else {
			for (int i = 0; i < schema.getNumColonne(); i++) {
				int[] numeriUtili = numeriUtili(getColonna(i), schema.getColonna(i));
				var parole = new ArrayList<String>();
				var temp = schema.paroleInColonna(i);
				for (String parola : temp) parole.add(parola);
				for (int j = 0; j < parole.size(); j++) if (parole.get(j).length() < 2) parole.remove(j);
				for (int j = 0; j < parole.size(); j++) mappa.put(numeriUtili[j], parole.get(j));
			}
		}
		return mappa;
	}
	
	private int[] numeriUtili(OptionalInt[] rigaNumeri, char[] rigaCaratteri) {
		var numeri = new ArrayList<Integer>();
		if(rigaNumeri[0].isPresent() && rigaCaratteri[1]!=Cruciverba.NERA ) numeri.add(rigaNumeri[0].getAsInt());
		for(int i=1; i<rigaCaratteri.length-1; i++) {
			if(rigaNumeri[i].isPresent() && rigaCaratteri[i-1]==Cruciverba.NERA && rigaCaratteri[i+1]!=Cruciverba.NERA) numeri.add(rigaNumeri[i].getAsInt());
		}
		return numeri.stream().mapToInt(Integer::intValue).toArray();
	}
	
}

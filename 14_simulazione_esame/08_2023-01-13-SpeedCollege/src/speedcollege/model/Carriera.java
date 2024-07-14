package speedcollege.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Carriera {	
	private List<Esame> listaEsami;
	private Comparator<Esame> cmp;
	
	public Carriera() {
		this.listaEsami=new ArrayList<>();
		this.cmp = Comparator.comparing(Esame::getDate).thenComparing((Esame e) -> e.getAf().getId());
	}
	
	public List<Esame> getListaEsami() {
		return listaEsami;
	}

	public List<Esame> getTentativiPrecedenti(Esame esame){
		return listaEsami.stream()
				.filter(e-> e.getAf().getId()==esame.getAf().getId())
				.sorted(cmp)
				.collect(Collectors.toList());
	}
		
	public Optional<Esame> getUltimoTentativo(Esame esame){
		List<Esame> tentativiPrecedenti = getTentativiPrecedenti(esame);
		return tentativiPrecedenti.isEmpty() ? Optional.empty() :
					Optional.of(tentativiPrecedenti.get(tentativiPrecedenti.size()-1));
	}

	public Optional<Esame> getUltimoEsameDato(){
		return listaEsami.stream().collect(Collectors.maxBy(cmp));
	}

	
	public void inserisci(Esame esame) {
		if (esame==null) throw new IllegalArgumentException("Impossibile inserire in carriera un esame nullo");
		
		// check eventuali tentativi precedenti per lo stesso esame
		Optional<Esame> ultimoTentativo = getUltimoTentativo(esame);
		
 		if (ultimoTentativo.isPresent() && (
 				ultimoTentativo.get().getVotoIniziale().superato() 
				|| esame.getDate().isBefore(ultimoTentativo.get().getDate())
				|| esame.getDate().equals(ultimoTentativo.get().getDate()) ))
					throw new IllegalArgumentException("Esame già sostenuto " + ultimoTentativo.get());
		
 		/* NB: per massima eleganza, questo if è interamente sostituibile e ristrutturabile usando
 		 * ifPresent + una funzione consumer accessoria, così:
 		 * 
 		 * ultimoTentativo.ifPresent(checkDate(esame));
 		 * 
 		 * e, a parte:
 		 * 
 		 * 	private Consumer<Esame> checkDate(Esame esame) {
				return ultimoTentativo -> {
					if (ultimoTentativo.getVoto().superato() 
					|| esame.getDate().isBefore(ultimoTentativo.getDate())
					|| esame.getDate().equals(ultimoTentativo.getDate()) )
						throw new IllegalArgumentException("Esame già sostenuto " + ultimoTentativo);
				};
			}
 		 */
 		  		
		// se l'esame è la prova finale, dev'essere ultimo in ordine cronologico
 		// NB: non verifica che sano stati acquisiti tutti i cfu previsti perché il valore da raggiungere
 		// differisce per LT, LM, LMCU
		if(esame.getAf().getNome().toUpperCase().contains("PROVA FINALE")) {
			Optional<Esame> ultimoEsameDato  = getUltimoEsameDato();
			if (ultimoEsameDato.isEmpty() || 
				ultimoEsameDato.isPresent() && ultimoEsameDato.get().getDate().isAfter(esame.getDate()))
					throw new IllegalArgumentException("Impossibile sostenere la prova finale prima di altri esami " + esame);
		}
		
		// se tutto ok, inserimento esame in carriera
		listaEsami.add(esame);
		listaEsami.sort(cmp);
	}
	
	public double creditiAcquisiti() {
		return listaEsami.stream()
				.filter(e -> e.getVotoIniziale().superato())
				.map(Esame::getAf)
				.mapToDouble(AttivitaFormativa::getCfu)
				.sum();
	}
	
	@Override
	public String toString() {
		return listaEsami.stream().map(Esame::toString).collect(Collectors.joining(System.lineSeparator()));
	}
	
}

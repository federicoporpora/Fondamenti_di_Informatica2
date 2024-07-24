package pacchi.model;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;


public class StatoPartita {

	private Map<Pacco,Pacco.Stato> statoPacchi;
	
	private Pacco paccoConcorrente;
	
	public StatoPartita(Set<Pacco> pacchi, Pacco paccoConcorrente) {
		validazioneInizialeListaPacchi(pacchi);
		verificaAssenzaInLista(pacchi, paccoConcorrente, "Il pacco concorrente non può essere fra quelli da aprire");		
		statoPacchi = pacchi.stream().collect(Collectors.toMap(p -> p, p -> Pacco.Stato.CHIUSO));
		this.paccoConcorrente = paccoConcorrente;
	}
	
	private void validazioneInizialeListaPacchi(Set<Pacco> pacchi) {
		var mappaPerTerritori = pacchi.stream().collect(Collectors.groupingBy(Pacco::territorio));
		mappaPerTerritori.values().forEach(l -> {if(l.size()>1) throw new IllegalArgumentException("territori duplicati");});
		var mappaPerNumeri = pacchi.stream().collect(Collectors.groupingBy(Pacco::numero));
		mappaPerNumeri.values().forEach(l -> {if(l.size()>1) throw new IllegalArgumentException("numeri duplicati");});
		var mappaPerValori = pacchi.stream().collect(Collectors.groupingBy(Pacco::premio));
		mappaPerValori.values().forEach(l -> {if(l.size()>1) throw new IllegalArgumentException("premi duplicati");});
	}
	
	private void verificaAssenzaInLista(Set<Pacco> pacchi, Pacco paccoConcorrente, String messaggio) {
		if (pacchi.contains(paccoConcorrente)  ||
			pacchi.stream().map(Pacco::territorio).filter(t -> t.equals(paccoConcorrente.territorio())).count()>0 ||
			pacchi.stream().map(Pacco::numero).filter(t -> t.equals(paccoConcorrente.numero())).count()>0 ||
			pacchi.stream().map(Pacco::premio).filter(t -> t.equals(paccoConcorrente.premio())).count()>0 
		)
			throw new IllegalArgumentException(messaggio);
	}

	@Override
	public String toString() {
		return "Partita [pacchiDaAprire=" + statoPacchi.keySet() + ", paccoConcorrente=" + paccoConcorrente + "]";
	}
	
	public Set<Pacco> getPacchi() { // ONLY FOR TESTING
		var tuttiPacchiInGioco = new HashSet<>(statoPacchi.keySet());
		tuttiPacchiInGioco.add(this.getPaccoConcorrente());
		return tuttiPacchiInGioco;
	}
	
	
	public Set<Pacco> getPacchiDaAprire() {
		return statoPacchi.keySet().stream().filter(p -> statoPacchi.get(p)==Pacco.Stato.CHIUSO).collect(Collectors.toSet());
	}
	
	public int quantiPacchiDaAprire() {
		return this.getPacchiDaAprire().size();
	}
	
	public Pacco getPaccoConcorrente() {
		return paccoConcorrente;
	}

	public Set<Pacco> getPacchiAperti() {
		return statoPacchi.keySet().stream().filter(p -> statoPacchi.get(p)==Pacco.Stato.APERTO).collect(Collectors.toSet());
	}

	public Valore apriPacco(Pacco pacco) {
		if (pacco==null) throw new IllegalArgumentException("Pacco nullo");
		if (!statoPacchi.keySet().contains(pacco)) throw new IllegalArgumentException("Pacco inesistente: " + pacco);
		if (statoPacchi.get(pacco)==Pacco.Stato.APERTO) throw new IllegalArgumentException("Pacco già aperto: " + pacco);
		statoPacchi.put(pacco, Pacco.Stato.APERTO);
		return pacco.premio();
	}
			
}

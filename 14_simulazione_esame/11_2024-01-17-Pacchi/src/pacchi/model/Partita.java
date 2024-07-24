package pacchi.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class Partita {

	private Set<Territorio> territori;
	private Set<Valore> premi;
	private Set<Pacco> pacchi;
	private StatoPartita statoPartita;
	private Dottore dottore;
	
	public Partita(Set<Territorio> territori, Set<Valore> premi) {
		if (territori==null) throw new IllegalArgumentException("Lista territori nulla");
		if (premi==null) throw new IllegalArgumentException("Lista premi nulla");
		if (territori.size()<=0) throw new IllegalArgumentException("Lista territori di lunghezza inammissibile");
		if (premi.size()<=0) throw new IllegalArgumentException("Liste premi di lunghezza inammissibile");
		if (territori.size()!=premi.size()) throw new IllegalArgumentException("Liste territori e valori di lunghezza diversa");
		//
		this.territori = territori;
		this.premi = new HashSet<>(premi);
		this.pacchi = generaPacchi(territori, premi);
		//
		int indexPaccoConcorrente = (int) Math.floor(Math.random()*pacchi.size());
		var paccIterator = pacchi.iterator();
		for(int k=0; k<indexPaccoConcorrente;k++) paccIterator.next(); // ne salta un certo numero
		Pacco paccoConcorrente = paccIterator.next();
		paccIterator.remove();
		
		this.statoPartita = new StatoPartita(pacchi, paccoConcorrente);
		this.dottore = new Dottore(this.statoPartita);
	}

	public Set<Pacco> generaPacchi(Set<Territorio> territori, Set<Valore> premi) {
        // ***
        // DA FARE
        // NB: questo metodo dovrebbe essere privato: viene reso pubblico solo per motivi di test
        //     Per lo stesso motivo non effettua validaazione degli argomenti, in quanto già validati dal costruttore
        // Specifica: il metodo itera sugli insiemi ricevuti accoppiando un territorio, un premio e un numero di pacco
        // sorteggiato fra 1 e N, senza ripetizioni e senza escluderne alcuno
        int lunghezza = territori.size();
        Random random = new Random();
        Set<Integer> numeriUsati=new HashSet<>();
        Set<Pacco> result=new HashSet<>();
        List<Valore> premiList=new ArrayList<>(premi);
        for(Territorio t : territori) {
            Valore premio=premiList.get(random.nextInt(premiList.size()));
            premiList.remove(premio);
            int numero;
            do {
                numero=random.nextInt(lunghezza)+1;
            }while(numeriUsati.contains(numero));

            numeriUsati.add(numero);
            Numero number=new Numero(numero);
            result.add(new Pacco(t,number,premio));
        }
        return result;
    }

	public Set<Territorio> getTerritori() {
		return territori;
	}

	public Set<Valore> getPremi() {
		return premi;
	}

	public Set<Pacco> getPacchi() {
		return pacchi;
	}

	public StatoPartita getStatoPartita() {
		return statoPartita;
	}

	public Risposta interpellaDottore() {
		return dottore.interpella();
	}
	
	public double media() {
		return dottore.media();
	}
	
	public Valore apriPacco(Numero numeroPacco) {
		if (numeroPacco.valore() > pacchi.size() + 1) throw new IllegalArgumentException("pacco da aprire out of range, " + numeroPacco.valore() + " più grande di " + pacchi.size());
		for (Pacco pacco : pacchi) {
			if (pacco.numero().equals(numeroPacco)) {
				return statoPartita.apriPacco(pacco);
			}
		}
		return null;
	}
	
	public Pacco getPacco(Numero numeroPacco) {
		if(numeroPacco.valore()<0 || numeroPacco.valore()>this.pacchi.size()+1) throw new IllegalArgumentException("Impossibile aprire pacco n." + numeroPacco + ", pacco inesistente");
		return this.pacchi.stream().filter(p -> p.numero().equals(numeroPacco)).toList().get(0);
	}

	@Override
	public String toString() {
		return "Partita [listaRegioni=" + territori + ", listaValori=" + premi + ", listaPacchi=" + pacchi
				+ ", statoPartita=" + statoPartita + ", dottore=" + dottore + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(premi, territori);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Partita other = (Partita) obj;
		return Objects.equals(premi, other.premi) && Objects.equals(territori, other.territori);
	}
			
}

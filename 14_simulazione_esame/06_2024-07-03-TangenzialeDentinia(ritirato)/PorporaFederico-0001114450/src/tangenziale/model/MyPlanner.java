package tangenziale.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

public class MyPlanner implements Planner {

	private Map<String, Autostrada> rete;

	public MyPlanner(Map<String,Autostrada> rete) {
		if (rete == null) throw new NullPointerException("In classe MyPlanner, nel costruttore è stata passata una mappa null");
		if (rete.isEmpty()) throw new IllegalArgumentException("In classe MyPlanner, nel costruttore è stata passata una mappa vuota");
		this.rete = rete;
		for (Autostrada autostrada : rete.values()) if (autostrada.tipologia().equals(Tipologia.TANGENZIALE)) return;
		throw new IllegalArgumentException("In classe MyPlanner, nel costruttore è stata passata una mappa senza tratte in tangenziale");
		// il costruttore riceve la mappa della rete autostradale, che non può essere né nulla, né vuota 
		// (altrimenti, viene lanciata rispettivamente NullPointerException o IllegalArgumentException) 
		// e deve necessariamente contenere almeno la tangenziale (altrimenti, IllegalArgumentException) 
	}

	@Override
	public Percorso trovaPercorso(String da, String a) {
		if (da == null || a == null) throw new NullPointerException("In classe MyPlanner, in trovaPercorso(), una o entrambe le stringhe sono null");
		if (rete.values().stream().filter(autostrada -> autostrada.profilo().containsValue(da) || autostrada.profilo().containsValue(a)).count() == 0)
			throw new IllegalArgumentException("In classe MyPlanner, in trovaPercorso(), la rete autostradale non contiene uno o entrambi i caselli");
		var result = new ArrayList<Tratta>();
		Entry<String, Autostrada> autostradaDa = null, autostradaA = null;
		for (Entry<String, Autostrada> coppia : rete.entrySet()) {
			if (coppia.getValue().profilo().containsValue(da)) autostradaDa = coppia;
			if (coppia.getValue().profilo().containsValue(a)) autostradaA = coppia;
		}
		if (autostradaDa.equals(autostradaA))
			return new Percorso(List.of(new Tratta(da, a, autostradaDa.getValue())));
		if (autostradaDa.getValue().tipologia().equals(Tipologia.TANGENZIALE) || autostradaA.getValue().tipologia().equals(Tipologia.TANGENZIALE))
			return new Percorso(List.of(
					new Tratta(da, autostradaA.getKey(), autostradaDa.getValue()),
					new Tratta(autostradaDa.getKey(), a, autostradaA.getValue())));
		return new Percorso(List.of(
				new Tratta(da, Autostrada.TANGENZIALE, autostradaDa.getValue()),
				new Tratta(autostradaDa.getKey(), autostradaA.getKey(), rete.get(Autostrada.TANGENZIALE)),
				new Tratta(Autostrada.TANGENZIALE, a, autostradaA.getValue())));
		
		//  Si ricorda che: 
		//  - se i caselli di entrata e di uscita sono sulla stessa autostrada (o tangenziale), il percorso 
		//    è diretto (cioè, è composto da un’unica tratta); 
		//  - se, invece, i caselli di entrata e di uscita sono su due autostrade diverse, il percorso è indiretto 
		//    e comprende tre tratte, di cui senz’altro anche un tratto intermedio in Tangenziale; 
		//  - se, infine, i caselli di entrata e di uscita sono uno su un’autostrada e l’altro sulla Tangenziale 
		//    (o viceversa), il percorso è indiretto e composto di due tratte, una delle quali è senz’altro in Tangenziale
		//
	}

}


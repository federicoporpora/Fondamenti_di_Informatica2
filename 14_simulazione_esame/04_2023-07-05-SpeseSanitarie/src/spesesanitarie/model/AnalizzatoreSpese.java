package spesesanitarie.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AnalizzatoreSpese {

	private List<DocumentoDiSpesa> listaSpese;

	public AnalizzatoreSpese(List<DocumentoDiSpesa> listaSpese) {
		if (listaSpese==null || listaSpese.isEmpty()) throw new IllegalArgumentException("la lista spese non può essere nulla né vuota");
		this.listaSpese = listaSpese;
	}

	public List<DocumentoDiSpesa> getListaSpese() {
		return listaSpese;
	}

	@Override
	public String toString() {
		return "AnalizzatoreSpese [listaSpese=" + listaSpese + "]";
	}
	
	public double somma(Tipologia t){
		double somma = 0.0;
		for (DocumentoDiSpesa documentoDiSpesa : listaSpese) {
			if (documentoDiSpesa.contieneVoce(t)) {
				List<VoceDiSpesa> voci = documentoDiSpesa.getVoci();
				for (VoceDiSpesa voce : voci) if (voce.getTipologia().equals(t)) somma += voce.getImporto();
			}
		}
		return somma;
	}
	
	public List<DocumentoDiSpesa> filtraPer(Tipologia t){
		List<DocumentoDiSpesa> copia = new ArrayList<DocumentoDiSpesa>();
		for (DocumentoDiSpesa documentoDiSpesa : listaSpese) {
			if (documentoDiSpesa.contieneVoce(t)) copia.add(documentoDiSpesa);
		}
		return copia; // FAKE; da implementare
	}

}

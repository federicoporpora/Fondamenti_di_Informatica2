package spesesanitarie.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentoDiSpesa {
	private LocalDate data;
	private String emittente;
	private double importo;
	private List<VoceDiSpesa> items;
	
	public DocumentoDiSpesa(LocalDate data, String emittente, double importo, List<VoceDiSpesa> items) {
		verificaPrecondizioni(data, emittente, importo, items);
		verificaCongruenzaVoci(importo, items);
		this.data = data;
		this.emittente = emittente;
		this.importo = importo;
		this.items = items;
	}

	private void verificaPrecondizioni(LocalDate data, String emittente, double importo, List<VoceDiSpesa> items) {
		if (data == null) throw new IllegalArgumentException("la data è null");
		if (emittente == null) throw new IllegalArgumentException("l'emittente è null");
		if (importo < 0.0) throw new IllegalArgumentException("l'importo è negativo");
		if (items == null) throw new IllegalArgumentException("la lista è null");
		if (items.isEmpty()) throw new IllegalArgumentException("la lista è vuota");
	}

	private void verificaCongruenzaVoci(double importo, List<VoceDiSpesa> items) {
		double sommaItems = 0.0;
		for (VoceDiSpesa item : items) {
			sommaItems += item.getImporto();
		}
		if (importo != sommaItems) throw new IllegalArgumentException("la somma delle spese di items è diversa dall'importo dello scontrino");
	}

	public LocalDate getData() {
		return data;
	}

	public String getEmittente() {
		return emittente;
	}

	public double getImporto() {
		return importo;
	}

	public List<VoceDiSpesa> getVoci() {
		return items;
	}

	public boolean contieneVoce(Tipologia t) {
		return items.stream().anyMatch(voce -> voce.getTipologia()==t);
	}
	
	@Override
	public String toString() {
		var sb = new StringBuilder();
		sb.append("Data: " + data.format(Formatters.itDateFormatter) + "\n");
		sb.append("Emittente: " + emittente + "\n");
		sb.append("Importo: " + Formatters.itPriceFormatter.format(importo));
		for (VoceDiSpesa item : items) {
			sb.append("\n" + item.toString());
		}
		return sb.toString();
	}
	
	
}

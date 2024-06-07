package dentburger.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Ordine {

	private List<Prodotto> prodotti;
	private LocalDateTime date;

	public Ordine(List<Prodotto> prodotti, LocalDateTime date) {
		if (prodotti==null) throw new IllegalArgumentException("lista prodotti dell'ordine nulla");
		if (date==null) throw new IllegalArgumentException("data/ora dell'ordine nulla");
		this.prodotti = prodotti.isEmpty() ? new ArrayList<>() : prodotti;
		this.date = date;
	}
	
	public void aggiungi(Prodotto p) {
		if (p==null) throw new IllegalArgumentException("prodotto nullo in aggiunta");
		prodotti.add(p);
	}
	
	public boolean rimuovi(Prodotto p) {
		if (p==null) throw new IllegalArgumentException("prodotto nullo in rimozione");
		return prodotti.remove(p);
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public double getPrezzo() {
		return prodotti.stream().mapToDouble(Prodotto::getPrezzo).sum();
	}

	public LocalDateTime getDataOra() {
		return date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, prodotti);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Ordine other = (Ordine) obj;
		return Objects.equals(date, other.date)
				&& Objects.equals(prodotti, other.prodotti);
	}

	@Override
	public String toString() {
		return prodotti.stream().map(Prodotto::toString).collect(Collectors.joining(System.lineSeparator()))
				+ System.lineSeparator()
				+ Formatters.datetimeFormatter.format(date) + "\t"
				+ Formatters.priceFormatter.format(
						prodotti.stream().mapToDouble(Prodotto::getPrezzo).sum()
						);
	}
			
}

package dentburger.model;

import java.util.Objects;


public class Prodotto {

	private Categoria categoria;
	private String genere, specifica;
	private double prezzo;

	public Prodotto(Categoria categoria, String genere, String specifica, double prezzo) {
		if(categoria==null || genere==null || specifica==null) throw new IllegalArgumentException("Argomento nullo in costruttore Prodotto");
		if(genere.isBlank() || specifica.isBlank()) throw new IllegalArgumentException("Denominazione o speficica vuote in costruttore Prodotto");
		if(!Double.isFinite(prezzo) || prezzo<0) throw new IllegalArgumentException("Prezzo illegale o negativo in costruttore Prodotto");
		this.categoria=categoria;
		this.genere=genere;
		this.specifica=specifica;
		this.prezzo=prezzo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getGenere() {
		return genere;
	}

	public String getSpecifica() {
		return specifica;
	}

	public double getPrezzo() {
		return prezzo;
	}

	@Override
	public int hashCode() {
		// due prodotti sono uguali se sono identici a meno del prezzo
		// poiché on possono esservi due oggetti identici ma di prezzo diverso
		return Objects.hash(categoria, genere, specifica);
	}

	@Override
	public boolean equals(Object obj) {
		// due prodotti sono uguali se sono identici a meno del prezzo
		// poiché on possono esservi due oggetti identici ma di prezzo diverso
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Prodotto other = (Prodotto) obj;
		return categoria == other.categoria && Objects.equals(genere, other.genere)
				&& Objects.equals(specifica, other.specifica);
	}

	@Override
	public String toString() {
		return genere + " " + specifica + " " + Formatters.priceFormatter.format(prezzo);
	}

}

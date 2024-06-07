package spesesanitarie.model;

import java.util.Objects;

public class VoceDiSpesa {
	private Tipologia tipologia;
	private double importo;
	
	public VoceDiSpesa(Tipologia tipologia, double importo) {
		verificaPrecondizioni(tipologia, importo);
		this.tipologia = tipologia;
		this.importo = importo;
	}
	
	private void verificaPrecondizioni(Tipologia tipologia, double importo) {
		if (tipologia==null) throw new IllegalArgumentException("la tipologia non può essere nulla");
		if (!Double.isFinite(importo) || importo<0) throw new IllegalArgumentException("l'importo deve'essere un numero non negativo");
	}

	public Tipologia getTipologia() {
		return tipologia;
	}

	public double getImporto() {
		return importo;
	}

	@Override
	public String toString() {
		return tipologia.getDescrizione() + "\t" + Formatters.itPriceFormatter.format(importo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(importo, tipologia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		VoceDiSpesa other = (VoceDiSpesa) obj;
		return Double.doubleToLongBits(importo) == Double.doubleToLongBits(other.importo) && tipologia == other.tipologia;
	}
		
}

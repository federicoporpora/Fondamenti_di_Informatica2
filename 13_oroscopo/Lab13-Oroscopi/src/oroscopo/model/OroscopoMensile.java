package oroscopo.model;

import java.util.Objects;

public class OroscopoMensile implements Oroscopo, Comparable<Oroscopo> {

	private Previsione amore, lavoro, salute;
	private SegnoZodiacale segnoZodiacale;
	
	private static SegnoZodiacale getSegnoZodiacaleFrom(String nomeSegnoZodiacale) {
		return SegnoZodiacale.valueOf(nomeSegnoZodiacale);
	}
	
	public OroscopoMensile(String string, Previsione previsione, Previsione previsione2, Previsione previsione3) {
		if (string == null || previsione == null || previsione2 == null || previsione3 == null || getSegnoZodiacaleFrom(string) == null) throw new NullPointerException("uno degli argomenti è null");
		if (string.isEmpty() || !previsione.validaPerSegno(getSegnoZodiacaleFrom(string))
				|| !previsione2.validaPerSegno(getSegnoZodiacaleFrom(string)) || !previsione3.validaPerSegno(getSegnoZodiacaleFrom(string))
				|| previsione.getValore() > 10 || previsione.getValore() < 0 || previsione2.getValore() > 10
				|| previsione2.getValore() < 0 || previsione3.getValore() > 10 || previsione3.getValore() < 0)
			throw new IllegalArgumentException("qualche argomento è sbagliato");
		this.segnoZodiacale = getSegnoZodiacaleFrom(string);
		this.amore = previsione;
		this.lavoro = previsione2;
		this.salute = previsione3;
	}

	public OroscopoMensile(SegnoZodiacale segnoZodiacale, Previsione previsione, Previsione previsione2, Previsione previsione3) {
		if (segnoZodiacale == null || previsione == null || previsione2 == null || previsione3 == null) throw new NullPointerException("uno degli argomenti è null");
		if (!previsione.validaPerSegno(segnoZodiacale) || !previsione2.validaPerSegno(segnoZodiacale)
				|| !previsione3.validaPerSegno(segnoZodiacale) || previsione.getValore() > 10 || previsione.getValore() < 0
				|| previsione2.getValore() > 10	|| previsione2.getValore() < 0 || previsione3.getValore() > 10
				|| previsione3.getValore() < 0)
			throw new IllegalArgumentException("qualche argomento è sbagliato");
		this.segnoZodiacale = segnoZodiacale;
		this.amore = previsione;
		this.lavoro = previsione2;
		this.salute = previsione3;
	}

	@Override
	public int compareTo(Oroscopo o) {
		if (o == null) throw new NullPointerException("oroscopo nullo");
		if (o.getSegnoZodiacale() != this.getSegnoZodiacale())
			return (this.getSegnoZodiacale().ordinal() > o.getSegnoZodiacale().ordinal()) ? 1 : -1;
		return 0;
	}

	@Override
	public SegnoZodiacale getSegnoZodiacale() {
		return this.segnoZodiacale;
	}

	@Override
	public Previsione getPrevisioneAmore() {
		return this.amore;
	}

	@Override
	public Previsione getPrevisioneSalute() {
		return this.salute;
	}

	@Override
	public Previsione getPrevisioneLavoro() {
		return this.lavoro;
	}

	@Override
	public int getFortuna() {
		double media = ((this.amore.getValore() + this.lavoro.getValore() + this.salute.getValore()) / 3.0) + 0.5;
		return (int) media;
	}
	
	public String toString() {
		return this.amore.getPrevisione() + this.lavoro.getPrevisione() + this.salute.getPrevisione();
	}

	@Override
	public int hashCode() {
		return Objects.hash(amore, lavoro, salute, segnoZodiacale);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OroscopoMensile other = (OroscopoMensile) obj;
		return Objects.equals(amore, other.amore) && Objects.equals(lavoro, other.lavoro)
				&& Objects.equals(salute, other.salute) && segnoZodiacale == other.segnoZodiacale;
	}



}

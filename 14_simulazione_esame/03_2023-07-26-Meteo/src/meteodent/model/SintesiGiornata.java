	package meteodent.model;

public enum SintesiGiornata {

	SERENO(5,"serena"), 
	QUASISERENO(10,"quasi serena"), 
	PIOGGESPARSE(25,"con possibili piogge sparse"), 
	VARIABILE(50,"variabile"), 
	PIOGGEDIFFUSE(65,"con piogge diffuse"), 
	PIOGGIA(80,"con piogge"), 
	PIOGGEINSISTENTI(100,"con piogge insistenti e generalizzate"); 
	
	private int probabilitaPioggia;
	private String testo;
	
	private SintesiGiornata(int probabilitaPioggia, String testo) {
		this.probabilitaPioggia = probabilitaPioggia;
		this.testo = testo;
	}
	
	public int getValue() {
		return probabilitaPioggia;
	}
	
	public String getTesto() {
		return testo;
	}
	
	public static String getTestoAnnuncio(int probabilitaPioggia) {
		SintesiGiornata t = SintesiGiornata.values()[0];
		for (SintesiGiornata tp : SintesiGiornata.values()) 
			if (probabilitaPioggia<=tp.getValue()) {
				t=tp;
				break;
			}
		return "Giornata " + t.getTesto() + ", con probabilità di pioggia del " + probabilitaPioggia + "%";
	}
	
}

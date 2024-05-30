package oroscopo.controller;


import java.io.IOException;

import oroscopo.model.Oroscopo;
import oroscopo.model.OroscopoMensile;
import oroscopo.model.SegnoZodiacale;
import oroscopo.persistence.OroscopoRepository;

public class MyController extends AbstractController {

	private StrategiaSelezione strategiaSelezione;

	public MyController(OroscopoRepository myReader, StrategiaSelezione strategiaSelezione)throws IOException {
		super(myReader);
		this.strategiaSelezione = strategiaSelezione;
	}

	@Override
	public SegnoZodiacale[] getSegni() {
		return SegnoZodiacale.values();
	}

	@Override
	public Oroscopo[] generaOroscopoAnnuale(SegnoZodiacale segno, int fortunaMin) {
		var oroscopoAnnuale = new Oroscopo[12];
		int fortuna;
		do {
			fortuna = 0;
			for (int i = 0; i < 12; i++) {
				oroscopoAnnuale[i] = generaOroscopoCasuale(segno);
				fortuna += oroscopoAnnuale[i].getFortuna();
			}
			fortuna /= 12;
		} while (fortuna < fortunaMin);
		return oroscopoAnnuale;
	}

	@Override
	public Oroscopo generaOroscopoCasuale(SegnoZodiacale segno) {
		return new OroscopoMensile(segno, strategiaSelezione.seleziona(this.getRepository().getPrevisioni("amore"), segno),
												  strategiaSelezione.seleziona(this.getRepository().getPrevisioni("lavoro"), segno),
												  strategiaSelezione.seleziona(this.getRepository().getPrevisioni("salute"), segno));
	}
}

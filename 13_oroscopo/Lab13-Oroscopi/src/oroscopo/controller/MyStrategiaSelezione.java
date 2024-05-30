package oroscopo.controller;

import java.util.List;
import java.util.Random;

import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;


public class MyStrategiaSelezione implements StrategiaSelezione {

	private Random r = new Random();
	
	@Override
	public Previsione seleziona(List<Previsione> previsioni, SegnoZodiacale segno) {
		while (true) {
			int numeroRandom = r.nextInt(previsioni.size());
			if (!previsioni.get(numeroRandom).validaPerSegno(segno)) continue;
			return previsioni.get(numeroRandom);
		}
	}

}

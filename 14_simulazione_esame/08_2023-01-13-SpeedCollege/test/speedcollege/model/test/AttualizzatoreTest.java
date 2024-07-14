package speedcollege.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import speedcollege.model.AttivitaFormativa;
import speedcollege.model.Attualizzatore;
import speedcollege.model.Carriera;
import speedcollege.model.Esame;
import speedcollege.model.Voto;

import java.time.LocalDate;
import java.util.List;


class AttualizzatoreTest {

	Carriera carriera;
	Attualizzatore attualizzatore;
	
	@Test
	void testOK_MediaPesata_VariEsamiConVoto_27CfuTot_OrdineInverso() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);
		
		attualizzatore = new Attualizzatore(carriera, 
				(esame, localdate) -> esame.getVotoIniziale().getValue().getAsInt()*1.0,
				LocalDate.now());
		assertEquals(23.56, attualizzatore.mediaPesata(),0.01);
	}
	
	@Test
	void testOK_MediaPesata_VariEsamiConVotoOGiudizio_33CfuTot_OrdineInverso() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);

		attualizzatore = new Attualizzatore(carriera, 
				(esame, localdate) -> esame.getVotoIniziale().getValue().getAsInt()*1.0,
				LocalDate.now());
		assertEquals(23.56, attualizzatore.mediaPesata(),0.01);

		AttivitaFormativa af4 = new AttivitaFormativa(26337, "LINGUA INGLESE B-2", 6);
		Esame esameInglese = new Esame(af4, LocalDate.of(2020, 7, 2), Voto.IDONEO);
		carriera.inserisci(esameInglese);

		attualizzatore.setCarriera(carriera);
		assertEquals(23.56, attualizzatore.mediaPesata(),0.01);
	}
		
	@Test
	void testOK_MediaPesata_VariEsamiConVoto_27CfuTot() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi1);
		AttivitaFormativa af3 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi2);
		
		attualizzatore = new Attualizzatore(carriera, 
					(esame, localdate) -> esame.getVotoIniziale().getValue().getAsInt()*1.0,
					LocalDate.now());
		
		assertEquals(23.0, attualizzatore.mediaPesata(), 0.01);
	}

	
	@Test
	void testOK_MediaPesata_VariEsamiConVoto_DueConEsitoPositivo_21CfuTot() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esame1a = new Esame(af1, LocalDate.of(2020, 6, 6), Voto.RESPINTO);
		Esame esame1b = new Esame(af1, LocalDate.of(2020, 6, 28), Voto.RITIRATO);
		Esame esame2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esame1a);
		carriera.inserisci(esame1b);
		carriera.inserisci(esame2);
		assertEquals(12, carriera.creditiAcquisiti());
		AttivitaFormativa af2 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esame3 = new Esame(af2, LocalDate.of(2020, 6, 28), Voto.RESPINTO);
		Esame esame4 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esame3);
		carriera.inserisci(esame4);
		assertEquals(21, carriera.creditiAcquisiti());
		assertEquals(List.of(esame1a,esame3,esame1b,esame4,esame2), carriera.getListaEsami());
		
		attualizzatore = new Attualizzatore(carriera, 
				(esame, localdate) -> esame.getVotoIniziale().getValue().getAsInt()*1.0,
				LocalDate.now());	
		
		assertEquals(21.0, attualizzatore.mediaPesata(),0.01);
	}
	
	@Test
	void testOK_MediaPesata_SingoloEsameConVotoDatoPiuVolte() {
		carriera = new Carriera();
		AttivitaFormativa af = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esame1a = new Esame(af, LocalDate.of(2020, 6, 6), Voto.RESPINTO);
		Esame esame1b = new Esame(af, LocalDate.of(2020, 6, 28), Voto.RITIRATO);
		Esame esame2 = new Esame(af, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esame1a);
		assertEquals(0, carriera.creditiAcquisiti());
		assertEquals(List.of(esame1a), carriera.getListaEsami());
		carriera.inserisci(esame1b);
		assertEquals(0, carriera.creditiAcquisiti());
		carriera.inserisci(esame2);
		assertEquals(12, carriera.creditiAcquisiti());

		attualizzatore = new Attualizzatore(carriera, 
				(esame, localdate) -> esame.getVotoIniziale().getValue().getAsInt()*1.0,
				LocalDate.now());
		assertEquals(18.0, attualizzatore.mediaPesata(), 0.01);
	}
	
	@Test
	void testOK_MediaPesata_NessunEsameConVotoSuperato() {
		carriera = new Carriera();
		AttivitaFormativa af = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esame1a = new Esame(af, LocalDate.of(2020, 6, 6), Voto.RESPINTO);
		Esame esame1b = new Esame(af, LocalDate.of(2020, 6, 28), Voto.RITIRATO);
		carriera.inserisci(esame1a);
		carriera.inserisci(esame1b);
		
		attualizzatore = new Attualizzatore(carriera, 
				(esame, localdate) -> esame.getVotoIniziale().getValue().getAsInt()*1.0,
				LocalDate.now());

		assertEquals(Double.NaN, attualizzatore.mediaPesata(), 0.01);		
		// NB: in JUnit, gli NaN sono considerati uguali fra loro, difformemente dal classico == fra Double 
	}}

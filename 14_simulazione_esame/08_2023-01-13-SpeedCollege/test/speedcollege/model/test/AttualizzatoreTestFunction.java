package speedcollege.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import speedcollege.model.AttivitaFormativa;
import speedcollege.model.Attualizzatore;
import speedcollege.model.Carriera;
import speedcollege.model.Esame;
import speedcollege.model.Voto;

import java.time.LocalDate;


class AttualizzatoreTestFunction {

	Carriera carriera;
	Attualizzatore attualizzatore;
	
	@Test
	void testOK_EntroUnMese_NessunDecadimento() {
		carriera = new Carriera();
		
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 1, 12), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 1, 20), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 2, 6), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);

		attualizzatore = new Attualizzatore(carriera, 
				Attualizzatore.UN_VOTO_IN_MENO_AL_MESE,
				LocalDate.of(2020,2,10));
		
		assertEquals(23.56, attualizzatore.mediaPesata(),0.01);
	}
	
	@Test
	void testOK_DopoUnMese_NessunDecadimentoPerche18() {
		carriera = new Carriera();
		
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 1, 12), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 1, 20), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 2, 6), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);

		attualizzatore = new Attualizzatore(carriera, 
				Attualizzatore.UN_VOTO_IN_MENO_AL_MESE,
				LocalDate.of(2020,2,13));
		
		assertEquals(23.56, attualizzatore.mediaPesata(),0.01);
	}
	
	@Test
	void testOK_DopoUnMese_DecadimentoDel25In24() {
		carriera = new Carriera();
		
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 1, 12), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 1, 20), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 2, 6), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);

		attualizzatore = new Attualizzatore(carriera, 
				Attualizzatore.UN_VOTO_IN_MENO_AL_MESE,
				LocalDate.of(2020,2,20));
		
		assertEquals(23.33, attualizzatore.mediaPesata(),0.01);
	}
	
	@Test
	void testOK_DopoPiuDiUnMese_DecadimentoDel25In24() {
		carriera = new Carriera();
		
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 1, 12), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 1, 20), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 2, 6), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);

		attualizzatore = new Attualizzatore(carriera, 
				Attualizzatore.UN_VOTO_IN_MENO_AL_MESE,
				LocalDate.of(2020,3,5));
		
		assertEquals(23.33, attualizzatore.mediaPesata(),0.01);
	}
	
	@Test
	void testOK_DopoPiuDiUnMese_DueDecadimenti_25in24_30in29() {
		carriera = new Carriera();
		
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 1, 12), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 1, 20), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 2, 6), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);

		attualizzatore = new Attualizzatore(carriera, 
				Attualizzatore.UN_VOTO_IN_MENO_AL_MESE,
				LocalDate.of(2020,3,6));
		
		assertEquals(23.0, attualizzatore.mediaPesata(),0.01);
	}
	
	@Test
	void testOK_DopoDueMesi_DueDecadimenti_25in23_30in29() {
		carriera = new Carriera();
		
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 1, 12), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 1, 20), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 2, 6), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);

		attualizzatore = new Attualizzatore(carriera, 
				Attualizzatore.UN_VOTO_IN_MENO_AL_MESE,
				LocalDate.of(2020,3,20));
		
		assertEquals(22.78, attualizzatore.mediaPesata(),0.01);
	}
	
	@Test
	void testOK_DopoDueMesi_DueDecadimenti_25in23_30in28() {
		carriera = new Carriera();
		
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 1, 12), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 1, 20), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 2, 6), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);

		attualizzatore = new Attualizzatore(carriera, 
				Attualizzatore.UN_VOTO_IN_MENO_AL_MESE,
				LocalDate.of(2020,4,6));
		
		assertEquals(22.44, attualizzatore.mediaPesata(),0.01);
	}
	
	
}

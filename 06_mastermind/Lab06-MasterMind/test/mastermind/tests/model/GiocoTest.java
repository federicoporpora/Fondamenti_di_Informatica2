package mastermind.tests.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mastermind.model.*;

public class GiocoTest {

	@Test
	public void testSituazioneIniziale() {
		Gioco gioco = new Gioco(10,4);
		assertEquals(10, gioco.maxTentativi());
		assertEquals(10, gioco.tentativiRimasti());
		assertEquals(0, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(Status.IN_CORSO, gioco.stato());	
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
	}
	
	@Test
	public void testDopo1Tentativo() {
		Gioco gioco = new Gioco(10,4); 
		assertEquals(10, gioco.maxTentativi());
		assertEquals(10, gioco.tentativiRimasti());
		assertEquals(0, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(Status.IN_CORSO, gioco.stato());
		Combinazione c = new Combinazione(4);
		c.setPiolo(0, PioloDiGioco.ROSSO);
		c.setPiolo(1, PioloDiGioco.GIALLO);
		c.setPiolo(2, PioloDiGioco.VERDE);
		c.setPiolo(3, PioloDiGioco.BLU);
		Risposta risposta = new Risposta(4);
		risposta.setPiolo(0, PioloRisposta.BIANCO);
		risposta.setPiolo(1, PioloRisposta.BIANCO);
		risposta.setPiolo(2, PioloRisposta.NERO);
		risposta.setPiolo(3, PioloRisposta.NERO);
		Status status = gioco.registraMossa(c, risposta);
		assertEquals(Status.IN_CORSO, status);
		assertEquals(10, gioco.maxTentativi());
		assertEquals(9, gioco.tentativiRimasti());
		assertEquals(1, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
		assertTrue(gioco.toString().contains("ROSSO,GIALLO,VERDE,BLU\t\tBIANCO,BIANCO,NERO,NERO"+ System.lineSeparator()));
		//System.out.println("testDopo1Tentativo-ultimo tentativo: " + gioco.ultimoTentativo());
		//System.out.println("testDopo1Tentativo-ultima risposta:  " + gioco.ultimaRisposta());
		assertEquals(c,gioco.ultimoTentativo());
		Risposta r = new Risposta(4);
		r.setPiolo(0, PioloRisposta.BIANCO);
		r.setPiolo(1, PioloRisposta.BIANCO);
		r.setPiolo(2, PioloRisposta.NERO);
		r.setPiolo(3, PioloRisposta.NERO);
		assertTrue(r.equals(gioco.ultimaRisposta()));
	}
	

	@Test
	public void testDopo2Tentativi() {
		Gioco gioco = new Gioco(10,4);
		assertEquals(10, gioco.maxTentativi());
		assertEquals(10, gioco.tentativiRimasti());
		assertEquals(0, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(Status.IN_CORSO, gioco.stato());
		Combinazione c1 = new Combinazione(4);
		c1.setPiolo(0, PioloDiGioco.ROSSO);
		c1.setPiolo(1, PioloDiGioco.GIALLO);
		c1.setPiolo(2, PioloDiGioco.VERDE);
		c1.setPiolo(3, PioloDiGioco.BLU);
		Risposta risposta1 = new Risposta(4);
		risposta1.setPiolo(0, PioloRisposta.BIANCO);
		risposta1.setPiolo(1, PioloRisposta.BIANCO);
		risposta1.setPiolo(2, PioloRisposta.NERO);
		risposta1.setPiolo(3, PioloRisposta.NERO);
		Status status1 = gioco.registraMossa(c1, risposta1);
		assertEquals(Status.IN_CORSO, status1);
		assertEquals(10, gioco.maxTentativi());
		assertEquals(9, gioco.tentativiRimasti());
		assertEquals(1, gioco.tentativiEffettuati());
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
		assertTrue(gioco.toString().contains("ROSSO,GIALLO,VERDE,BLU\t\tBIANCO,BIANCO,NERO,NERO"+ System.lineSeparator()));
		//System.out.println("testDopo1Tentativo-ultimo tentativo: " + gioco.ultimoTentativo());
		//System.out.println("testDopo1Tentativo-ultima risposta:  " + gioco.ultimaRisposta());
		assertEquals(c1,gioco.ultimoTentativo());
		Risposta r1 = new Risposta(4);
		r1.setPiolo(0, PioloRisposta.BIANCO);
		r1.setPiolo(1, PioloRisposta.BIANCO);
		r1.setPiolo(2, PioloRisposta.NERO);
		r1.setPiolo(3, PioloRisposta.NERO);
		assertTrue(r1.equals(gioco.ultimaRisposta()));
		//--------------
		Combinazione c2 = new Combinazione(4);
		c2.setPiolo(0, PioloDiGioco.ROSSO);
		c2.setPiolo(1, PioloDiGioco.BLU);
		c2.setPiolo(2, PioloDiGioco.VERDE);
		c2.setPiolo(3, PioloDiGioco.GIALLO);
		Risposta risposta2 = new Risposta(4);
		risposta2.setPiolo(0, PioloRisposta.NERO);
		risposta2.setPiolo(1, PioloRisposta.BIANCO);
		risposta2.setPiolo(2, PioloRisposta.NERO);
		risposta2.setPiolo(3, PioloRisposta.NERO);
		Status status2 = gioco.registraMossa(c2, risposta2);
		assertEquals(Status.IN_CORSO, status2);
		assertEquals(10, gioco.maxTentativi());
		assertEquals(8, gioco.tentativiRimasti());
		assertEquals(2, gioco.tentativiEffettuati());
		//System.out.println(gioco);
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
		//System.out.println(gioco);
		assertTrue(gioco.toString().contains("1) ROSSO,GIALLO,VERDE,BLU\t\tBIANCO,BIANCO,NERO,NERO"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("2) ROSSO,BLU,VERDE,GIALLO\t\tNERO,BIANCO,NERO,NERO"+ System.lineSeparator()));
		assertEquals(c2,gioco.ultimoTentativo());
		assertTrue(risposta2.equals(gioco.ultimaRisposta()));
	}

}

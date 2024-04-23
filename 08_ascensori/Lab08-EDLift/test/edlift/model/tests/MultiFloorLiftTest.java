package edlift.model.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edlift.model.*;

public class MultiFloorLiftTest {

	@Test
	public void testGetTipo() {
		Lift lift = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals("MULTI", lift.getMode().toUpperCase());
	}

	@Test
	public void testGetCurrentFloor() {
		Lift lift = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(5, lift.getCurrentFloor());
	}

	@Test
	public void testSetCurrentFloor() {
		Lift lift = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(5, lift.getCurrentFloor());
		lift.setCurrentFloor(3);
		assertEquals(3, lift.getCurrentFloor());
	}

	@Test
	public void testGetCallingFloor() {
		Lift lift = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(5, lift.getRequestedFloor()); // default
	}

	@Test
	public void testSetCallingFloor() {
		Lift lift = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(5, lift.getRequestedFloor()); // default
		lift.setRequestedFloor(4);
		assertEquals(4, lift.getRequestedFloor());
		assertEquals(5, lift.getCurrentFloor());
	}

	@Test
	public void testGetCurrentState() {
		Lift lift = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(LiftState.REST, lift.getCurrentState());
	}
	
	@Test
	public void testSetCurrentState() {
		Lift lift = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(LiftState.REST, lift.getCurrentState());
		lift.setCurrentState(LiftState.UP);
		assertEquals(LiftState.UP, lift.getCurrentState());
		lift.setCurrentState(LiftState.DOWN);
		lift.setCurrentState(LiftState.DOWN);
		assertEquals(LiftState.DOWN, lift.getCurrentState());
		lift.setCurrentState(LiftState.REST);
		assertEquals(LiftState.REST, lift.getCurrentState());
	}
	
	
	@Test
	public void testGoToFloorOK_ChiamataAccettataPerPianoSuperiore() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(7));
		// Ascensore fermo al piano 5, ha registrato la chiamata per il 7
		assertEquals(7, asc.getRequestedFloor());
		assertEquals(5, asc.getCurrentFloor());
	}
	
	@Test
	public void testGoToFloorOK_ChiamataAccettataPerPianoInferiore() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(2));
		// Ascensore fermo al piano 5, accetta la chiamata per il 2
		assertEquals(2, asc.getRequestedFloor());
		assertEquals(5, asc.getCurrentFloor());
	}

	@Test
	public void testGoToFloorOK_UnaPrenotazioneAccettataConChiamataInCorso() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(3));
		// Ascensore fermo al piano 5, accetta la chiamata per il 3
		assertEquals(3, asc.getRequestedFloor());
		assertEquals(5, asc.getCurrentFloor());
		asc.setCurrentState(LiftState.DOWN);
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(7));
		assertTrue(asc.hasPendingFloors());
	}

	@Test
	public void testGoToFloorOK_DuePrenotazioniAccettateConChiamataInCorso() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(3));
		// Ascensore fermo al piano 5, accetta la chiamata per il 3
		assertEquals(3, asc.getRequestedFloor());
		assertEquals(5, asc.getCurrentFloor());
		asc.setCurrentState(LiftState.DOWN);
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(7));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(6));
		assertTrue(asc.hasPendingFloors());
	}
	
	@Test
	public void testGoToFloorOK_TrePrenotazioniAccettateConChiamataInCorso() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(3));
		// Ascensore fermo al piano 5, accetta la chiamata per il 3
		assertEquals(3, asc.getRequestedFloor());
		assertEquals(5, asc.getCurrentFloor());
		asc.setCurrentState(LiftState.DOWN);
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(7));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(6));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(9));
		assertTrue(asc.hasPendingFloors());
	}
	
	@Test
	public void testGoToFloorOK_QuattroPrenotazioniAccettateConChiamataInCorsoDOWN() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(3));
		// Ascensore fermo al piano 5, accetta la chiamata per il 3
		assertEquals(3, asc.getRequestedFloor());
		assertEquals(5, asc.getCurrentFloor());
		asc.setCurrentState(LiftState.DOWN);
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(7));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(6));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(9));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(4));
		assertTrue(asc.hasPendingFloors());
	}
	
	@Test
	public void testGoToFloorOK_QuattroPrenotazioniAccettateConChiamataInCorsoUP() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(6));
		// Ascensore fermo al piano 5, accetta la chiamata per il 3
		assertEquals(6, asc.getRequestedFloor());
		assertEquals(5, asc.getCurrentFloor());
		asc.setCurrentState(LiftState.UP);
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(7));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(3));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(9));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(4));
		assertTrue(asc.hasPendingFloors());
	}
	
	@Test
	public void testGoToFloorKO_QuintaPrenotazioneRifiutata() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(3));
		// Ascensore fermo al piano 5, accetta la chiamata per il 3
		assertEquals(3, asc.getRequestedFloor());
		assertEquals(5, asc.getCurrentFloor());
		asc.setCurrentState(LiftState.DOWN);
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(7));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(6));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(9));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(4));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: rifiutata per superamento capacità pool
		assertEquals(RequestResult.REJECTED, asc.goToFloor(5));
	}
	
	
	@Test
	public void testGoToFloorKO_PianoDiDestinazioneOltreIlMax() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertThrows(IllegalArgumentException.class, () -> asc.goToFloor(11));
	}
	
	@Test
	public void testGoToFloorKO_PianoDiDestinazioneSottoIlMin() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertThrows(IllegalArgumentException.class, () -> asc.goToFloor(1));
	}
	
	@Test
	public void testNextPendingFloorDOWN() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(3));
		// Ascensore fermo al piano 5, accetta la chiamata per il 3
		assertEquals(3, asc.getRequestedFloor());
		assertEquals(5, asc.getCurrentFloor());
		asc.setCurrentState(LiftState.DOWN);
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(7));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(6));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(9));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(4));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: rifiutata per superamento capacità pool
		assertEquals(RequestResult.REJECTED, asc.goToFloor(5));
		//-------
		assertEquals(7, asc.nextPendingFloor(LiftState.DOWN));
		assertEquals(6, asc.nextPendingFloor(LiftState.DOWN));
		assertEquals(9, asc.nextPendingFloor(LiftState.DOWN));
		assertEquals(4, asc.nextPendingFloor(LiftState.DOWN));
		assertEquals(Integer.MIN_VALUE, asc.nextPendingFloor(LiftState.DOWN));
	}
	
	@Test
	public void testNextPendingFloorUP() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(6));
		// Ascensore fermo al piano 5, accetta la chiamata per il 6
		assertEquals(6, asc.getRequestedFloor());
		assertEquals(5, asc.getCurrentFloor());
		asc.setCurrentState(LiftState.UP);
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(7));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(3));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(9));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(4));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: rifiutata per superamento capacità pool
		assertEquals(RequestResult.REJECTED, asc.goToFloor(5));
		//-------
		assertEquals(7, asc.nextPendingFloor(LiftState.UP));
		assertEquals(3, asc.nextPendingFloor(LiftState.UP));
		assertEquals(9, asc.nextPendingFloor(LiftState.UP));
		assertEquals(4, asc.nextPendingFloor(LiftState.UP));
		assertEquals(Integer.MIN_VALUE, asc.nextPendingFloor(LiftState.UP));
	}
	
	@Test
	public void testNextPendingFloorREST() {
		Lift asc = new MultiFloorLift(2, 10, 5, 1.0);
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(6));
		// Ascensore fermo al piano 5, accetta la chiamata per il 6
		assertEquals(6, asc.getRequestedFloor());
		assertEquals(5, asc.getCurrentFloor());
		asc.setCurrentState(LiftState.UP);
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(7));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(3));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(9));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: accetta comunque la prenotazione
		assertEquals(RequestResult.ACCEPTED, asc.goToFloor(4));
		assertTrue(asc.hasPendingFloors());
		// nuova chiamata: rifiutata per superamento capacità pool
		assertEquals(RequestResult.REJECTED, asc.goToFloor(5));
		//------- the list is not in REST state, but nextPendingFloor is not sensitive to the lift state in this type of lift
		assertEquals(7, asc.nextPendingFloor(LiftState.REST));
		assertEquals(3, asc.nextPendingFloor(LiftState.REST));
		assertEquals(9, asc.nextPendingFloor(LiftState.REST));
		assertEquals(4, asc.nextPendingFloor(LiftState.REST));
		assertEquals(Integer.MIN_VALUE, asc.nextPendingFloor(LiftState.REST));
	}
}

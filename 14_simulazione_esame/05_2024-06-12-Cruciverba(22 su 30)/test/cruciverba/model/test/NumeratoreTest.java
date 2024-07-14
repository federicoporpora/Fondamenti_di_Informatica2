package cruciverba.model.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Map;
import java.util.OptionalInt;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cruciverba.model.Numeratore;
import cruciverba.model.Orientamento;
import cruciverba.model.Cruciverba;

class NumeratoreTest {

	static Cruciverba schema;
	static Numeratore numeratore;
	
	@BeforeAll
	static void setup() {
		Cruciverba schema = new Cruciverba(11,17);
		
		//System.out.print(schema);
		assertEquals(11, schema.getNumRighe());
		assertEquals(17, schema.getNumColonne());
		
		schema.setParola(0,0,"LI", Orientamento.ORIZZONTALE);
		schema.setParola(0,3,"AV", Orientamento.ORIZZONTALE);
		schema.setParola(0,6,"NOLI", Orientamento.ORIZZONTALE);
		schema.setParola(0,12,"VLAD", Orientamento.ORIZZONTALE);
		
		schema.setParola(1,0,"EST", Orientamento.ORIZZONTALE);
		schema.setParola(1,4,"ALEA", Orientamento.ORIZZONTALE);
		schema.setParola(1,9,"RETE", Orientamento.ORIZZONTALE);
		schema.setParola(1,14,"SIM", Orientamento.ORIZZONTALE);
		
		schema.setParola(2,0,"MAI", Orientamento.ORIZZONTALE);
		schema.setParola(2,4,"JET", Orientamento.ORIZZONTALE);
		schema.setParola(2,10,"VERBANO", Orientamento.ORIZZONTALE);

		schema.setParola(3,1,"ACCONTENTARSI", Orientamento.ORIZZONTALE);
		schema.setParola(3,15,"OR", Orientamento.ORIZZONTALE);
		
		schema.setParola(4,0,"EC", Orientamento.ORIZZONTALE);
		schema.setParola(4,3,"ENTUSIASMARE", Orientamento.ORIZZONTALE);
		schema.setParola(4,16,"S", Orientamento.ORIZZONTALE);
			
		schema.setParola(5,0,"A", Orientamento.ORIZZONTALE);
		schema.setParola(5,2,"ATTANAGLIATO", Orientamento.ORIZZONTALE);
		schema.setParola(5,15,"ME", Orientamento.ORIZZONTALE);
		
		schema.setParola(6,1,"INT", Orientamento.ORIZZONTALE);
		schema.setParola(6,5,"MOLECOLA", Orientamento.ORIZZONTALE);
		schema.setParola(6,14,"LA", Orientamento.ORIZZONTALE);
		
		schema.setParola(7,0,"CATONE", Orientamento.ORIZZONTALE);
		schema.setParola(7,7,"TRONI", Orientamento.ORIZZONTALE);
		schema.setParola(7,13,"SEGA", Orientamento.ORIZZONTALE);
		
		schema.setParola(8,0,"OSE", Orientamento.ORIZZONTALE);
		schema.setParola(8,4,"INIA", Orientamento.ORIZZONTALE);
		schema.setParola(8,10,"E", Orientamento.ORIZZONTALE);
		schema.setParola(8,12,"SUGAR", Orientamento.ORIZZONTALE);
		
		schema.setParola(9,0,"CI", Orientamento.ORIZZONTALE);
		schema.setParola(9,3,"ALTERCO", Orientamento.ORIZZONTALE);
		schema.setParola(9,11,"ANSA", Orientamento.ORIZZONTALE);
		schema.setParola(9,16,"G", Orientamento.ORIZZONTALE);

		schema.setParola(10,0,"O", Orientamento.ORIZZONTALE);
		schema.setParola(10,2,"ALOE", Orientamento.ORIZZONTALE);
		schema.setParola(10,7,"ENOTECA", Orientamento.ORIZZONTALE);
		schema.setParola(10,15,"AO", Orientamento.ORIZZONTALE);	
		
		//System.out.print(schema);
		
		assertArrayEquals(new String[] {"LEM", "EA", "COCO"},schema.paroleInColonna(0));
		assertArrayEquals(new String[] {"ISAAC", "IASI"},schema.paroleInColonna(1));
		assertArrayEquals(new String[] {"TIC", "ANTE", "A"},schema.paroleInColonna(2));
		assertArrayEquals(new String[] {"A", "CETTO", "AL"},schema.paroleInColonna(3));
		assertArrayEquals(new String[] {"VAJONT", "NILO"},schema.paroleInColonna(4));
		assertArrayEquals(new String[] {"LENTAMENTE"},schema.paroleInColonna(5));	
		assertArrayEquals(new String[] {"NETTUNO", "IE"},schema.paroleInColonna(6));
		assertArrayEquals(new String[] {"OA", "ESALTARE"},schema.paroleInColonna(7));
		assertArrayEquals(new String[] {"L", "NIGER", "CN"},schema.paroleInColonna(8));
		assertArrayEquals(new String[] {"IR", "TALCO", "OO"},schema.paroleInColonna(9));
		assertArrayEquals(new String[] {"EVASIONE", "T"},schema.paroleInColonna(10));
		assertArrayEquals(new String[] {"TERMALI", "AE"},schema.paroleInColonna(11));
		assertArrayEquals(new String[] {"VERSATA", "SNC"},schema.paroleInColonna(12));
		assertArrayEquals(new String[] {"L", "BIRO", "SUSA"},schema.paroleInColonna(13));
		assertArrayEquals(new String[] {"ASA", "E", "LEGA"},schema.paroleInColonna(14));
		assertArrayEquals(new String[] {"DINO", "MAGA", "A"},schema.paroleInColonna(15));
		assertArrayEquals(new String[] {"MORSE", "ARGO"},schema.paroleInColonna(16));
		
		assertArrayEquals(new String[] {"LI",  "AV",   "NOLI", "VLAD"}, schema.paroleInRiga(0));
		assertArrayEquals(new String[] {"EST", "ALEA", "RETE", "SIM"}, schema.paroleInRiga(1));
		assertArrayEquals(new String[] {"MAI", "JET",  "VERBANO"}, schema.paroleInRiga(2));
		assertArrayEquals(new String[] {"ACCONTENTARSI", "OR"}, schema.paroleInRiga(3));
		assertArrayEquals(new String[] {"EC", "ENTUSIASMARE", "S"}, schema.paroleInRiga(4));
		assertArrayEquals(new String[] {"A", "ATTANAGLIATO", "ME"}, schema.paroleInRiga(5));
		assertArrayEquals(new String[] {"INT", "MOLECOLA", "LA"}, schema.paroleInRiga(6));
		assertArrayEquals(new String[] {"CATONE", "TRONI", "SEGA"}, schema.paroleInRiga(7));
		assertArrayEquals(new String[] {"OSE", "INIA", "E", "SUGAR"}, schema.paroleInRiga(8));
		assertArrayEquals(new String[] {"CI", "ALTERCO", "ANSA", "G"}, schema.paroleInRiga(9));
		assertArrayEquals(new String[] {"O", "ALOE", "ENOTECA", "AO"}, schema.paroleInRiga(10));
		
		///--------------- fine check Schema 
		
		numeratore = new Numeratore(schema);
		
		assertSame(schema, numeratore.getSchema());
		assertEquals(schema.getNumRighe(), numeratore.getNumRighe());
		assertEquals(schema.getNumColonne(), numeratore.getNumColonne());
		
		System.out.print(numeratore);
	}
	
	@Test
	public void testOK_NumeratoreConSchemaConfigurato() {

		assertEquals( OptionalInt.of( 1), numeratore.getNumeroCella(0, 0) );
		assertEquals( OptionalInt.of( 2), numeratore.getNumeroCella(0, 1) );
		assertEquals( OptionalInt.of( 3), numeratore.getNumeroCella(0, 3) );
		assertEquals( OptionalInt.of( 4), numeratore.getNumeroCella(0, 4) );
		assertEquals( OptionalInt.of( 8), numeratore.getNumeroCella(0,12) );
		assertEquals( OptionalInt.of(10), numeratore.getNumeroCella(0,15) );
		
		assertEquals( OptionalInt.of(19), numeratore.getNumeroCella(1,16) );
		
		assertEquals( OptionalInt.of(20), numeratore.getNumeroCella(2, 0) );
		assertEquals( OptionalInt.of(21), numeratore.getNumeroCella(2, 4) );
		assertEquals( OptionalInt.of(22), numeratore.getNumeroCella(2,10) );
		assertEquals( OptionalInt.of(23), numeratore.getNumeroCella(2,13) );
		assertEquals( OptionalInt.empty(), numeratore.getNumeroCella(2,1) );
		assertEquals( OptionalInt.empty(), numeratore.getNumeroCella(2,2) );
		assertEquals( OptionalInt.empty(), numeratore.getNumeroCella(2,16));
		
		assertEquals( OptionalInt.empty(), numeratore.getNumeroCella(3,0) );
		assertEquals( OptionalInt.of(24), numeratore.getNumeroCella(3, 1) );
		assertEquals( OptionalInt.of(25), numeratore.getNumeroCella(3, 3) );
		assertEquals( OptionalInt.of(28), numeratore.getNumeroCella(3, 9) );
		assertEquals( OptionalInt.of(29), numeratore.getNumeroCella(3,15) );
		
		assertEquals( OptionalInt.empty(), numeratore.getNumeroCella(6,0) );
		assertEquals( OptionalInt.of(34), numeratore.getNumeroCella(6, 1) );
		assertEquals( OptionalInt.of(35), numeratore.getNumeroCella(6, 5) );
		assertEquals( OptionalInt.of(36), numeratore.getNumeroCella(6,14) );
		
		assertEquals( OptionalInt.of(41), numeratore.getNumeroCella(7,16) );
		
		assertEquals( OptionalInt.of(42), numeratore.getNumeroCella(8, 0) );
		assertEquals( OptionalInt.of(43), numeratore.getNumeroCella(8, 4) );
		assertEquals( OptionalInt.of(44), numeratore.getNumeroCella(8, 6) );
		assertEquals( OptionalInt.of(45), numeratore.getNumeroCella(8,12) );
	}
	
	
	@Test
	public void testOK_Orizzontali() { 
			
		assertEquals(new TreeMap<>(Map.ofEntries(
			Map.entry(1, "LI"), Map.entry(3, "AV"), Map.entry(5, "NOLI"), Map.entry(8, "VLAD"),
			Map.entry(11, "EST"), Map.entry(13, "ALEA"), Map.entry(15, "RETE"), Map.entry(18, "SIM"),
			Map.entry(20, "MAI"), Map.entry(21, "JET"), Map.entry(22, "VERBANO"), 
			Map.entry(24, "ACCONTENTARSI"), Map.entry(29, "OR"),
			Map.entry(30, "EC"), Map.entry(31, "ENTUSIASMARE"),
			Map.entry(32, "ATTANAGLIATO"), Map.entry(33, "ME"),
			Map.entry(34, "INT"), Map.entry(35, "MOLECOLA"), Map.entry(36, "LA"),
			Map.entry(37, "CATONE"), Map.entry(39, "TRONI"), Map.entry(40, "SEGA"),
			Map.entry(42, "OSE"), Map.entry(43, "INIA"), Map.entry(45, "SUGAR"),
			Map.entry(46, "CI"), Map.entry(47, "ALTERCO"), Map.entry(50, "ANSA"),
			Map.entry(51, "ALOE"), Map.entry(52, "ENOTECA"), Map.entry(53, "AO")
			)), 
				numeratore.orizzontali());
		}
	
	@Test
	public void testOK_Verticali() {
		
		assertEquals(new TreeMap<>(Map.ofEntries(
			Map.entry( 1, "LEM"),    Map.entry(30, "EA"),   Map.entry(37, "COCO"),
			Map.entry( 2, "ISAAC"),  Map.entry(34, "IASI"),
			Map.entry(12, "TIC"),    Map.entry(32, "ANTE"), 
			Map.entry(25, "CETTO"),  Map.entry(47, "AL"),
			Map.entry( 4, "VAJONT"), Map.entry(38, "NILO"),
			Map.entry(14, "LENTAMENTE"),
			Map.entry( 5, "NETTUNO"),Map.entry(44, "IE"),
			Map.entry(6,  "OA"),     Map.entry(26, "ESALTARE"),
			Map.entry(27, "NIGER"),  Map.entry(48, "CN"), 
			Map.entry( 7, "IR"),     Map.entry(28, "TALCO"), Map.entry(49, "OO"),
			Map.entry(16, "EVASIONE"), 
			Map.entry(17, "TERMALI"),Map.entry(50, "AE"),
			Map.entry( 8, "VERSATA"),Map.entry(45, "SNC"),
			Map.entry(23, "BIRO"),   Map.entry(40, "SUSA"),
			Map.entry( 9, "ASA"),    Map.entry(36, "LEGA"),
			Map.entry(10, "DINO"),   Map.entry(33, "MAGA"),
			Map.entry(19, "MORSE"),   Map.entry(41, "ARGO")
			)), 
				numeratore.verticali());
		}

}

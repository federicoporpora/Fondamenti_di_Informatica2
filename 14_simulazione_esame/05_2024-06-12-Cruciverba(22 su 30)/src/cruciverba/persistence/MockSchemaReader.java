package cruciverba.persistence;

import java.io.IOException;
import cruciverba.model.Cruciverba;
import cruciverba.model.Orientamento;


public class MockSchemaReader implements SchemaReader {
		
	public Cruciverba leggiSchema() throws BadFileFormatException, IOException {
		Cruciverba schema = new Cruciverba(11,17);

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
		
		return schema;
	}
}

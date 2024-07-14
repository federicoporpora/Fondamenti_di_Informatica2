package trainstats.persistence.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.jupiter.api.Test;

import trainstats.model.Train;
import trainstats.model.Recording;
import trainstats.persistence.BadFileFormatException;
import trainstats.persistence.MyTrainReader;

public class MyTrainReaderTest {

	@Test
	void testOK() throws IOException {
		StringReader fakeReader = new StringReader(
				           "MILANO CENTRALE		;--   ;--   ;13:20;13:54\r\n"
				  + "		MILANO LAMBRATE		;13:26;13:59;13:27;14:01\r\n"
				  + "		MILANO ROGOREDO		;13:31;14:05;13:32;14:07\r\n"
				  + "		LODI           		;13:46;14:21;13:47;14:22\r\n"
				  + "		CASALPUSTERLENGO	;13:57;14:34;13:58;14:36\r\n"
				  + "		PIACENZA       		;14:12;14:45;14:14;14:48\r\n"
				  + "		FIORENZUOLA    		;14:26;14:58;14:27;14:59\r\n"
				  + "		FIDENZA        		;14:35;15:06;14:37;15:07\r\n"
				  + "		PARMA          		;14:58;15:18;15:00;15:19\r\n"
				  + "		SANT'ILARIO    		;15:07;15:25;15:08;15:27\r\n"
				  + "		REGGIO EMILIA  		;15:18;15:36;15:20;15:38\r\n"
				  + "		RUBIERA        		;15:27;15:43;15:28;15:45\r\n"
				  + "		MODENA         		;15:36;15:54;15:38;15:57\r\n"
				  + "		CASTELFRANCO EMILIA	;15:45;16:02;15:46;16:04\r\n"
				  + "		SAMOGGIA       		;15:51;16:09;15:52;16:09\r\n"
				  + "		ANZOLA         		;15:56;16:13;15:57;16:14\r\n"
				  + "		BOLOGNA C.LE   		;16:10;16:24;--   ;--"
				);		
		Train train = new MyTrainReader().readTrain(fakeReader);
		//
		List<Recording> recList = train.getRecordings();
		assertEquals(17, recList.size());
		assertEquals("MILANO CENTRALE", train.getFirstStation());
		assertEquals("BOLOGNA C.LE", train.getLastStation());
	}
	
	@Test
	void testKO_Missing4thItemInFirstLine() throws IOException {
		StringReader fakeReader = new StringReader(
				           "MILANO CENTRALE		;--   ;--   ;13:20;\r\n"
				  + "		MILANO LAMBRATE		;13:26;13:59;13:27;14:01\r\n"
				  + "		MILANO ROGOREDO		;13:31;14:05;13:32;14:07\r\n"
				  + "		LODI           		;13:46;14:21;13:47;14:22\r\n"
				  + "		CASALPUSTERLENGO	;13:57;14:34;13:58;14:36\r\n"
				  + "		PIACENZA       		;14:12;14:45;14:14;14:48\r\n"
				  + "		FIORENZUOLA    		;14:26;14:58;14:27;14:59\r\n"
				  + "		FIDENZA        		;14:35;15:06;14:37;15:07\r\n"
				  + "		PARMA          		;14:58;15:18;15:00;15:19\r\n"
				  + "		SANT'ILARIO    		;15:07;15:25;15:08;15:27\r\n"
				  + "		REGGIO EMILIA  		;15:18;15:36;15:20;15:38\r\n"
				  + "		RUBIERA        		;15:27;15:43;15:28;15:45\r\n"
				  + "		MODENA         		;15:36;15:54;15:38;15:57\r\n"
				  + "		CASTELFRANCO EMILIA	;15:45;16:02;15:46;16:04\r\n"
				  + "		SAMOGGIA       		;15:51;16:09;15:52;16:09\r\n"
				  + "		ANZOLA         		;15:56;16:13;15:57;16:14\r\n"
				  + "		BOLOGNA C.LE   		;16:10;16:24;--   ;--"
				);		
		assertThrows(BadFileFormatException.class, () -> new MyTrainReader().readTrain(fakeReader));
	}
	
	@Test
	void testKO_Missing5thItemInFirstLine() throws IOException {
		StringReader fakeReader = new StringReader(
				           "MILANO CENTRALE		;--   ;--   ;13:54\r\n"
				  + "		MILANO LAMBRATE		;13:26;13:59;13:27;14:01\r\n"
				  + "		MILANO ROGOREDO		;13:31;14:05;13:32;14:07\r\n"
				  + "		LODI           		;13:46;14:21;13:47;14:22\r\n"
				  + "		CASALPUSTERLENGO	;13:57;14:34;13:58;14:36\r\n"
				  + "		PIACENZA       		;14:12;14:45;14:14;14:48\r\n"
				  + "		FIORENZUOLA    		;14:26;14:58;14:27;14:59\r\n"
				  + "		FIDENZA        		;14:35;15:06;14:37;15:07\r\n"
				  + "		PARMA          		;14:58;15:18;15:00;15:19\r\n"
				  + "		SANT'ILARIO    		;15:07;15:25;15:08;15:27\r\n"
				  + "		REGGIO EMILIA  		;15:18;15:36;15:20;15:38\r\n"
				  + "		RUBIERA        		;15:27;15:43;15:28;15:45\r\n"
				  + "		MODENA         		;15:36;15:54;15:38;15:57\r\n"
				  + "		CASTELFRANCO EMILIA	;15:45;16:02;15:46;16:04\r\n"
				  + "		SAMOGGIA       		;15:51;16:09;15:52;16:09\r\n"
				  + "		ANZOLA         		;15:56;16:13;15:57;16:14\r\n"
				  + "		BOLOGNA C.LE   		;16:10;16:24;--   ;--"
				);		
		assertThrows(BadFileFormatException.class, () -> new MyTrainReader().readTrain(fakeReader));
	}
	
	@Test
	void testKO_Missing1stItemInFirstLine() throws IOException {
		StringReader fakeReader = new StringReader(
				           "MILANO CENTRALE		;--   ;13:20;13:54\r\n"
				  + "		MILANO LAMBRATE		;13:26;13:59;13:27;14:01\r\n"
				  + "		MILANO ROGOREDO		;13:31;14:05;13:32;14:07\r\n"
				  + "		LODI           		;13:46;14:21;13:47;14:22\r\n"
				  + "		CASALPUSTERLENGO	;13:57;14:34;13:58;14:36\r\n"
				  + "		PIACENZA       		;14:12;14:45;14:14;14:48\r\n"
				  + "		FIORENZUOLA    		;14:26;14:58;14:27;14:59\r\n"
				  + "		FIDENZA        		;14:35;15:06;14:37;15:07\r\n"
				  + "		PARMA          		;14:58;15:18;15:00;15:19\r\n"
				  + "		SANT'ILARIO    		;15:07;15:25;15:08;15:27\r\n"
				  + "		REGGIO EMILIA  		;15:18;15:36;15:20;15:38\r\n"
				  + "		RUBIERA        		;15:27;15:43;15:28;15:45\r\n"
				  + "		MODENA         		;15:36;15:54;15:38;15:57\r\n"
				  + "		CASTELFRANCO EMILIA	;15:45;16:02;15:46;16:04\r\n"
				  + "		SAMOGGIA       		;15:51;16:09;15:52;16:09\r\n"
				  + "		ANZOLA         		;15:56;16:13;15:57;16:14\r\n"
				  + "		BOLOGNA C.LE   		;16:10;16:24;--   ;--"
				);		
		assertThrows(BadFileFormatException.class, () -> new MyTrainReader().readTrain(fakeReader));
	}
	
	@Test
	void testKO_ExtraItemInFirstLine() throws IOException {
		StringReader fakeReader = new StringReader(
				           "MILANO CENTRALE		;--   ;--   ;--   ;13:20;13:54\r\n"
				  + "		MILANO LAMBRATE		;13:26;13:59;13:27;14:01\r\n"
				  + "		MILANO ROGOREDO		;13:31;14:05;13:32;14:07\r\n"
				  + "		LODI           		;13:46;14:21;13:47;14:22\r\n"
				  + "		CASALPUSTERLENGO	;13:57;14:34;13:58;14:36\r\n"
				  + "		PIACENZA       		;14:12;14:45;14:14;14:48\r\n"
				  + "		FIORENZUOLA    		;14:26;14:58;14:27;14:59\r\n"
				  + "		FIDENZA        		;14:35;15:06;14:37;15:07\r\n"
				  + "		PARMA          		;14:58;15:18;15:00;15:19\r\n"
				  + "		SANT'ILARIO    		;15:07;15:25;15:08;15:27\r\n"
				  + "		REGGIO EMILIA  		;15:18;15:36;15:20;15:38\r\n"
				  + "		RUBIERA        		;15:27;15:43;15:28;15:45\r\n"
				  + "		MODENA         		;15:36;15:54;15:38;15:57\r\n"
				  + "		CASTELFRANCO EMILIA	;15:45;16:02;15:46;16:04\r\n"
				  + "		SAMOGGIA       		;15:51;16:09;15:52;16:09\r\n"
				  + "		ANZOLA         		;15:56;16:13;15:57;16:14\r\n"
				  + "		BOLOGNA C.LE   		;16:10;16:24;--   ;--"
				);		
		assertThrows(BadFileFormatException.class, () -> new MyTrainReader().readTrain(fakeReader));
	}
	
	@Test
	void testKO_ExtraItemInOtherLine() throws IOException {
		StringReader fakeReader = new StringReader(
				           "MILANO CENTRALE		;--   ;--   ;13:20;13:54\r\n"
				  + "		MILANO LAMBRATE		;13:26;13:59;13:27;14:01\r\n"
				  + "		MILANO ROGOREDO		;13:31;14:05;13:32;14:07\r\n"
				  + "		LODI           		;13:46;14:21;13:47;14:22\r\n"
				  + "		CASALPUSTERLENGO	;13:57;14:34;13:58;14:36\r\n"
				  + "		PIACENZA       		;14:12;14:45;-;14:14;14:48\r\n" // here
				  + "		FIORENZUOLA    		;14:26;14:58;14:27;14:59\r\n"
				  + "		FIDENZA        		;14:35;15:06;14:37;15:07\r\n"
				  + "		PARMA          		;14:58;15:18;15:00;15:19\r\n"
				  + "		SANT'ILARIO    		;15:07;15:25;15:08;15:27\r\n"
				  + "		REGGIO EMILIA  		;15:18;15:36;15:20;15:38\r\n"
				  + "		RUBIERA        		;15:27;15:43;15:28;15:45\r\n"
				  + "		MODENA         		;15:36;15:54;15:38;15:57\r\n"
				  + "		CASTELFRANCO EMILIA	;15:45;16:02;15:46;16:04\r\n"
				  + "		SAMOGGIA       		;15:51;16:09;15:52;16:09\r\n"
				  + "		ANZOLA         		;15:56;16:13;15:57;16:14\r\n"
				  + "		BOLOGNA C.LE   		;16:10;16:24;--   ;--"
				);		
		assertThrows(BadFileFormatException.class, () -> new MyTrainReader().readTrain(fakeReader));
	}

	
	@Test
	void testKO_BadTimeInOtherLine() throws IOException {
		StringReader fakeReader = new StringReader(
				           "MILANO CENTRALE		;--   ;--   ;13:20;13:54\r\n"
				  + "		MILANO LAMBRATE		;13:26;13:59;13:27;14:01\r\n"
				  + "		MILANO ROGOREDO		;13:31;14:05;13:32;14:07\r\n"
				  + "		LODI           		;13:46;14:21;13:47;14:22\r\n"
				  + "		CASALPUSTERLENGO	;13:57;14:34;13:58;14:36\r\n"
				  + "		PIACENZA       		;14:12;14:45;x4:14;14:48\r\n" // here
				  + "		FIORENZUOLA    		;14:26;14:58;14:27;14:59\r\n"
				  + "		FIDENZA        		;14:35;15:06;14:37;15:07\r\n"
				  + "		PARMA          		;14:58;15:18;15:00;15:19\r\n"
				  + "		SANT'ILARIO    		;15:07;15:25;15:08;15:27\r\n"
				  + "		REGGIO EMILIA  		;15:18;15:36;15:20;15:38\r\n"
				  + "		RUBIERA        		;15:27;15:43;15:28;15:45\r\n"
				  + "		MODENA         		;15:36;15:54;15:38;15:57\r\n"
				  + "		CASTELFRANCO EMILIA	;15:45;16:02;15:46;16:04\r\n"
				  + "		SAMOGGIA       		;15:51;16:09;15:52;16:09\r\n"
				  + "		ANZOLA         		;15:56;16:13;15:57;16:14\r\n"
				  + "		BOLOGNA C.LE   		;16:10;16:24;--   ;--"
				);		
		assertThrows(BadFileFormatException.class, () -> new MyTrainReader().readTrain(fakeReader));
	}
	
	@Test
	void testKO_WrongTimeInOtherLine() throws IOException {
		StringReader fakeReader = new StringReader(
				           "MILANO CENTRALE		;--   ;--   ;13:20;13:54\r\n"
				  + "		MILANO LAMBRATE		;13:26;13:59;13:27;14:01\r\n"
				  + "		MILANO ROGOREDO		;13:31;14:05;13:32;14:07\r\n"
				  + "		LODI           		;13:46;14:21;13:47;14:22\r\n"
				  + "		CASALPUSTERLENGO	;13:57;14:34;13:58;14:36\r\n"
				  + "		PIACENZA       		;14:12;14:45;24:14;14:48\r\n" // here
				  + "		FIORENZUOLA    		;14:26;14:58;14:27;14:59\r\n"
				  + "		FIDENZA        		;14:35;15:06;14:37;15:07\r\n"
				  + "		PARMA          		;14:58;15:18;15:00;15:19\r\n"
				  + "		SANT'ILARIO    		;15:07;15:25;15:08;15:27\r\n"
				  + "		REGGIO EMILIA  		;15:18;15:36;15:20;15:38\r\n"
				  + "		RUBIERA        		;15:27;15:43;15:28;15:45\r\n"
				  + "		MODENA         		;15:36;15:54;15:38;15:57\r\n"
				  + "		CASTELFRANCO EMILIA	;15:45;16:02;15:46;16:04\r\n"
				  + "		SAMOGGIA       		;15:51;16:09;15:52;16:09\r\n"
				  + "		ANZOLA         		;15:56;16:13;15:57;16:14\r\n"
				  + "		BOLOGNA C.LE   		;16:10;16:24;--   ;--"
				);		
		assertThrows(BadFileFormatException.class, () -> new MyTrainReader().readTrain(fakeReader));
	}
	
	@Test
	void testKO_WrongLastLine() throws IOException {
		StringReader fakeReader = new StringReader(
				           "MILANO CENTRALE		;--   ;--   ;13:20;13:54\r\n"
				  + "		MILANO LAMBRATE		;13:26;13:59;13:27;14:01\r\n"
				  + "		MILANO ROGOREDO		;13:31;14:05;13:32;14:07\r\n"
				  + "		LODI           		;13:46;14:21;13:47;14:22\r\n"
				  + "		CASALPUSTERLENGO	;13:57;14:34;13:58;14:36\r\n"
				  + "		PIACENZA       		;14:12;14:45;14:14;14:48\r\n"
				  + "		FIORENZUOLA    		;14:26;14:58;14:27;14:59\r\n"
				  + "		FIDENZA        		;14:35;15:06;14:37;15:07\r\n"
				  + "		PARMA          		;14:58;15:18;15:00;15:19\r\n"
				  + "		SANT'ILARIO    		;15:07;15:25;15:08;15:27\r\n"
				  + "		REGGIO EMILIA  		;15:18;15:36;15:20;15:38\r\n"
				  + "		RUBIERA        		;15:27;15:43;15:28;15:45\r\n"
				  + "		MODENA         		;15:36;15:54;15:38;15:57\r\n"
				  + "		CASTELFRANCO EMILIA	;15:45;16:02;15:46;16:04\r\n"
				  + "		SAMOGGIA       		;15:51;16:09;15:52;16:09\r\n"
				  + "		ANZOLA         		;15:56;16:13;15:57;16:14\r\n"
				  + "		BOLOGNA C.LE   		;16:10;--   ;16:24;--   " // here
				);		
		assertThrows(BadFileFormatException.class, () -> new MyTrainReader().readTrain(fakeReader));
	}
	
}

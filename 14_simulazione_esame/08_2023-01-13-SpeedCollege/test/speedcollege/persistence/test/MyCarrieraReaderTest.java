package speedcollege.persistence.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.jupiter.api.Test;

import speedcollege.model.Carriera;
import speedcollege.model.Esame;
import speedcollege.persistence.BadFileFormatException;
import speedcollege.persistence.MyCarrieraReader;

public class MyCarrieraReaderTest {

	@Test
	void testOK1() throws IOException {
		StringReader fakeReader = new StringReader(
				  "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				+ "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				+ "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				+ "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				+ "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				+ "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				+ "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				+ "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				+ "28011	RETI LOGICHE T (cfu:6)"
				);	
		Carriera carriera = new MyCarrieraReader().leggiCarriera(fakeReader);
		
		List<Esame> listaEsami = carriera.getListaEsami();
		assertEquals(6, listaEsami.size());
		assertEquals(27, carriera.creditiAcquisiti(), 0.01);
	}
	
	@Test
	void testOK2() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			22/07/20	23\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)	21/07/20	27\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)	"
				);	
		Carriera carriera = new MyCarrieraReader().leggiCarriera(fakeReader);
		
		List<Esame> listaEsami = carriera.getListaEsami();
		assertEquals(8, listaEsami.size());
		assertEquals(45, carriera.creditiAcquisiti(), 0.01);
	}

	@Test
	void testOK5() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)				18/06/20	ID\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			22/07/20	23\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)	21/07/20	27\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)					22/02/22	22\r\n"
				        + "\r\n"
				        + "28012	CALCOLATORI ELETTRONICI T (cfu:6)		22/01/21	RT\r\n"
				        + "28012	CALCOLATORI ELETTRONICI T (cfu:6)		22/02/21	20\r\n"
				        + "30780	FISICA GENERALE T (cfu:9)				12/02/21	25\r\n"
				        + "28032	MATEMATICA APPLICATA T (cfu:6)			02/02/21	24\r\n"
				        + "28027	SISTEMI INFORMATIVI T (cfu:9)			03/06/21	28\r\n"
				        + "28030	ECONOMIA E ORG. AZIENDALE T (cfu:6)		02/07/21	RE\r\n"
				        + "28030	ECONOMIA E ORG. AZIENDALE T (cfu:6)		22/07/21	24\r\n"
				        + "28029	ELETTROTECNICA T (cfu:6)				02/09/21	26\r\n"
				        + "28014	FOND. DI TELECOMUNICAZIONI T (cfu:9)	15/09/21	30\r\n"
				        + "28020	SISTEMI OPERATIVI T (cfu:9)				12/01/22	24\r\n"
				        + "\r\n"
				        + "28015	CONTROLLI AUTOMATICI T (cfu:9)			13/01/21	25\r\n"
				        + "28016	ELETTRONICA T (cfu:6)					10/02/21	22\r\n"
				        + "28024	RETI DI CALCOLATORI T (cfu:9)			05/02/21	23\r\n"
				        + "28659	TECNOLOGIE WEB T (cfu:9)				12/06/21	25\r\n"
				        + "28021	INGEGNERIA DEL SOFTWARE T (cfu:9)		24/06/21	27\r\n"
				        + "17268	PROVA FINALE (cfu:3)					07/03/22	ID\r\n"
				        + "\r\n"
				        + "28074	TIROCINIO T (cfu:6)						27/09/21	ID\r\n"
				        + "\r\n"
				        + "88324	AMMINISTRAZIONE DI SISTEMI T (cfu:6)	13/07/21	29\r\n"
				        + "88325	LAB. SICUREZZA INFORMATICA T (cfu:6)	07/06/21	30L"
				);	
		Carriera carriera = new MyCarrieraReader().leggiCarriera(fakeReader);
		
		List<Esame> listaEsami = carriera.getListaEsami();
		assertEquals(29, listaEsami.size());
		assertEquals(180, carriera.creditiAcquisiti(), 0.01);
	}

	@Test
	void testKO_ProvaFinalePrimaDiUnAltroEsame() throws IOException {
		StringReader fakeReader = new StringReader(
		        "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
		        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
		        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
		        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
		        + "26337	LINGUA INGLESE B-2 (cfu:6)				18/06/20	ID\r\n"
		        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
		        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
		        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			22/07/20	23\r\n"
		        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)	21/07/20	27\r\n"
		        + "28011	RETI LOGICHE T (cfu:6)					22/02/22	22\r\n"
		        + "\r\n"
		        + "28012	CALCOLATORI ELETTRONICI T (cfu:6)		22/01/21	RT\r\n"
		        + "28012	CALCOLATORI ELETTRONICI T (cfu:6)		22/02/21	20\r\n"
		        + "30780	FISICA GENERALE T (cfu:9)				12/02/21	25\r\n"
		        + "28032	MATEMATICA APPLICATA T (cfu:6)			02/02/21	24\r\n"
		        + "28027	SISTEMI INFORMATIVI T (cfu:9)			03/06/21	28\r\n"
		        + "28030	ECONOMIA E ORG. AZIENDALE T (cfu:6)		02/07/21	RE\r\n"
		        + "28030	ECONOMIA E ORG. AZIENDALE T (cfu:6)		22/07/21	24\r\n"
		        + "28029	ELETTROTECNICA T (cfu:6)				02/09/21	26\r\n"
		        + "28014	FOND. DI TELECOMUNICAZIONI T (cfu:9)	15/09/21	30\r\n"
		        + "28020	SISTEMI OPERATIVI T (cfu:9)				12/01/22	24\r\n"
		        + "\r\n"
		        + "28015	CONTROLLI AUTOMATICI T (cfu:9)			13/01/21	25\r\n"
		        + "28016	ELETTRONICA T (cfu:6)					10/02/21	22\r\n"
		        + "28024	RETI DI CALCOLATORI T (cfu:9)			05/02/21	23\r\n"
		        + "28659	TECNOLOGIE WEB T (cfu:9)				12/06/21	25\r\n"
		        + "28021	INGEGNERIA DEL SOFTWARE T (cfu:9)		24/06/21	27\r\n"
		        + "17268	PROVA FINALE (cfu:3)					26/09/21	ID\r\n"
		        + "\r\n"
		        + "28074	TIROCINIO T (cfu:6)						27/09/21	ID\r\n"
		        + "\r\n"
		        + "88324	AMMINISTRAZIONE DI SISTEMI T (cfu:6)	13/07/21	29\r\n"
		        + "88325	LAB. SICUREZZA INFORMATICA T (cfu:6)	07/06/21	30L"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));
	}

	@Test
	void testKO_readerNull() throws IOException {
		assertThrows(IllegalArgumentException.class, () -> new MyCarrieraReader().leggiCarriera(null));
	}

	@Test
	void testKO_MissingIdInPrimaRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				 			  "ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
							+ "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
							+ "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
							+ "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
							+ "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
							+ "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
							+ "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
							+ "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
							+ "28011	RETI LOGICHE T (cfu:6)"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_MissingIdInRigheSuccessive() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_MissingNomeAttivitàInPrimaRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	(cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}

	@Test
	void testKO_MissingNomeAttivitàInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	  (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_MissingCfuInPrimaRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1					12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_MissingCfuWordInPrimaRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_MissingCfuWordAndColonInPrimaRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_MissingCfuNumberInPrimaRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_MissingCfuInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				        "27991	ANALISI MATEMATICA T-1				9		12/01/20	RT\r\n"
				      + "27991	ANALISI MATEMATICA T-1				9		10/02/20	22\r\n"
				      + "28004	FONDAMENTI DI INFORMATICA T-1		12		13/02/20	24\r\n"
				      + "29228	GEOMETRIA E ALGEBRA T						18/01/20	26\r\n"
				      + "26337	LINGUA INGLESE B-2					6\r\n"
				      + "27993	ANALISI MATEMATICA T-2				6		10/06/20	RE\r\n"
				      + "27993	ANALISI MATEMATICA T-2				6		02/07/20	RT\r\n"
				      + "28006	FONDAMENTI DI INFORMATICA T-2		12\r\n"
				      + "28011	RETI LOGICHE T						6"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_CfuwithDecimalPointInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6.5)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}

	@Test
	void testKO_IdNonNumericalInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				        "27991	ANALISI MATEMATICA T-1				9		12/01/20	RT\r\n"
				      + "27991	ANALISI MATEMATICA T-1				9		10/02/20	22\r\n"
				      + "28A28	FONDAMENTI DI INFORMATICA T-1		12		13/02/20	24\r\n"
				      + "29228	GEOMETRIA E ALGEBRA T				6		18/01/20	26\r\n"
				      + "26337	LINGUA INGLESE B-2					6\r\n"
				      + "27993	ANALISI MATEMATICA T-2				6		10/06/20	RE\r\n"
				      + "27993	ANALISI MATEMATICA T-2				6		02/07/20	RT\r\n"
				      + "28006	FONDAMENTI DI INFORMATICA T-2		12\r\n"
				      + "28011	RETI LOGICHE T						6"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}

	@Test
	void testKO_WrongCfuInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:x)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_DataErrataInAnnoInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/		26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}

	@Test
	void testKO_DataIllegaleInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/13/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}

	@Test
	void testKO_DataNegativaInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			-18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_VotoMinoreDi18InAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				        "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	16\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_VotoNegativoInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				        "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	-19\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}

	@Test
	void testKO_VotoZeroInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				        "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	0\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_VotoOltre30InAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				        "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	40\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_VotoConLodeNon30InAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				        "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24L\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_VotoRtErratoInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RIT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}

	@Test
	void testKO_VotoRtErrato2InAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				          "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	R T\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}

	@Test
	void testKO_VotoReErratoInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				        "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	xE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}
	
	@Test
	void testKO_VotoIdErratoInAltraRiga() throws IOException {
		StringReader fakeReader = new StringReader(
				        "27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT\r\n"
				        + "27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22\r\n"
				        + "28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24\r\n"
				        + "29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26\r\n"
				        + "26337	LINGUA INGLESE B-2 (cfu:6)				18/06/20	IDONEO\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE\r\n"
				        + "27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT\r\n"
				        + "28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)\r\n"
				        + "28011	RETI LOGICHE T (cfu:6)		"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyCarrieraReader().leggiCarriera(fakeReader));	
	}

}

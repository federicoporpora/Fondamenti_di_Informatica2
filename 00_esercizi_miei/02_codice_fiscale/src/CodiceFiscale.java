import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CodiceFiscale {
    public static String calcolaCodiceFiscale(String cognome, String nome, int giorno, int mese, int anno, String comune, char sesso) {
        String codiceFiscale = "";
        codiceFiscale += calcolaCognome(cognome);
        codiceFiscale += calcolaNome(nome);
        codiceFiscale += calcolaAnno(anno);
        codiceFiscale += calcolaMese(mese);
        codiceFiscale += calcolaGiornoSesso(giorno, sesso);
        if (calcolaComune(comune).equals("Non e' stato trovato il comune inserito")) return "Non e' stato trovato il comune inserito";
        codiceFiscale += calcolaComune(comune);
        codiceFiscale += calcolaCarControllo(codiceFiscale);
        return codiceFiscale;
    }
    private static boolean isConsonante(char c) {
        String vocali = "AEIOU";
        return vocali.indexOf(Character.toUpperCase(c)) == -1;
    }
    private static String calcolaCognome(String cognome) {
        String res = "";
        for (int i = 0; i < cognome.length(); i++) {
            if (isConsonante(cognome.charAt(i))) {
                res += cognome.charAt(i);
            }
            if (res.length() >= 3) break;
        }
        if (res.length() == 3) return res.toUpperCase();
        for (int i = 0; i < cognome.length(); i++) {
            if (!isConsonante(cognome.charAt(i))) {
                res += cognome.charAt(i);
            }
            if (res.length() >= 3) break;
        }
        while (res.length() < 3) {
            res += 'X';
        }
        return res.toUpperCase();
    }
    private static String calcolaNome(String nome) {
        String res = "";
        int contatore = 0, nConsonanti = 0;
        for (int i = 0; i < nome.length(); i++) {
            if (isConsonante(nome.charAt(i))) nConsonanti++;
        }
        if (nConsonanti > 3) {
            for (int i = 0; i < nome.length(); i++) { // se consonanti piu di 3
                if (isConsonante(nome.charAt(i))) {
                    if (contatore == 1) {
                        contatore++;
                        continue;
                    }
                    res += nome.charAt(i);
                    contatore++;
                }
                if (res.length() >= 3) break;
            }
        } else {
            for (int i = 0; i < nome.length(); i++) {
                if (isConsonante(nome.charAt(i))) {
                    res += nome.charAt(i);
                }
                if (res.length() >= 3) break;
            }
        }
        if (res.length() == 3) return res.toUpperCase();
        for (int i = 0; i < nome.length(); i++) {
            if (!isConsonante(nome.charAt(i))) {
                res += nome.charAt(i);
            }
            if (res.length() >= 3) break;
        }
        while (res.length() < 3) {
            res += 'X';
        }
        return res.toUpperCase();
    }
    private static String calcolaAnno(int anno) {
        return String.format("%02d", anno % 100);
    }
    private static char calcolaMese(int mese) {
        String mesi = "ABCDEHLMPRST";
        return mesi.charAt(mese - 1);
    }
    private static String calcolaGiornoSesso(int giorno, char sesso) {
        if (Character.toUpperCase(sesso) == 'F') return String.format("%02d", (giorno + 40));
        else return String.format("%02d", giorno);
    }
    private static String calcolaComune(String comune) {
        comune = comune.toUpperCase();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("00_esercizi_miei/02_codice_fiscale/puppa.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String comuneCorrente = parts[2].trim();
                    String codiceComune = parts[0].trim();

                    if (comuneCorrente.equals(comune)) {
                        reader.close();
                        return codiceComune;
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante la lettura del file.");
            e.printStackTrace();
        }

        // Se il comune non è stato trovato, restituisci una stringa vuota
        return "Non e' stato trovato il comune inserito";
    }
    private static char calcolaCarControllo(String codiceFiscale) {
        String codiceFiscaleDaAnalizzare = codiceFiscale;
        codiceFiscaleDaAnalizzare = codiceFiscaleDaAnalizzare.replaceAll("0", "A");
        codiceFiscaleDaAnalizzare = codiceFiscaleDaAnalizzare.replaceAll("1", "B");
        codiceFiscaleDaAnalizzare = codiceFiscaleDaAnalizzare.replaceAll("2", "C");
        codiceFiscaleDaAnalizzare = codiceFiscaleDaAnalizzare.replaceAll("3", "D");
        codiceFiscaleDaAnalizzare = codiceFiscaleDaAnalizzare.replaceAll("4", "E");
        codiceFiscaleDaAnalizzare = codiceFiscaleDaAnalizzare.replaceAll("5", "F");
        codiceFiscaleDaAnalizzare = codiceFiscaleDaAnalizzare.replaceAll("6", "G");
        codiceFiscaleDaAnalizzare = codiceFiscaleDaAnalizzare.replaceAll("7", "H");
        codiceFiscaleDaAnalizzare = codiceFiscaleDaAnalizzare.replaceAll("8", "I");
        codiceFiscaleDaAnalizzare = codiceFiscaleDaAnalizzare.replaceAll("9", "J");
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String valorePostoDispari = "BAKPLCQDREVOSFTGUHMINJWZYX";
        int somma = 0;
        for (int i = 0; i < codiceFiscaleDaAnalizzare.length(); i++) {
            if (i % 2 == 0) { //posto dispari (indice pari)
                // System.out.println("Lettera " + codiceFiscale.charAt(i) + " posto dispari, valore " + valorePostoDispari.indexOf(codiceFiscale.charAt(i)));
                somma += valorePostoDispari.indexOf(codiceFiscaleDaAnalizzare.charAt(i));
            } else { //posto pari
                // System.out.println("Lettera " + codiceFiscale.charAt(i) + " posto pari, valore " + alfabeto.indexOf(codiceFiscale.charAt(i)));
                somma += alfabeto.indexOf(codiceFiscaleDaAnalizzare.charAt(i));
            }
            // System.out.println("Somma attuale: " + somma);
        }
        somma %= 26;
        return alfabeto.charAt(somma);
    }
}

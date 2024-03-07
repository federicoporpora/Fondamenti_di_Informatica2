public class CodiceFiscale {
    // public String calcolaCodiceFiscale(String cognome, String nome, int giorno, int mese, int anno, String comune, char sesso) {

    // }
    // public boolean verificaCodiceFiscale(String cognome, String nome, int giorno, int mese, int anno, String comune, char sesso, String codiceDaVerificare) {

    // }
    private static boolean isConsonante(char c) {
        String vocali = "AEIOU";
        return vocali.indexOf(Character.toUpperCase(c)) == -1;
    }
    public static String calcolaCognome(String cognome) {
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
    public static String calcolaNome(String nome) {
        String res = "";
        int contatore = 0, nConsonanti = 0;
        for (int i = 0; i < nome.length(); i++) {
            if (isConsonante(nome.charAt(i))) nConsonanti++;
        }
        if (nConsonanti > 3) {
            for (int i = 0; i < nome.length(); i++) { // se consonanti piu di 3
                if (isConsonante(nome.charAt(i)) && contatore != 2) {
                    res += nome.charAt(i);
                }
                contatore++;
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
    public static String calcolaAnno(int anno) {
        return String.format("%02d", anno % 100);
    }
    public static char calcolaMese(int mese) {
        String mesi = "ABCDEHLMPRST";
        return mesi.charAt(mese - 1);
    }
    public static String calcolaGiornoSesso(int giorno, char sesso) {
        if (Character.toUpperCase(sesso) == 'M') return String.format("%02d", giorno);
        else return String.format("%02d", giorno + 40);
    }
}
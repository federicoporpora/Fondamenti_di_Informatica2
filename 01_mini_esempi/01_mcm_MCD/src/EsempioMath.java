/**
* Cliente che calcola mcd o mcm fra due numeri passati da riga di comando
* utilizzando la libreria MyMath
* @author Fondamenti di Informatica T-2
* @version 1.0 02/2024
*/
public class EsempioMath {
/** Calcola mcd o mcm dei due numeri passati da linea di comando
*/
	public static void main(String args[]) {
		if (args[0].equals("mcm")) {
			int a = Integer.parseInt(args[1]);
			int b = Integer.parseInt(args[2]);
			int mcm = MyMath.mcm(a,b);
			System.out.println(mcm);
		}
		else if (args[0].equals("MCD")) {
			int a = Integer.parseInt(args[1]);
			int b = Integer.parseInt(args[2]);
			int mcd = MyMath.mcd(a,b);
			System.out.println(mcd);
		}
		else {
			System.out.println("Input non corretto, devi scrivere 'MCD' oppure 'mcm'");
		}
	}
}
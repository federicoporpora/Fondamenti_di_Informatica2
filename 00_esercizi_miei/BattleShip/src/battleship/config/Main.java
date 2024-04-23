package battleship.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import battleship.model.Board;
import battleship.model.Pos;
import battleship.model.Ship;
import battleship.model.Orientation;
import battleship.model.ShipType;


public class Main  {

	public static void main(String[] args) {

		var board = new Board(10, new Ship[] {
				new Ship(ShipType.PORTAEREI, new Pos(1,7), Orientation.HORIZONTAL, "portaerei1"),
				new Ship(ShipType.PORTAEREI, new Pos(3,1), Orientation.VERTICAL, "portaerei2"),
				new Ship(ShipType.INCROCIATORE, new Pos(1,4), Orientation.VERTICAL, "incrociatore1"),
				new Ship(ShipType.INCROCIATORE, new Pos(3,6), Orientation.HORIZONTAL, "incrociatore2"),
				new Ship(ShipType.INCROCIATORE, new Pos(10,6), Orientation.HORIZONTAL, "incrociatore3"),			
				new Ship(ShipType.INCROCIATORE, new Pos(6,10), Orientation.VERTICAL, "incrociatore4"),			
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(1,1), Orientation.HORIZONTAL, "cacciatorpediniere1"),
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(8,1), Orientation.HORIZONTAL, "cacciatorpediniere2"),
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(6,6), Orientation.VERTICAL, "cacciatorpediniere3"),
				new Ship(ShipType.SOMMERGIBILE, new Pos(5,4), Orientation.HORIZONTAL, "sommergibile1"),
				new Ship(ShipType.SOMMERGIBILE, new Pos(5,8), Orientation.HORIZONTAL, "sommergibile2"),
				new Ship(ShipType.SOMMERGIBILE, new Pos(10,2), Orientation.HORIZONTAL, "sommergibile3")
		});
		
		System.out.println("Configurato campo di gioco con " + board.getShipNumber() + " navi:");
		
		int tentativi=0;
		while(board.getShipNumber()>0) {
			System.out.print("Restano " + board.getShipNumber() + " navi. ");
			System.out.print("La tua mossa? ");
			var mossa = readLine();
			if(mossa.equalsIgnoreCase("END")) {
				System.out.println(board.revealAsMap());
				System.exit(0);
			}
			tentativi++;
			System.out.println( board.fire(new Pos(mossa)) );
			System.out.println(board);
		}
		System.out.println("Hai vinto in " + tentativi + " tentativi.");
		System.out.println(board.revealAsMap());
		
	}
	
	private static String readLine() {
		try {
			return reader.readLine().toUpperCase().trim();
		}
		catch(IOException e) {
			System.err.println("Errore di lettura imprevisto");
			System.exit(1);
			return null; // fake, unreachable code
		}
	}
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
	
}

package crosswords.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class Scheme {

	private int[][] board;
	private int size;
	
	public Scheme(int size) {
		if (size<1) throw new IllegalArgumentException("Schema: size<1");
		board = new int[size][size];
		Arrays.stream(board).forEach(arr -> Arrays.fill(arr, -1));
		this.size = size;
	}
	
	public void setCellRow(int row, int[] numValues) {
		Objects.requireNonNull(numValues, "Array nullo");
		if (numValues.length!=size) throw new IllegalArgumentException("Riga di lunghezza inconsistente: " + numValues.length + ", doveva essere " + size);
		if (row<0 || row>=size) throw new IllegalArgumentException("Indice di riga fuori range: " + row);
		IntStream.of(numValues).forEach(n -> {if(n<0 || n>26) throw new IllegalArgumentException("Numero da assegnare fuori range [1-26]: " + n);});
		// might throw NPE if the referenced board cell is null
		for (int col=0; col<size; col++) {
			board[row][col] = numValues[col]; 
		}
	}
	
	public int[] getCellRow(int row){
		if (row<0 || row>=size) throw new IllegalArgumentException("Indice di riga fuori range: " + row);
		return board[row];
	}
	
	
	public int getCell(int row, int col) {
		if (row<0 || row>=size) throw new IllegalArgumentException("Indice di riga fuori range: " + row);
		if (col<0 || col>=size) throw new IllegalArgumentException("Indice di colonna fuori range: " + col);
		return board[row][col];
	}

	public int getSize() {
		return size;
	}

	public boolean isConfigured() {
		for (int row=0; row<size; row++)
			for (int col=0; col<size; col++)
				if(board[row][col]==-1) return false;
		return true;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int k=0; k<size*size; k++) {
			sb.append(board[k/size][k%size]);
			sb.append(k%size==size-1 ? System.lineSeparator() : '\t');
		}
		return sb.toString();
	}
	
}
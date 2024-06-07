package dentburger.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

import dentburger.model.Categoria;
import dentburger.model.Menu;
import dentburger.model.Ordine;
import dentburger.model.Prodotto;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Controller {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	private Menu menu;
	private Ordine ordine;
	
	//--------------
	
	public Controller(Menu menu) {
		if(menu==null) throw new IllegalArgumentException("Menu nullo nel construttore del Controller!");
		this.menu = menu;
		this.ordine = new Ordine(Collections.emptyList(), LocalDateTime.now());
	}

	public Menu getMenu() {
		return menu;
	}
	
	public Ordine getOrdine() {
		return ordine;
	}
	
	public List<Categoria> getCategorie(){
		return Stream.of(Categoria.values()).sorted(Comparator.comparing(Categoria::toString)).toList();
	}
	
	public List<String> getGeneriPerCategoria(Categoria categoria){
		return menu.getGeneriPerCategoria(categoria).stream().sorted().toList();
	}
	
	public List<Prodotto> getProdottiPerGenereCategoria(Categoria categoria, String genere){
		return new ArrayList<>(menu.getProdottiPerCategoriaEGenere(categoria, genere));
		//NB: necessario incapsulare il risultato in una lista modificabile per evitare 
		//    eccezioni NPE nelle grafica, in quant le combo presuppongono liste modificabili
	}
	
	public void aggiungiProdotto(Prodotto p) {
		ordine.aggiungi(p);
	}

	public boolean rimuoviProdotto(Prodotto p) {
		return ordine.rimuovi(p);
	}
}

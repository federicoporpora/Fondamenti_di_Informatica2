package dentburger.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.HashMap;

/** Rappresenta il menù del ristorante, strutturato come mappa di mappe che gestiscono liste di prodotti. 
 *  Più precisamente, il menù è organizzato come mappa indicizzata per Categoria: a ogni categoria è 
 *  associata un'ulteriore mappa, indicizzata per genere (stringa), che associa a ogni genere la lista 
 *  dei Prodotto di quella categoria e genere (vedere figura nel testo)
 */
public class Menu {

	private Map<Categoria, Map<String,List<Prodotto>> > menu;
	
	/** Il costruttore istanzia la mappa vuota: è compito del metodo add popolarla via via che vengono 
	 *  aggiunti prodotti, curando la costruzione delle mappe di secondo livello e delle liste quando 
	 *  necessario. 
	 */
	public Menu() {
		menu = new HashMap<>();
	}
	
	public void add(Prodotto p) { //da rivedere
		Map<String,List<Prodotto>> prodottiDiQuellaCategoria = menu.get(p.getCategoria());
		if(prodottiDiQuellaCategoria==null) {
			prodottiDiQuellaCategoria = new HashMap<>();
			menu.put(p.getCategoria(), prodottiDiQuellaCategoria);
		}
		List<Prodotto> prodottiDelGiustoTipo = prodottiDiQuellaCategoria.get(p.getGenere());
		if (prodottiDelGiustoTipo==null) {
			prodottiDelGiustoTipo = new ArrayList<>();
			prodottiDiQuellaCategoria.put(p.getGenere(), prodottiDelGiustoTipo);
		}
		if (prodottiDelGiustoTipo.contains(p)) throw new IllegalArgumentException("Prodotto già esistente: " + p);
		else prodottiDelGiustoTipo.add(p);
	}
	
	private Map<String,List<Prodotto>> getProdottiPerCategoria(Categoria c){
		return Map.copyOf(menu.getOrDefault(c, Collections.emptyMap()));
	}
	
	public Set<String> getGeneriPerCategoria(Categoria c){
		if (menu.containsKey(c)) return Set.copyOf(menu.get(c).keySet());
		return Collections.emptySet();
	}
	
	public List<Prodotto> getProdottiPerCategoriaEGenere(Categoria c, String denominazione){
		if (getGeneriPerCategoria(c).contains(denominazione)) return List.copyOf(menu.get(c).get(denominazione));
		return Collections.emptyList();
	}
	
	@Override
	public String toString() { 
		return menu.keySet().stream()
							.map(this::getProdottiPerCategoria)
							.map(Map::entrySet)
							.flatMap(Set::stream)
							.map(entry -> entry.getKey() + " " + entry.getValue().toString())
							.collect(Collectors.joining(System.lineSeparator()));
	}
	
}

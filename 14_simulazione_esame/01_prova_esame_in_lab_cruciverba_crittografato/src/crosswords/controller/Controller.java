package crosswords.controller;

import java.util.Objects;

import crosswords.model.Game;
import crosswords.model.MyGame;
import crosswords.model.Scheme;


public class Controller {
	private MyGame game;

	public Controller(int size) {
		if (size<1) throw new IllegalArgumentException("Controller size cannot be less than 1");
		this.game = new MyGame(size);
	}

	public Controller(Scheme s)	{
		Objects.requireNonNull(s, "Controller constructor: scheme cannot be null");
		this.game = new MyGame(s.getSize());
		for (int i = 0; i<s.getSize(); i++)
			game.setCellRow(i,s.getCellRow(i));
	}

	public int getSize() {
		return game.getSize();
	}
	
	public Game getGame() {
		return game;
	}

	public void updateMapping(int numericalValue, String letter) {
		game.setMapping(numericalValue, letter);
	}
	
}

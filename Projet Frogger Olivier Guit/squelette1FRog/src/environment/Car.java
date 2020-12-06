package environment;

import java.awt.Color;


import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.RED;
	private final Color colorRtL = Color.BLUE;

	//Constructeur

	public Car(Game game, Case frontPosition, boolean leftToRight) {
		this.game = game;
		this.length = game.randomGen.nextInt(3) + 1;
		this.leftToRight = leftToRight;
		this.leftPosition = new Case(leftToRight ? (frontPosition.absc - this.length) : frontPosition.absc, frontPosition.ord);
	}
	
	//Methodes

	public void move(boolean move) {
		if (move)
			this.leftPosition = new Case(this.leftPosition.absc + (this.leftToRight ? 1 : -1), this.leftPosition.ord);
		addToGraphics();
	}
	public boolean appearsInBounds() {
		return !(this.leftPosition.absc + this.length <= 0 && this.leftPosition.absc >= this.game.width);
	}

	public boolean Casefull(Case pos) {
		if (pos.ord != this.leftPosition.ord)
			return false;
		return (pos.absc >= this.leftPosition.absc && pos.absc < this.leftPosition.absc + this.length);
	}


	
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}

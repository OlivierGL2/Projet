package environment;

import java.util.ArrayList;

import gameCommons.Game;
import util.Case;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<environment.Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int timer;

	//Constructeur

	public Lane(Game game, int ord, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = density;
	}


	public void update() {
		this.timer++;
		if (this.timer <= this.speed) {
			moveCars(false);
			return;
		}
		moveCars(true);
		mayAddCar();
		this.timer = 0;
	}

		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e



	// Methode

	public boolean isSafe(Case pos) {
		for (environment.Car car : this.cars) {
			if (car.Casefull(pos))
				return false;
		}
		return true;
	}
	private void moveCars(boolean b) {
		for (environment.Car car : this.cars)
			car.move(b);
		removeOldCars();
	}

	private void removeOldCars() {
		ArrayList<environment.Car> toBeRemoved = new ArrayList<>();
		for (environment.Car c : this.cars) {
			if (!c.appearsInBounds())
				toBeRemoved.add(c);
		}
		for (environment.Car c : toBeRemoved)
			this.cars.remove(c);
	}

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new environment.Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}

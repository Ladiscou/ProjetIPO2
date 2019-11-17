package environment;

import java.util.ArrayList;
import java.util.Iterator;

import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int tic;
	private Environment environment;

	//Constructeur(s)
	
	public Lane (Game game, int ord, boolean sens, double density, Environment environment){
		this.game = game;
		this.ord = ord;
		int alea = 1 + (int)(Math.random() * ((3 - 1) + 1));
		this.speed = alea;
		this.leftToRight = sens;
		this.density = density;
		this.tic = this.speed;
		this.environment = environment;
		
	}

	public void update() {
		this.tic -= 1;
		if(this.tic == 0){
			Iterator<Car> iter = cars.iterator();
			while(iter.hasNext()){
				Car vroum = iter.next();
				vroum.deplaceCar();
				}
			this.tic = this.speed;
			}
		this.mayAddCar();
		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e

	}

    public ArrayList<Car> rvCar(){
	    return this.cars;
    }

	// TODO : ajout de methodes

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (this.environment.isSafe(getFirstCase()) && this.environment.isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
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

package environment;

import java.util.ArrayList;
import java.util.Iterator;

import gameCommons.Game;
import gameCommons.IEnvironment;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int tic;
	private IEnvironment environment;

	//Constructeur(s)
	
	public Lane (Game game, int ord, boolean sens, double density, IEnvironment environment){
		this.game = game;
		this.ord = ord;
		int alea = 2 + (int)(Math.random() * ((4 - 2) + 1));
		this.speed = alea;
		this.leftToRight = sens;
		this.density = density;
		this.tic = this.speed;
		this.environment = environment;
		
	}



	// TODO : ajout de methodes

	/**
	 * fonction qui met a jour l'ordonnée des voitures en fonction de l'ordonnée de la ligne
	 */
	public void majCars(){
		Iterator<Car> iter = cars.iterator();
		while(iter.hasNext()){
			iter.next().modifOrd(this.ord);
		}
	}

	/**
	 * fonction qui augmente de 1 l'ordonnée de la ligne si sens est true et diminue sinon
	 * met aussi a jour l'ordonnée des voitures avec
	 * @param sens
	 */
	public void majOrd(boolean sens){
		if(sens){
			this.ord ++;
			majCars();
		}
		else{
			this.ord --;
			majCars();
		}
	}


	// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
	// d'horloge" �gal � leur vitesse
	// Notez que cette m�thode est appel�e � chaque tic d'horloge

	// Les voitures doivent etre ajoutes a l interface graphique meme quand
	// elle ne bougent pas

	// A chaque tic d'horloge, une voiture peut �tre ajout�e

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
		Iterator<Car> iter2 = cars.iterator();
		while(iter2.hasNext()){
			Car vroum = iter2.next();
			vroum.affichage();
		}
	}

	/**
	 * fonction getter
	 * @return le tabeau de voiture de la ligne
	 */
	public ArrayList<Car> rvCar(){
		return this.cars;
	}


	/**
	 * fonction getter
	 * @return l'ordonnée associée a la ligne
	 */
	public int pos(){
		return this.ord;
	}

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
			return new Case(-3, ord);
		} else
			return new Case(game.width, ord);
	}

}

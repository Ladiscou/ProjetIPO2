package environment;

import java.awt.Color;

import gameCommons.Game;
import graphicalElements.Element;
import environment.Case;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	//Constructeur(s)
	 public Car(Game game, Case cas, boolean sens) {
		 this.game = game;
		 this.leftPosition = cas;
		 this.leftToRight = sens;
		 int nombreAleatoire = 1 + (int)(Math.random() * ((3 - 1) + 1));
		 this.length = nombreAleatoire;
	 }
	
	//TODO : ajout de methodes
	public Case pos(){
	 	return this.leftPosition;
	}

	public int size(){
	 	return this.length;
	}

    public void deplaceCar() {
    	if(this.leftToRight) {
    		this.leftPosition= new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
    	}
    	else {
    		this.leftPosition= new Case(this.leftPosition.absc - 1, this.leftPosition.ord);
    	}
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

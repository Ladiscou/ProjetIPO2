package environment;

import java.util.ArrayList;

import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
		
	private ArrayList<Lane> line = new ArrayList<>();
	private Game game;

	public Environment(Game game) {
		this.game = game;
		boolean sens;
		for (int i = 0; i < game.height; i ++) {
			//Crée un nb aléatoire entre 0 et 1 -> met false ou true pour connaitre le sens de la ligne
			int alea = 0 + (int)(Math.random() * ((1 - 0) + 1));
			if (alea == 0) {
				sens =true;
			} else {
				sens  = false;
			}
			//crée une ligne
			Lane ligne = new Lane (game, i, sens, game.defaultDensity);
			//add une ligne ds Array List. addLane
			this.line.add(ligne);
		} 
	}

}

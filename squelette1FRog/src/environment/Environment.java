package environment;

import java.util.ArrayList;
import java.util.Iterator;

import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
		
	private ArrayList<Lane> line = new ArrayList<>();
	private Game game;
	/**
	 * constructeur
	 * @param game
	 */
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
			Lane ligne = new Lane (game, i, sens, game.defaultDensity, this);
			//add une ligne ds Array List. addLane
			this.line.add(ligne);
		} 
	}
	
	//Méthodes

	/**
	 * fonction booleene qui nous indique si la case C est un position de victoire
	 * @param c
	 * @return vrai si on la case est gagnante faux sinon
	 */
	public boolean isWinningPosition(Case c) {
		//regarde si l'ordonnée est égal la hauteur-1
		if (c.ord == game.height-1) {
			return true;
		}
		return false;
	}

	/**
	 * fonction qui prend un case e parametre et indique si la case peux faire perdre la partie
	 * @param c
	 * @return vrai si etre sur la case provoque la perte de la partie faux sinon
	 */
	public boolean isSafe(Case c){
        Lane here = this.line.get(c.ord);
        Iterator<Car> iter = here.rvCar().iterator();
        while(iter.hasNext()){
            Car vroom = iter.next();
            int distance = c.absc - vroom.pos().absc;
            if(distance >= 0) {
                if (vroom.size() > distance) {
                    return false;
                }
            }
        }
        return true;
    }

	/**
	 * fonction qui met a jour toutes les ligne du tableau de jeu
	 */
	public void update() {
		Iterator<Lane> iter = line.iterator();
		iter.next();
		while(iter.hasNext()){
			Lane lg = iter.next();
			if(lg.pos() < (this.game.height - 1)){
				lg.update();
			}
		}
	}



}

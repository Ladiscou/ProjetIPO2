package environment;

import java.util.ArrayList;
import java.util.Iterator;

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
			Lane ligne = new Lane (game, i, sens, game.defaultDensity, this);
			//add une ligne ds Array List. addLane
			this.line.add(ligne);
		} 
	}
	
	//Méthodes
	public boolean isWinningPosition(Case c) {
		//regarde si l'ordonnée est égal la hauteur-1
		if (c.ord == game.height-1) {
			return true;
		}
		return false;
	}

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

    public void update() {
		Iterator<Lane> iter = line.iterator();
		while(iter.hasNext()){
			Lane lg = iter.next();
			lg.update();
		}
	}

}

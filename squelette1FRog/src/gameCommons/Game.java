package gameCommons;

import java.awt.Color;
import java.util.Random;

import environment.Case;
import environment.EnvInf;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	public long time;
	public int gagne;
	public int perdu;
	// Lien aux objets utilises
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;
	private int score;
	private int realScore;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant deplacement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.score =0;
		this.realScore = 0;
		this.time = System.currentTimeMillis();
		this.gagne = 0;
		this.perdu = 0;
	}

	/**
	 * Lie l'objet frog a la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * fonction getter
	 * @return le score actuel
	 */
	public int score(){
		return this.realScore;
	}

	 public void incrScore(boolean sens){
		    if(sens){
	            this.score++;
	        }
		    else{
		        this.score--;
	        }
	        if(this.score > this.realScore){
	            this.realScore = this.score;
	        }
	    }
	
	/**
	 * Teste si la partie est perdue et lance un ecran de fin approprie si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		Case pos = this.frog.getPosition();
		if(this.environment.isSafe(pos)) {
		   return false;
	    }
		if (this.perdu == 0) {
			String scor = Integer.toString(this.score);
			long tps = (long) ((System.currentTimeMillis()-this.time)*Math.pow(10, -3));
			this.graphic.endGameScreen("Perdu votre score est : " + scor + "  + tps : " + tps + " s" );
			this.perdu ++;
		}
		return true;
	}

	/**
	 * Teste si la partie est gagnee et lance un ecran de fin approprie si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagne
	 */
	public boolean testWin() {
		Case pos = this.frog.getPosition();
		if(this.environment.isWinningPosition(pos)) {
			if (this.gagne == 0) {
				long tps = (long) ((System.currentTimeMillis()-this.time)*Math.pow(10, -3));
				this.graphic.endGameScreen("Gagné en " + tps + " s" );
				this.gagne ++;
			}
			return true;
		}
		return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		testLose();
		testWin();
	}

}

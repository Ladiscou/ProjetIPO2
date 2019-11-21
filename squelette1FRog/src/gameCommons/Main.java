package gameCommons;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import environment.Case;
import environment.EnvInf;
import frog.Frog;
import frog.FrogInf;
import givenEnvironment.GivenEnvironment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import environment.Environment;

public class Main {

	public static void main(String[] args) {

		//Caract�ristiques du jeu
		int width = 26;
		int height = 20;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.02;
		int depart = 2;
		
		//Cr�ation de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Cr�ation de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
		
		//IEnvironment env = new GivenEnvironment(game);
		
		//Point2
		//Creation et liaison de l'environnement
		//IEnvironment env = new Environment(game);
		//IFrog frog = new Frog(game);
		
		//Point3
		//Creation et liaison de l'environnement
		IEnvironment env = new EnvInf(game, depart);
		IFrog frog = new FrogInf(game, (EnvInf) env, depart);
		
		//Creation et liason de la grenouille
		game.setEnvironment(env);
		game.setFrog(frog);
		graphic.setFrog(frog);
				
		//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}

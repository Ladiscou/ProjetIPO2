package frog;

import java.util.ArrayList;

import environment.Case;
import gameCommons.Game;

import gameCommons.IFrog;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private int x;
	private int y;
	private Direction direction;

	//Constructeurs
	public Frog (Game game) {
		this.game = game;
		this.x = game.width/2;
		this.y = 0;							
	}
	
	//Méthodes
	public Case getPosition() {
		Case pos = new Case(this.x, this.y);
		return pos;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void move(Direction key) {
		this.direction = key;
		switch(key) {
		case up:
			if(this.y < game.height - 1){
			   this.y += 1;
			}
		break;
		case down:
			if(this.y > 0) {
			   this.y -= 1;
			}
		break;
		case right:
			if(this.x < game.width - 1) {
			   this.x += 1;
			}
		break;
		case left:
			if(this.x > 0) {
			   this.x -= 1;
			}
		break;
		}
		game.testLose();
		game.testWin();
	}

}

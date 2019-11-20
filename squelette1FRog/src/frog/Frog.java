package frog;

import java.util.ArrayList;

import environment.Case;
import gameCommons.Game;

import gameCommons.IFrog;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private Case pos;
	private Direction direction;

	//Constructeurs
	public Frog (Game game) {
		this.game = game;
		this.pos = new Case(game.width/2, 0);
	}
	
	//Méthodes
	public Case getPosition() {
		return pos;
	}
	
	public Direction getDirection() {

		return this.direction;
	}
	public void move(Direction key){
		this.direction = key;
		switch(key) {
			case up:
				if(this.pos.ord < game.height - 1){
					this.pos = new Case(this.pos.absc, this.pos.ord + 1);
				}
				break;
			case down:
				if(this.pos.ord > 0) {
					this.pos = new Case(this.pos.absc, this.pos.ord - 1);
				}
				break;
			case right:
				if(this.pos.absc < game.width - 1) {
					this.pos = new Case(this.pos.absc + 1, this.pos.ord);
				}
				break;
			case left:
				if(this.pos.absc > 0) {
					this.pos = new Case(this.pos.absc - 1, this.pos.ord);
				}
				break;
		}
		game.testLose();
		game.testWin();
	}
}

/**
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
 **/


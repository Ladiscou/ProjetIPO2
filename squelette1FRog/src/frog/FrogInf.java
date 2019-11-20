package frog;

import environment.Case;
import environment.EnvInf;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;
import util.Direction;

public class FrogInf implements IFrog {
    private Game game;
    private Case pos;
    private Direction direction;
    private EnvInf environment;
    private int depart;

    //constructeur
    public FrogInf (Game game, EnvInf environment, int depart) {
        this.depart = depart;
        this.game = game;
        this.pos = new Case(game.width/2, depart);
        this.environment = environment;
    }


    public Case getPosition() {
        return pos;
    }


    public Direction getDirection() {
        return direction;
    }

    public void move(Direction key){
        this.direction = key;
        switch(key) {
            case up:
                    this.environment.majLineUp();
                break;
            case down:
                this.environment.majLineDown();
                break;
            case right:
                if(this.pos.absc < game.width - 1) {
                    this.pos = new Case(this.pos.absc + 1, this.pos.ord);
                }
                break;
            case left:
                if(this.pos.absc > 0) {
                    this.pos = new Case(this.pos.absc -1, this.pos.ord);
                }
                break;
        }
        game.testLose();
        game.testWin();
    }
}

package environment;

import frog.FrogInf;
import gameCommons.Game;
import gameCommons.IEnvironment;

import java.util.ArrayList;
import java.util.Iterator;

public class EnvInf implements IEnvironment {
    private ArrayList<Lane> line = new ArrayList<>();
    private Game game;
    public int score;
    private int depart;

   // constructeur
    public EnvInf(Game game, int depart) {
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
        this.depart = depart;
    }




    public boolean isSafe(Case c) {
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

    @Override
    public boolean isWinningPosition(Case c) {
        return false;
    }

    /**
     * fonction qui met a jour les ligne du jeux quand la grenouille avance
     */
    public void majLineUp() {
        boolean sens;
        int alea = 0 + (int) (Math.random() * ((1 - 0) + 1));
        if (alea == 0) {
            sens = true;
        } else {
            sens = false;
        }
        Lane up = new Lane(this.game, game.height - 1, sens, game.defaultDensity, this);
        line.remove(0);
        Iterator<Lane> iter = line.iterator();
        while (iter.hasNext()){
            iter.next().majOrd(false);

        }
        line.add(up);
        this.score++;

    }

    /**
     *fonction qui met a jour les lignes de jeux quand la grenouille recule
     */
    public void majLineDown(){
        boolean sens;
        int alea = 0 + (int) (Math.random() * ((1 - 0) + 1));
        if(alea == 0){
            sens = true;
        }
        else{
            sens = false;
        }
        Lane down = new Lane(this.game, 0, sens, game.defaultDensity, this);
        Iterator<Lane> iter = line.iterator();
        while(iter.hasNext()){
            iter.next().majOrd(true);
        }
        line.add(0, down);
        line.remove(game.height-1);
    }


    /**
     * fonction qui met a jour les ligne du jeux
     */
    public void update() {
        Iterator<Lane> iter = line.iterator();
        if(this.score == 0){
            for(int i = 0; i <= this.depart; i++){
                iter.next();
            }
        }
        while(iter.hasNext()){
            Lane lg = iter.next();
            if(lg.pos() < (this.game.height)){
                lg.update();
            }
        }
        if(this.score%10 == 0){
            System.out.println("vous avez atteint le score de :"+ this.score);
        }
    }
}

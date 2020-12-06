package environment;

import java.util.ArrayList;

import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

public class Environment implements IEnvironment {

    //Attribut

    private Game game;
    private ArrayList<environment.Lane> roadLines;

    //Constructeur

    public Environment(Game game) {
        this.game = game;
        this.roadLines = new ArrayList<>();
        this.roadLines.add(new environment.Lane(game, 0, 0.0D));
        for (int i = 1; i < game.height - 1; i++)
            this.roadLines.add(new Lane(game, i, 1));
        this.roadLines.add(new environment.Lane(game, game.height - 1, 0.0D));
    }

    //Methode

    public boolean isSafe(Case c) {

        return ((environment.Lane) this.roadLines.get(c.ord)).isSafe(c);
    }

    public boolean isWinningPosition(Case c) {
        return (c.ord == this.game.height - 1);
    }

    public void update() {
        for (environment.Lane lane : this.roadLines)
            lane.update();
    }
}




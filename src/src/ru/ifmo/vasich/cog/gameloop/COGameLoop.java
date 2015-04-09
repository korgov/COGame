package src.ru.ifmo.vasich.cog.gameloop;

import src.ru.ifmo.vasich.cog.gamestate.COStateManager;
import src.ru.ifmo.vasich.ge.window.GameLoop;

public class COGameLoop extends GameLoop {

    public COGameLoop() {
        super(new COStateManager());
    }
}

package src.ru.ifmo.vasich.cog.main;

import src.ru.ifmo.vasich.cog.gamestate.level.LevelForOnePlayer;
import src.ru.ifmo.vasich.cog.gamestate.level.LevelForTwoPlayers;
import src.ru.ifmo.vasich.cog.gamestate.menu.COStartMenu;
import src.ru.ifmo.vasich.ge.gamestate.GameStateManager;

public class COStateManager extends GameStateManager {

    public COStateManager() {
        states.put("Menu", new COStartMenu(this));
        states.put("Level 1", new LevelForTwoPlayers(this));
    }

    @Override
    public void init() {
        currentState = states.get("Menu");
    }
}

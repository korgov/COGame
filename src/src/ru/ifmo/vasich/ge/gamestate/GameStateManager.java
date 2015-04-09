package src.ru.ifmo.vasich.ge.gamestate;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public abstract class GameStateManager implements KeyListener {

    protected Map<String, GameState> states;
    protected GameState currentState;

    public GameStateManager() {
        states = new HashMap<String, GameState>();
        init();
    }

    public abstract void init();

    public void setCurrentState(String stateName) {
        currentState = states.get(stateName);
    }

    public void tick() {
        currentState.tick();
    }

    public void render(Graphics2D g2) {
        currentState.render(g2);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        currentState.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentState.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentState.keyReleased(e);
    }
}

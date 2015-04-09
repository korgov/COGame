package src.ru.ifmo.vasich.ge.state;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class GameState {

    protected String nextState;
    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public GameStateManager getGsm() {
        return gsm;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }

    public void tick() {
        if (nextState != null) {
            gsm.setCurrentState(nextState);
        }
        nextState = null;
    }

    public abstract void render(Graphics2D g2);

    public abstract void keyTyped(KeyEvent e);

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);
}

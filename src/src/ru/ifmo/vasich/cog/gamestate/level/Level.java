package src.ru.ifmo.vasich.cog.gamestate.level;

import src.ru.ifmo.vasich.cog.entity.Player;
import src.ru.ifmo.vasich.cog.inputhandling.moving.MoveCommand;
import src.ru.ifmo.vasich.cog.inputhandling.moving.MoveInputHandler;
import src.ru.ifmo.vasich.ge.state.GameState;
import src.ru.ifmo.vasich.ge.state.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Level extends GameState {

    protected ArrayList<Player> players;
    protected ArrayList<GameScreen> screens;
    protected ArrayList<MoveInputHandler> inputHandlers;

    protected BufferedImage background;

    public Level(GameStateManager gsm) {
        super(gsm);
        players = new ArrayList<Player>();
        screens = new ArrayList<GameScreen>();
        inputHandlers = new ArrayList<MoveInputHandler>();
        setBackground();
        initScreens();
    }

    abstract void setBackground();

    abstract void initScreens();

    @Override
    public void tick() {
        super.tick();
        for (GameScreen screen : screens) {
            screen.tick();
        }
    }

    @Override
    public void render(Graphics2D g2) {
        for (GameScreen screen : screens) {
            screen.render(g2);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setNextState("Menu");
        }
        for (GameScreen screen : screens) {
            MoveCommand c = screen.getInputHandler().handleInput(e.getKeyCode() + " pressed");
            if (c != null) {
                c.move(screen.getPlayer());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (GameScreen screen : screens) {
            MoveCommand c = screen.getInputHandler().handleInput(e.getKeyCode() + " released");
            if (c != null) {
                c.move(screen.getPlayer());
            }
        }
    }
}

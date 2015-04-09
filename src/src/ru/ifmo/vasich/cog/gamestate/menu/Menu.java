package src.ru.ifmo.vasich.cog.gamestate.menu;

import src.ru.ifmo.vasich.ge.state.GameState;
import src.ru.ifmo.vasich.ge.state.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class Menu extends GameState {
    protected ArrayList<MenuOption> options;
    private MenuOption currentOption;
    protected Color backgroundColor;

    public Menu(GameStateManager gsm) {
        super(gsm);
        options = new ArrayList<MenuOption>();
    }

    @Override
    public void render(Graphics2D g2) {

        int x = upperLeftX(g2);
        int y = upperLeftY(g2);
        int line = lineWidth();

        g2.setColor(backgroundColor);
        g2.fill(g2.getClipBounds());

        for (MenuOption option : options) {
            option.render(g2, x, y + line * options.indexOf(option));
        }
    }

    protected abstract int lineWidth();

    protected abstract int upperLeftY(Graphics2D g2);

    protected abstract int upperLeftX(Graphics2D g2);

    @Override
    public void keyPressed(KeyEvent e) {
        currentOption.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentOption.keyReleased(e);
    }

    public MenuOption getCurrentOption() {
        return currentOption;
    }

    public void setCurrentOption(MenuOption currentOption) {

        if (this.currentOption != null) {
            this.currentOption.setActive(false);
        }
        this.currentOption = currentOption;
        currentOption.setActive(true);
    }
}

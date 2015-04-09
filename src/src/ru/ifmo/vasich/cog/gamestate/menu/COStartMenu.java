package src.ru.ifmo.vasich.cog.gamestate.menu;

import src.ru.ifmo.vasich.ge.state.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class COStartMenu extends Menu {
    private String title;
    private Font titleFont;
    private Color titleColor;

    public COStartMenu(GameStateManager gsm) {
        super(gsm);

        backgroundColor = StartMenuSettings.backgroundColor;
        titleFont = StartMenuSettings.titleFont;
        titleColor = StartMenuSettings.titleColor;
        title = "Camera obscura";

        options.add(new OptionStart(this));
        options.add(new OptionExit(this));
    }

    @Override
    protected int lineWidth() {
        return StartMenuSettings.interlinear;
    }

    @Override
    protected int upperLeftY(Graphics2D g2) {
        return (int) (g2.getClipBounds().height - lineWidth() * (options.size() + 0.5));
    }

    @Override
    protected int upperLeftX(Graphics2D g2) {
        return g2.getClipBounds().width - 200;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void render(Graphics2D g2) {
        super.render(g2);
        g2.setFont(titleFont);
        g2.setColor(titleColor);
        g2.drawString(title, 100, 100);
    }
}

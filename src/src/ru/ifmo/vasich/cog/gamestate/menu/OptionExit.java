package src.ru.ifmo.vasich.cog.gamestate.menu;

import src.ru.ifmo.vasich.ge.gamestate.menu.MenuOption;

import java.awt.*;
import java.awt.event.KeyEvent;

class OptionExit extends MenuOption {

    public OptionExit(COStartMenu holder) {
        super("Exit", holder);

        passiveColor = StartMenuSettings.passiveOptionColor;
        passiveFont = StartMenuSettings.passiveOptionFont;

        activeColor = StartMenuSettings.activeOptionColor;
        activeFont = StartMenuSettings.activeOptionFont;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void render(Graphics2D g2, int x, int y) {
        super.render(g2, x, y);
        if (isActive) {

            float scale = activeFont.getSize();

            int leftX = (int) (x - scale * 1.5);
            int leftY = (int) (y - scale / 2);

            int rightX = (int) (x - scale * 0.75);
            int rightY = (int) (y - scale / 2);

            int upX = (int) (x - scale * 1.1);
            int upY = (int) (y - 2 * scale / 3);

            int downX = (int) (x - scale * 1.1);
            int downY = (int) (y - 1 * scale / 3);

            g2.setColor(StartMenuSettings.activeOptionColor);
            g2.drawLine(leftX, leftY, rightX, rightY);
            g2.drawLine(upX, upY, rightX, rightY);
            g2.drawLine(downX, downY, rightX, rightY);
        }
    }
}

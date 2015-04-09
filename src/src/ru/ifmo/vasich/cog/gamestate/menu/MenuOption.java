package src.ru.ifmo.vasich.cog.gamestate.menu;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class MenuOption {

    protected Menu holder;
    protected String option;

    protected Color passiveColor;
    protected Font passiveFont;

    protected Color activeColor;
    protected Font activeFont;

    boolean isActive;

    public MenuOption(String option, Menu holder) {
        this.option = option;
        this.holder = holder;

        isActive = holder.options.size() == 0;
        if (isActive) {
            holder.setCurrentOption(this);
        }
    }

    void keyPressed(KeyEvent e) {
        int i = holder.options.indexOf(this);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                i--;
                if (i < 0) {
                    i = holder.options.size() - 1;
                }
                holder.setCurrentOption(holder.options.get(i));
                break;
            case KeyEvent.VK_DOWN:
                i++;
                if (i >= holder.options.size()) {
                    i = 0;
                }
                holder.setCurrentOption(holder.options.get(i));
                break;
        }
    }

    abstract void keyReleased(KeyEvent e);

    public void render(Graphics2D g2, int x, int y) {
        g2.setColor(isActive ? activeColor : passiveColor);
        g2.setFont(isActive ? activeFont : passiveFont);

        g2.drawString(option, x, y);
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}

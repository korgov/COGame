package src.ru.ifmo.vasich.cog.main;

import src.ru.ifmo.vasich.cog.gameloop.COGameWindow;
import src.ru.ifmo.vasich.ge.window.GameWindow;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public static int width = gd.getDisplayMode().getWidth();
    public static int height = gd.getDisplayMode().getHeight();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow frame = new COGameWindow(width / 2, height / 2);
            }
        });
    }
}

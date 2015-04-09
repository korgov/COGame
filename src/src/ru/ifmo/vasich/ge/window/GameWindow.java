package src.ru.ifmo.vasich.ge.window;

import src.ru.ifmo.vasich.ge.gamestate.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private ScreenMode screenMode = ScreenMode.WINDOWED;
    private GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    private int windowWidth;
    private int windowHeight;

    private GameLoop gameLoop;

    public GameWindow(String title, int width, int height, ScreenMode screenMode) {
        setTitle(title);

        windowWidth = width;
        windowHeight = height;

        this.screenMode = screenMode;
        setScreenMode();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void setContent(GameStateManager gsm) {
        gameLoop = new GameLoop(gsm);
        setContentPane(gameLoop);
    }

    private void setScreenMode() {
        switch (screenMode) {

            case WINDOWED:
                setUndecorated(false);
                setSize(new Dimension(windowWidth, windowHeight));
                setPreferredSize(getSize());
                break;

            case WINDOWED_UNDECORATED:
                setUndecorated(true);
                setSize(new Dimension(windowWidth, windowHeight));
                setPreferredSize(getSize());
                break;

            case MAXIMIZED:
                setUndecorated(true);
                setSize(Toolkit.getDefaultToolkit().getScreenSize());
                setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                break;

            case FULLSCREEN:
                setUndecorated(true);
                setSize(Toolkit.getDefaultToolkit().getScreenSize());
                setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
                device.setFullScreenWindow(this);
                break;
        }

        Config.width = getPreferredSize().width;
        Config.height = getPreferredSize().height;
    }

    public void setScreenMode(ScreenMode mode) {
        screenMode = mode;
        setScreenMode();
    }
}

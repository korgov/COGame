package src.ru.ifmo.vasich.ge.window;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private ScreenMode screenMode = ScreenMode.WINDOWED;
    private GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    private int windowWidth;
    private int windowHeight;

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

    private void setScreenMode() {
        switch (screenMode) {

            case WINDOWED:
                setUndecorated(false);
                setSize(new Dimension(windowWidth, windowHeight));
                setPreferredSize(getSize());

                if (getContentPane() != null) {
                    getContentPane().setPreferredSize(getSize());
                }
                break;

            case WINDOWED_UNDECORATED:
                setUndecorated(true);
                setSize(new Dimension(windowWidth, windowHeight));
                setPreferredSize(getSize());

                if (getContentPane() != null) {
                    getContentPane().setPreferredSize(getSize());
                }
                break;

            case MAXIMIZED:
                setUndecorated(true);
                setSize(Toolkit.getDefaultToolkit().getScreenSize());
                setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
                setExtendedState(JFrame.MAXIMIZED_BOTH);

                if (getContentPane() != null) {
                    getContentPane().setPreferredSize(getPreferredSize());
                }
                break;

            case FULLSCREEN:
                setUndecorated(true);
                setSize(Toolkit.getDefaultToolkit().getScreenSize());
                setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

                device.setFullScreenWindow(this);

                if (getContentPane() != null) {
                    getContentPane().setPreferredSize(getPreferredSize());
                }
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

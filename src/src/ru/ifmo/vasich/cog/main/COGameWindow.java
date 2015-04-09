package src.ru.ifmo.vasich.cog.main;

import src.ru.ifmo.vasich.ge.window.GameWindow;
import src.ru.ifmo.vasich.ge.window.ScreenMode;

public class COGameWindow extends GameWindow {
    public COGameWindow(int width, int height) {
        super("Camera obscura", width, height, ScreenMode.FULLSCREEN);
        setContent(new COStateManager());
        setVisible(true);
    }
}

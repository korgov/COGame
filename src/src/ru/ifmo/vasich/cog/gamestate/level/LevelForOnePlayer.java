package src.ru.ifmo.vasich.cog.gamestate.level;

import src.ru.ifmo.vasich.cog.entity.Player;
import src.ru.ifmo.vasich.cog.inputhandling.moving.MoveInputHandler;
import src.ru.ifmo.vasich.ge.state.GameStateManager;
import src.ru.ifmo.vasich.ge.window.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelForOnePlayer extends Level {
    public LevelForOnePlayer(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    void setBackground() {
        background = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) background.getGraphics();
        graphics2D.setColor(Color.DARK_GRAY);
        graphics2D.fillRect(0, 0, 5000, 5000);
        graphics2D.setColor(Color.CYAN);
        for (int i = 0, d = 30; i * d < background.getHeight(); ++i) {
            graphics2D.drawLine(0, d * i, 5000, d * i);
            graphics2D.drawLine(d * i, 0, d * i, 5000);
        }
    }

    @Override
    void initScreens() {
        players.add(new Player(50, 50, Color.orange, Color.red));
        inputHandlers.add(new MoveInputHandler().defaultHandler());
        screens.add(
                new GameScreen(
                        0, 0,
                        Config.getWidth(), Config.getHeight(),
                        0, 0,
                        players.get(0), inputHandlers.get(0),
                        this
                )
        );
    }
}

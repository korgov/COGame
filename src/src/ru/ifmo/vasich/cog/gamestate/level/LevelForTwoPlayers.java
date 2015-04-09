package src.ru.ifmo.vasich.cog.gamestate.level;

import src.ru.ifmo.vasich.cog.entity.Player;

import src.ru.ifmo.vasich.cog.inputhandling.moving.MoveInputHandler;
import src.ru.ifmo.vasich.ge.state.GameStateManager;
import src.ru.ifmo.vasich.ge.window.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelForTwoPlayers extends Level {
    public LevelForTwoPlayers(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    void setBackground() {
        background = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) background.getGraphics();
        graphics2D.setColor(Color.DARK_GRAY);
        graphics2D.fillRect(0, 0, 5000, 5000);
        graphics2D.setColor(Color.CYAN);
        for (int i = 0, d = 100; i * d < background.getHeight(); ++i) {
            graphics2D.drawLine(0, d * i, 5000, d * i);
            graphics2D.drawLine(d * i, 0, d * i, 5000);
        }
    }

    @Override
    void initScreens() {
        int offset = 5;
        players.add(new Player(50, 50, Color.green, Color.pink));
        inputHandlers.add(new MoveInputHandler().firstPlayerHandler());
        screens.add(
                new GameScreen(
                        0, 0,
                        Config.getWidth() / 2 - offset, Config.getHeight(),
                        0, 0,
                        players.get(0), inputHandlers.get(0),
                        this
                )
        );

        players.add(new Player(50 + Config.getWidth() / 2, 250, Color.pink, Color.green));
        inputHandlers.add(new MoveInputHandler().secondPlayerHandler());
        screens.add(
                new GameScreen(
                        offset + Config.getWidth() / 2, 0,
                        Config.getWidth() / 2 - offset, Config.getHeight(),
                        Config.getWidth() / 2, 0,
                        players.get(1), inputHandlers.get(1),
                        this
                )
        );
    }
}

package src.ru.ifmo.vasich.cog.gamestate.level;

import src.ru.ifmo.vasich.cog.entity.Player;
import src.ru.ifmo.vasich.ge.inputhandling.moving.MoveInputHandler;
import src.ru.ifmo.vasich.ge.gamestate.GameStateManager;
import src.ru.ifmo.vasich.ge.gamestate.level.GameScreen;
import src.ru.ifmo.vasich.ge.gamestate.level.GameScreenPlacer;
import src.ru.ifmo.vasich.ge.gamestate.level.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelForOnePlayer extends Level {
    public LevelForOnePlayer(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    protected void setBackground() {
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
    protected void initScreens() {

        GameScreenPlacer gsp = new GameScreenPlacer(1, 1, 10, this);
        gsp.setPosition(0, 0);

        Player p = new Player(50, 50, Color.orange, Color.red);
        p.setMoveInputHandler(new MoveInputHandler().defaultHandler());

        players.add(p);
        screens.add(new GameScreen(p, gsp));
    }
}

package src.ru.ifmo.vasich.cog.gamestate.level;

import src.ru.ifmo.vasich.cog.entity.Entity;
import src.ru.ifmo.vasich.cog.entity.Player;
import src.ru.ifmo.vasich.cog.inputhandling.moving.MoveInputHandler;
import src.ru.ifmo.vasich.ge.window.Config;

import java.awt.*;

public class GameScreen extends Rectangle {

    private Player player;
    private MoveInputHandler inputHandler;
    private Level level;


    private float xPos;
    private float yPos;

    public GameScreen(int x, int y, int width, int height,
                      float xPos, float yPos,
                      Player player, MoveInputHandler inputHandler,
                      Level level) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
        this.player = player;
        this.inputHandler = inputHandler;
        this.level = level;

        initBorders();
    }

    private float minSpeedOfScrolling = 0.8f;
    private float speedOfScrollingMultiplier = (float) (1.05 / Config.TPS);
    private float minScrollAmount = minSpeedOfScrolling * speedOfScrollingMultiplier;

    private float innerBorderFactor = 0.45f;
    private float innerBorderLeft;
    private float innerBorderRight;
    private float innerBorderUp;
    private float innerBorderDown;

    private float externalBorderFactor = 0.90f;
    private float externalBorderLeft;
    private float externalBorderRight;
    private float externalBorderUp;
    private float externalBorderDown;

    private float offsetX;
    private float offsetY;
    private float glideOffset = 0.0f;

    private void initBorders() {
        innerBorderLeft = width * innerBorderFactor;
        innerBorderRight = width * (1 - innerBorderFactor);
        innerBorderUp = height * innerBorderFactor;
        innerBorderDown = height * (1 - innerBorderFactor);

        externalBorderLeft = width * externalBorderFactor;
        externalBorderRight = width * (1 - externalBorderFactor);
        externalBorderUp = height * externalBorderFactor;
        externalBorderDown = height * (1 - externalBorderFactor);

        offsetX = innerBorderLeft * 0.25f;
        offsetY = innerBorderUp * 0.25f;
    }

    /**
     * Этот и последующие методы ответственны за плавное перемещение камеры.
     */
    private void glideLeft() {
        float dx = player.getXPos() - xPos;
        if (player.vX() < -0.01 && dx < externalBorderLeft) {
            xPos += (externalBorderLeft - dx) *
                    speedOfScrollingMultiplier * player.vX();
        } else if (!player.isRight() && dx < innerBorderLeft) {
            xPos += (innerBorderLeft - dx) * (-minScrollAmount);
        }
    }

    private void glideRight() {
        float dx = player.getXPos() - xPos;

        if (player.vX() > 0.01 && dx > externalBorderRight) {
            xPos += (dx - externalBorderRight) *
                    speedOfScrollingMultiplier * player.vX();
        } else if (!player.isLeft() && dx > innerBorderRight) {
            xPos += (dx - innerBorderRight) * minScrollAmount;
        }
    }

    private void glideUp() {
        float dy = player.getYPos() - yPos;
        if (player.vY() < -0.01 && dy < externalBorderUp) {
            yPos += (externalBorderUp - dy) *
                    speedOfScrollingMultiplier * player.vY();
        } else if (!player.isDown() && dy < innerBorderUp - glideOffset) {
            yPos += (innerBorderUp - dy + glideOffset) * (-minScrollAmount);
        }
    }

    private void glideDown() {
        float dy = player.getYPos() - yPos;

        if (player.vY() > 0.01 && dy > externalBorderDown) {
            yPos += (dy - externalBorderDown) *
                    speedOfScrollingMultiplier * player.vY();
        } else if (!player.isUp() && dy > innerBorderDown + glideOffset) {
            yPos += (dy - innerBorderDown + glideOffset) * minScrollAmount;
        }
    }

    private void checkBorders() {
        if (xPos < -offsetX) {
            xPos = -offsetX;
        } else if (xPos > level.background.getWidth() - width + offsetX) {
            xPos = (level.background.getWidth() - width + offsetX);
        }

        if (yPos < -offsetY) {
            yPos = -offsetY;
        } else if (yPos > level.background.getHeight() - height + offsetY) {
            yPos = (level.background.getHeight() - height + offsetY);
        }
    }

    public void tick() {
        player.tick();
        glideLeft();
        glideRight();
        glideUp();
        glideDown();
        checkBorders();
    }

    public void render(Graphics2D g2) {

        // экстендил Rectangle только ради этой строчки :(
        g2.setClip(this);
        g2.drawImage(level.background,
                (int) (-xPos + x),
                (int) (-yPos + y),
                null
        );
        for (Player p : level.players) {
            g2.drawImage(p.getSprite(),
                    (int) (p.getXPos() - xPos + x),
                    (int) (p.getYPos() - yPos + y),
                    null
            );
        }

        if (Entity.areCollided(level.players.get(0), level.players.get(1))) {
            g2.setColor(Color.white);
            g2.drawString("BUMP!", x + 10, y + 50);
        }
    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public Player getPlayer() {
        return player;
    }

    public MoveInputHandler getInputHandler() {
        return inputHandler;
    }
}

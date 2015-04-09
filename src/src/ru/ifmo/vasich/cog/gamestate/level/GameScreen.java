package src.ru.ifmo.vasich.cog.gamestate.level;

import src.ru.ifmo.vasich.cog.entity.Entity;
import src.ru.ifmo.vasich.cog.entity.MovableObject;
import src.ru.ifmo.vasich.ge.window.Config;

import java.awt.*;

public class GameScreen extends Rectangle {

    private MovableObject cameraFocus;
    private Level level;
    private float xPos;
    private float yPos;

    public GameScreen(MovableObject cameraFocus, final GameScreenPlacer gsp) {
        this.x = gsp.getX();
        this.y = gsp.getY();
        this.width = gsp.getWidth();
        this.height = gsp.getHeight();
        this.cameraFocus = cameraFocus;
        this.level = gsp.getLevel();

        this.xPos = cameraFocus.getXPos() - width / 2;
        this.yPos = cameraFocus.getYPos() - height / 2;

        initBorders();
        checkBorders();
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

        offsetX = 50;
        offsetY = 50;
    }

    /**
     * Этот и последующие методы ответственны за плавное перемещение камеры.
     */
    private void glideLeft() {
        float dx = cameraFocus.getXPos() - xPos;
        if (cameraFocus.vX() < -0.01 && dx < externalBorderLeft) {
            xPos += (externalBorderLeft - dx) *
                    speedOfScrollingMultiplier * cameraFocus.vX();
        } else if (!cameraFocus.isRight() && dx < innerBorderLeft) {
            xPos += (innerBorderLeft - dx) * (-minScrollAmount);
        }
    }

    private void glideRight() {
        float dx = cameraFocus.getXPos() - xPos;

        if (cameraFocus.vX() > 0.01 && dx > externalBorderRight) {
            xPos += (dx - externalBorderRight) *
                    speedOfScrollingMultiplier * cameraFocus.vX();
        } else if (!cameraFocus.isLeft() && dx > innerBorderRight) {
            xPos += (dx - innerBorderRight) * minScrollAmount;
        }
    }

    private void glideUp() {
        float dy = cameraFocus.getYPos() - yPos;
        if (cameraFocus.vY() < -0.01 && dy < externalBorderUp) {
            yPos += (externalBorderUp - dy) *
                    speedOfScrollingMultiplier * cameraFocus.vY();
        } else if (!cameraFocus.isDown() && dy < innerBorderUp - glideOffset) {
            yPos += (innerBorderUp - dy + glideOffset) * (-minScrollAmount);
        }
    }

    private void glideDown() {
        float dy = cameraFocus.getYPos() - yPos;

        if (cameraFocus.vY() > 0.01 && dy > externalBorderDown) {
            yPos += (dy - externalBorderDown) *
                    speedOfScrollingMultiplier * cameraFocus.vY();
        } else if (!cameraFocus.isUp() && dy > innerBorderDown + glideOffset) {
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
        cameraFocus.tick();
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
        for (MovableObject p : level.players) {
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

    public MovableObject getFocus() {
        return cameraFocus;
    }
}

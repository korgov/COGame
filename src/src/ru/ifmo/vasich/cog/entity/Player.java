package src.ru.ifmo.vasich.cog.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MovableObject {
    private Color bodyColor;
    private Color eyeColor;

    private BufferedImage body;
    private BufferedImage eye;


    private int bodyRadius = 9;
    private int eyeRadius = bodyRadius / 2;
    private int offset = eyeRadius;

    @Override
    protected void setSprite() {
        int bodyWidth = 2 * bodyRadius + 1;
        int bodyHeight = 2 * bodyRadius + 1;

        sprite = new BufferedImage(bodyWidth + 2 * offset, bodyHeight + 2 * offset, BufferedImage.TYPE_INT_ARGB);

        body = new BufferedImage(bodyWidth + 2 * offset, bodyHeight + 2 * offset, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = body.createGraphics();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
        g2.fillRect(0, 0, bodyWidth, bodyHeight);

        g2.setColor(bodyColor);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2.fillOval(offset, offset, bodyRadius * 2, bodyRadius * 2);


        int eyeWidth = 2 * eyeRadius + 1;
        int eyeHeight = 2 * eyeRadius + 1;

        eye = new BufferedImage(eyeWidth, eyeHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = eye.createGraphics();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
        g2.fillRect(0, 0, eyeWidth, eyeHeight);

        g2.setColor(eyeColor);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2.fillOval(0, 0, eyeRadius * 2, eyeRadius * 2);

        solidMask = body;

    }

    @Override
    protected void initVelocity() {
        up = false;
        down = false;
        left = false;
        right = false;

        speedLeft = 0f;
        speedRight = 0f;
        speedUp = 0f;
        speedDown = 0f;

        accelerationTime = 2f;
        decelerationTime = 1.2f;

        setMaxV(350f);
    }

    @Override
    protected void redrawSprite() {
        Graphics2D g2 = sprite.createGraphics();

        g2.setBackground(new Color(255, 255, 255, 0));
        g2.clearRect(0, 0, sprite.getWidth(), sprite.getHeight());

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2.drawImage(body, 0, 0, null);

        int eyeX = (int) (bodyRadius * (1 + vX() / (1.25 * maxV))) - eyeRadius;
        int eyeY = (int) (bodyRadius * (1 + vY() / (1.25 * maxV))) - eyeRadius;

        if (vX() < 0) eyeX++;
        if (vY() < 0) eyeY++;

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2.drawImage(eye, eyeX + offset, eyeY + offset, null);
    }

    public Player(int x, int y, Color bodyColor, Color eyeColor) {
        super(x, y);
        this.bodyColor = bodyColor;
        this.eyeColor = eyeColor;
        setSprite();
    }

    public int getRadius() {
        return bodyRadius;
    }
}

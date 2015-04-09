package src.ru.ifmo.vasich.cog.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected BufferedImage sprite;
    protected BufferedImage solidMask;

    protected Shape opqueBox;

    protected float xPos;
    protected float yPos;

    public Entity(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    abstract protected void setSprite();

    public BufferedImage getSprite() {
        return sprite;
    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    /**
     * ѕровер€ем два объекта на столкновение:
     * если их solidMask'и не пересекаютс€, они не могут столкнутьс€ - возвращаем false.
     * ≈сли пересекаютс€, попиксельно сканим область пересечени€ на предмет наложени€.
     * Ќаходим наложение - сразу возвращаем true; так и не нашли - false.
     */
    public static boolean areCollided(Entity e1, Entity e2) {

        int left1 = (int) e1.xPos;
        int left2 = (int) e2.xPos;
        int right1 = (int) (e1.xPos + e1.solidMask.getWidth());
        int right2 = (int) (e2.xPos + e2.solidMask.getWidth());

        if (left2 > right1 || left1 > right2) {
            return false;
        }

        int top1 = (int) e1.yPos;
        int top2 = (int) e2.yPos;
        int bottom1 = (int) (e1.yPos + e1.solidMask.getHeight());
        int bottom2 = (int) (e2.yPos + e2.solidMask.getHeight());

        if (top1 > bottom2 || top2 > bottom1) {
            return false;
        }

        int overBottom = bottom1 < bottom2 ? bottom1 : bottom2;
        int overTop = top1 > top2 ? top1 : top2;
        int overRight = right1 < right2 ? right1 : right2;
        int overLeft = left1 > left2 ? left1 : left2;

        int overWidth = overRight - overLeft;
        int overHeight = overBottom - overTop;

        int dx1 = overLeft - left1;
        int dy1 = overTop - top1;
        int dx2 = overLeft - left2;
        int dy2 = overTop - top2;

        for (int x = 0; x < overWidth; ++x) {
            for (int y = 0; y < overHeight; ++y) {
                int point = e1.solidMask.getRGB(dx1 + x, dy1 + y) &
                        e2.solidMask.getRGB(dx2 + x, dy2 + y);
                if (point != 0) {
                    return true;
                }
            }
        }

        return false;
    }
}

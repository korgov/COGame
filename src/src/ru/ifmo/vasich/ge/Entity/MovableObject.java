package src.ru.ifmo.vasich.ge.Entity;

import src.ru.ifmo.vasich.ge.inputhandling.moving.MoveInputHandler;
import src.ru.ifmo.vasich.ge.window.Config;

public abstract class MovableObject extends Entity {

    protected boolean up;
    protected boolean down;
    protected boolean left;
    protected boolean right;

    protected float speedLeft;
    protected float speedRight;
    protected float speedUp;
    protected float speedDown;

    protected float accelerationTime;
    protected float decelerationTime;

    protected float maxV;
    protected MoveInputHandler moveInputHandler;

    public MovableObject(int xPos, int yPos) {
        super(xPos, yPos);

        initVelocity();
    }

    protected abstract void initVelocity();

    protected abstract void redrawSprite();

    /**
     * @param maxV is max velocity expressed in pixels per second
     */
    public void setMaxV(float maxV) {
        this.maxV = (float) (maxV / Config.TPS);
    }

    /**
     * @param accelerationTime is time in seconds
     *                         required to pick up the max speed
     */
    public void setAccelerationTime(float accelerationTime) {
        this.accelerationTime = accelerationTime;
    }

    /**
     * @param decelerationTime is time in seconds
     *                         required to dump speed from maxV to 0
     */
    public void setDecelerationTime(float decelerationTime) {
        this.decelerationTime = decelerationTime;
    }

    /**
     * @return speed increase per tick
     */
    private float accelerationAmount() {
        return (float) (maxV / (accelerationTime * Config.TPS));
    }

    /**
     * @return speed decrease per tick
     */
    private float decelerationAmount() {
        return (float) (maxV / (decelerationTime * Config.TPS));
    }

    public float vX() {
        return speedRight - speedLeft;
    }

    public float vY() {
        return speedDown - speedUp;
    }

    public float V() {
        return (float) Math.sqrt(
                vX() * vX() +
                        vY() * vY()
        );
    }

    /**
     * sets speed equal to maxV in current direction
     * if direction is undefined method directs speed down
     */
    private void normalizeV() {
        if (V() == 0) {
            speedDown = maxV;
        } else {
            float scale = maxV / V();
            speedUp = speedUp * scale;
            speedDown = speedDown * scale;
            speedLeft = speedLeft * scale;
            speedRight = speedRight * scale;
        }
    }

    public void tick() {
        if (up) {
            speedUp += accelerationAmount();
            if (V() > maxV) {
                normalizeV();
            }
        } else {
            speedUp -= decelerationAmount();
            if (speedUp < 0) {
                speedUp = 0;
            }
        }

        if (down) {
            speedDown += accelerationAmount();
            if (V() > maxV) {
                normalizeV();
            }
        } else {
            speedDown -= decelerationAmount();
            if (speedDown < 0) {
                speedDown = 0;
            }
        }

        if (left) {
            speedLeft += accelerationAmount();
            if (V() > maxV) {
                normalizeV();
            }
        } else {
            speedLeft -= decelerationAmount();
            if (speedLeft < 0) {
                speedLeft = 0;
            }
        }

        if (right) {
            speedRight += accelerationAmount();
            if (V() > maxV) {
                normalizeV();
            }
        } else {
            speedRight -= decelerationAmount();
            if (speedRight < 0) {
                speedRight = 0;
            }
        }

        xPos += vX();
        yPos += vY();

        redrawSprite();
    }

    public float getMaxV() {
        return maxV;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public MovableObject setMoveInputHandler(MoveInputHandler handler) {
        moveInputHandler = handler;
        return this;
    }

    public MoveInputHandler getMoveInputHandler() {
        return moveInputHandler;
    }
}

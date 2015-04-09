package src.ru.ifmo.vasich.cog.inputhandling.moving;

import src.ru.ifmo.vasich.cog.entity.MovableObject;

public class StopMoveRight extends MoveCommand {
    @Override
    public void move(MovableObject movableObject) {
        movableObject.setRight(false);
    }
}

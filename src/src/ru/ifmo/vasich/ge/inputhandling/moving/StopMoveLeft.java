package src.ru.ifmo.vasich.ge.inputhandling.moving;

import src.ru.ifmo.vasich.ge.Entity.MovableObject;

public class StopMoveLeft extends MoveCommand {
    @Override
    public void move(MovableObject movableObject) {
        movableObject.setLeft(false);
    }
}

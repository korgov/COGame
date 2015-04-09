package src.ru.ifmo.vasich.ge.inputhandling.moving;


import src.ru.ifmo.vasich.ge.Entity.MovableObject;

public abstract class MoveCommand {
    public abstract void move(MovableObject movableObject);
}

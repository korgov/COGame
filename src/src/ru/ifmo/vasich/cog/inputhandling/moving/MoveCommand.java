package src.ru.ifmo.vasich.cog.inputhandling.moving;


import src.ru.ifmo.vasich.cog.entity.MovableObject;

public abstract class MoveCommand {
    public abstract void move(MovableObject movableObject);
}

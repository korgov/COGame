package src.ru.ifmo.vasich.cog.inputhandling.moving;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class MoveInputHandler {
    private Map<String, MoveCommand> commands;

    public MoveInputHandler() {
        commands = new HashMap<String, MoveCommand>();
    }

    public MoveInputHandler defaultHandler() {
        commands.clear();

        commands.put(KeyEvent.VK_W + " pressed", new StartMoveUp());
        commands.put(KeyEvent.VK_UP + " pressed", new StartMoveUp());
        commands.put(KeyEvent.VK_S + " pressed", new StartMoveDown());
        commands.put(KeyEvent.VK_DOWN + " pressed", new StartMoveDown());
        commands.put(KeyEvent.VK_A + " pressed", new StartMoveLeft());
        commands.put(KeyEvent.VK_LEFT + " pressed", new StartMoveLeft());
        commands.put(KeyEvent.VK_D + " pressed", new StartMoveRight());
        commands.put(KeyEvent.VK_RIGHT + " pressed", new StartMoveRight());

        commands.put(KeyEvent.VK_W + " released", new StopMoveUp());
        commands.put(KeyEvent.VK_UP + " released", new StopMoveUp());
        commands.put(KeyEvent.VK_S + " released", new StopMoveDown());
        commands.put(KeyEvent.VK_DOWN + " released", new StopMoveDown());
        commands.put(KeyEvent.VK_A + " released", new StopMoveLeft());
        commands.put(KeyEvent.VK_LEFT + " released", new StopMoveLeft());
        commands.put(KeyEvent.VK_D + " released", new StopMoveRight());
        commands.put(KeyEvent.VK_RIGHT + " released", new StopMoveRight());

        return this;
    }

    public MoveInputHandler firstPlayerHandler() {
        commands.clear();

        commands.put(KeyEvent.VK_W + " pressed", new StartMoveUp());
        commands.put(KeyEvent.VK_S + " pressed", new StartMoveDown());
        commands.put(KeyEvent.VK_A + " pressed", new StartMoveLeft());
        commands.put(KeyEvent.VK_D + " pressed", new StartMoveRight());

        commands.put(KeyEvent.VK_W + " released", new StopMoveUp());
        commands.put(KeyEvent.VK_S + " released", new StopMoveDown());
        commands.put(KeyEvent.VK_A + " released", new StopMoveLeft());
        commands.put(KeyEvent.VK_D + " released", new StopMoveRight());

        return this;
    }

    public MoveInputHandler secondPlayerHandler() {
        commands.clear();

        commands.put(KeyEvent.VK_UP + " pressed", new StartMoveUp());
        commands.put(KeyEvent.VK_DOWN + " pressed", new StartMoveDown());
        commands.put(KeyEvent.VK_LEFT + " pressed", new StartMoveLeft());
        commands.put(KeyEvent.VK_RIGHT + " pressed", new StartMoveRight());


        commands.put(KeyEvent.VK_UP + " released", new StopMoveUp());
        commands.put(KeyEvent.VK_DOWN + " released", new StopMoveDown());
        commands.put(KeyEvent.VK_LEFT + " released", new StopMoveLeft());
        commands.put(KeyEvent.VK_RIGHT + " released", new StopMoveRight());

        return this;
    }

    public MoveCommand handleInput(String commandKey) {
        return commands.get(commandKey);
    }
}

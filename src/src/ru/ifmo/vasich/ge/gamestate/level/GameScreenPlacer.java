package src.ru.ifmo.vasich.ge.gamestate.level;

import src.ru.ifmo.vasich.ge.window.Config;

public class GameScreenPlacer {
    private int gridRows;
    private int gridColumns;
    private int row;
    private int column;
    private int offset;

    private Level level;

    private int width;
    private int height;

    public GameScreenPlacer(int gridRows, int gridColumns, int offset, Level level) {
        this.gridRows = gridRows;
        this.gridColumns = gridColumns;
        this.offset = offset;
        this.level = level;
        row = 0;
        column = 0;
        setDefaultWidth();
        setDefaultHeight();
    }

    public void setDefaultWidth() {
        width = (Config.getWidth() - offset * (gridColumns + 1)) / gridColumns;
    }

    public void setDefaultHeight() {
        height = (Config.getHeight() - offset * (gridRows + 1)) / gridRows;
    }

    public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getX() {
        return (width + offset) * column + offset;
    }

    public int getY() {
        return (height + offset) * row + offset;
    }

    public int getOffset() {
        return offset;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Level getLevel() {
        return level;
    }
}

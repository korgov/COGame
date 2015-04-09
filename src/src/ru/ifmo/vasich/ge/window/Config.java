package src.ru.ifmo.vasich.ge.window;

public class Config {
    public static final double FPS = 60;
    public static final double TPS = 120;
    public static final int NSPS = 1000000000;

    /**
     * ��, ���������� ��������� - �����, �� ��������� ���� � ����� ���,
     * � ���������������� � ������������� ��� ���� ����� ���
     */
    protected static int width;
    protected static int height;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}

package src.ru.ifmo.vasich.ge.window;

import src.ru.ifmo.vasich.ge.gamestate.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameLoop extends JPanel implements Runnable {

    private Thread thread;
    private boolean running;

    private GameStateManager gsm;
    private Graphics2D g2;
    private BufferedImage img;

    /**
     * FPS (frames per second): how many times per second we want to redraw the screen
     * TPS (ticks per second): how many times per second we update game logic
     */
    private double FPS = Config.FPS;
    private double TPS = Config.TPS;

    // nanos per second, obviously
    private int NSPS = Config.NSPS;

    public GameLoop(GameStateManager gsm) {
        this.gsm = gsm;

        gsm.init();

        // JPanel must be focusable by default, but still
        setFocusable(true);
        requestFocus();
    }

    /**
     * stackoverflow.com says I should put some initialization here
     * instead of constructor for some reason
     */
    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(gsm);
            thread.start();
        }
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        init();
    }

    protected void init() {
        img = new BufferedImage(Config.getWidth(), Config.getHeight(), BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) img.getGraphics();
        g2.setClip(0, 0, getWidth(), getHeight());

        running = true;
    }

    @Override
    public void run() {

        init();

        long tickTimer = 0;
        double timePerTick = NSPS / TPS;

        long frameTimer = 0;
        double timePerFrame = NSPS / FPS;

        long previous = System.nanoTime();

        while (running) {
            long current = System.nanoTime();
            long elapsed = current - previous;
            previous = current;

            tickTimer += elapsed;
            frameTimer += elapsed;

            while (tickTimer > timePerTick) {
                tick();
                tickTimer -= timePerTick;
            }

            //if (frameTimer > timePerFrame) {
            render();
            frameTimer %= timePerFrame;
            // }
        }
    }

    // update game logic
    protected void tick() {
        gsm.tick();
    }

    // update screen image
    protected void render() {
        g2.setClip(0, 0, Config.getWidth(), Config.getHeight());
        g2.clearRect(0, 0, Config.getWidth(), Config.getHeight());
        gsm.render(g2);
        toScreen();
    }

    protected void toScreen() {
        Graphics g = getGraphics();
        if (img != null) {
            g.drawImage(img, 0, 0, null);
        }
        g.dispose();
    }
}


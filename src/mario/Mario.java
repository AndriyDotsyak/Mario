package mario;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Mario extends Canvas implements Runnable {
    private boolean running;

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;

        init();

        while (running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            update(delta);
            render();
        }
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void init() {

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics graphics = bs.getDrawGraphics();
        graphics.setColor(Color.cyan);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.dispose();
        bs.show();
    }

    public void update(long delta) {

    }
}

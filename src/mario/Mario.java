package mario;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mario extends Canvas implements Runnable {
    private boolean running;
    public static Sprite sbeckground;

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
        sbeckground = getSprite("Sprites/Background.jpg");
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics graphics = bs.getDrawGraphics();
        graphics.fillRect(0, 0, getWidth(), getHeight());
        sbeckground.draw(graphics, 0, 0);
        graphics.dispose();
        bs.show();
    }

    public void update(long delta) {

    }

    public Sprite getSprite(String path) {
        BufferedImage sourceImage = null;

        try {
            sourceImage = ImageIO.read(new File("Sprites/Background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));

        return sprite;
    }
}

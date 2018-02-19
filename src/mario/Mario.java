package mario;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mario extends Canvas implements Runnable {
    private boolean running;

    public Sprite beckground;
    public Sprite marioDefault;

    private int marioX = 402;
    private int marioY = 374;

    boolean up = true;

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
        beckground = getSprite("Sprites/Background.jpg");
        marioDefault = getSprite("Sprites/MarioDefault.png");

        addKeyListener(new KeyInputHandler());
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
        beckground.draw(graphics, 0, 0);
        marioDefault.draw(graphics, marioX, marioY);
        graphics.dispose();
        bs.show();
    }

    public void update(long delta) {
        if (KeyInputHandler.upPressed) {

            if (marioY >= 320 && up == true) {
                marioY--;
                if (marioY == 320) up = false;
            } else if (marioY <= 374) {
                marioY++;
                if (marioY == 374) {
                    up = true;
                    KeyInputHandler.upPressed = false;
                }
            }
        }

        if (KeyInputHandler.leftPressed) {
            if (marioX >= 0) {
                marioX--;
            }
        }

        if (KeyInputHandler.rigthPressed) {
            if (marioX <= 747) {
                marioX++;
            }
        }
    }

    public Sprite getSprite(String path) {
        BufferedImage sourceImage = null;

        try {
            sourceImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));

        return sprite;
    }
}

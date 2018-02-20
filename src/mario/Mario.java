package mario;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class Mario extends Canvas implements Runnable {
    private boolean running;
    private boolean openDrawer = false;
    boolean up = true;

    private Sprite beckground;
    private Sprite mario;
    private Sprite faifly;

    private int marioX = 402;
    private int marioY = 374;

    private int drawerY = 258;

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

    void start() {
        running = true;
        new Thread(this).start();

        sound();
    }

    private void init() {
        beckground = getSprite("Sprites/Background.jpg");
        mario = getSprite("Sprites/MarioDefault.png");
        faifly = getSprite("Sprites/Faifly.png");

        addKeyListener(new KeyInputHandler());
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics graphics = bs.getDrawGraphics();
        graphics.fillRect(0, 0, getWidth(), getHeight());
        beckground.draw(graphics, 0, 0);
        mario.draw(graphics, marioX, marioY);
        if (openDrawer) faifly.draw(graphics, 396, drawerY);
        graphics.dispose();
        bs.show();
    }

    public void update(long delta) {
        if (!KeyInputHandler.upPressed && !KeyInputHandler.leftPressed && !KeyInputHandler.rigthPressed) {
            mario = getSprite("Sprites/MarioDefault.png");
        }

        if (KeyInputHandler.upPressed) {

            if (marioY >= 320 && up) {
                marioY--;
                if (marioY == 320) {
                    up = false;

                    if (marioX >= 385 && marioX <= 425) {
                        openDrawer = true;
                    }
                }
            } else if (marioY <= 374) {
                marioY++;
                if (marioY == 374) {
                    up = true;
                    KeyInputHandler.upPressed = false;
                }
            }

            mario = getSprite("Sprites/MarioUp.png");
        }

        if (KeyInputHandler.leftPressed) {
            if (marioX >= 0) {
                marioX--;
                mario = getSprite("Sprites/MarioLeft.png");
            }
        }

        if (KeyInputHandler.rigthPressed) {
            if (marioX <= 747) {
                marioX++;
                mario = getSprite("Sprites/MarioRigth.png");
            }
        }

        if (openDrawer && drawerY >= 210) {
            drawerY--;
        }
    }

    private Sprite getSprite(String path) {
        BufferedImage sourceImage = null;

        try {
            sourceImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));

        return sprite;
    }

    private void sound() {
        try {
            File file = new File("Sound/SoundMario.mp3");

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            Player player = new Player(bis);
            player.play();

        } catch (IOException | JavaLayerException ioe) {

        }
    }
}
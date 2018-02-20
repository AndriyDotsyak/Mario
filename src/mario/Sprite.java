package mario;

import java.awt.*;

public class Sprite {
    private Image image;

    Sprite(Image image) {
        this.image = image;
    }

    int getWidth() {
        return image.getWidth(null);
    }

    int getHeight() {
        return image.getHeight(null);
    }

    void draw(Graphics g, int x, int y) {
        g.drawImage(image,x,y,null);
    }
}

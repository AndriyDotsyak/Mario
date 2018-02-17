package mario;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static int WIDTH = 650;
    public static int HEIGHT = 450;
    public static String PROJECT_NAME = "Mario";

    public static void main(String[] args) {
        Mario mario = new Mario();
        mario.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame(PROJECT_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(mario, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        mario.start();
    }
}

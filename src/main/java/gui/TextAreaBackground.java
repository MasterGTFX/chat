package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TextAreaBackground extends JTextPane {
    private URL filePath = getClass().getClassLoader().getResource("czatBckg.jpg");

    public TextAreaBackground() {
    }
    public TextAreaBackground(URL filePath) {
        this.filePath = filePath;
    }

    @Override
    protected void paintComponent(Graphics g) {
        try{
            Image img = ImageIO.read(filePath);
            g.drawImage(img,0,0,getWidth(),getHeight(),null);
            super.paintComponent(g);
        } catch(IOException e) {
            System.out.println("Couldnt load TextArea background file");
        }

    }
}
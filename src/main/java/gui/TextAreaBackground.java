package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TextAreaBackground extends JTextPane {
    private String filePath = "src/main/resources/czatBckg.jpg";
    public TextAreaBackground() {
    }
    public TextAreaBackground(String filePath) {
        this.filePath = filePath;
    }

    @Override
    protected void paintComponent(Graphics g) {
        try{
            Image img = ImageIO.read(new File(filePath));
            g.drawImage(img,0,0,getWidth(),getHeight(),null);
            super.paintComponent(g);
        } catch(IOException e) {
            System.out.println("Couldnt load TextArea background file");
        }

    }
}
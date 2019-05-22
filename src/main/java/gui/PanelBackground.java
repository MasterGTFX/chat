package gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanelBackground extends JPanel {
    private URL filePath ;
    public PanelBackground() {
        this.filePath = getClass().getClassLoader().getResource("background.gif");
    }
    public PanelBackground(URL filePath) {
        this.filePath = filePath;
    }
    @Override
    protected void paintComponent(Graphics g) {
        try {
            ImageIcon imageIcon = new ImageIcon(filePath);
            Image i = imageIcon.getImage();
            super.paintComponent(g); // paint the background image and scale it to fill the entire space
            g.drawImage(i, 0, 0, this.getSize().width, this.getSize().height, this);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldnt load background file");
        }
    }
}

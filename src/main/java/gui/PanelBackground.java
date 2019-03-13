package gui;

import javax.swing.*;
import java.awt.*;

public class PanelBackground extends JPanel {
    private String filePath ;
    public PanelBackground() {
        this.filePath = "src/main/resources/background.gif";
    }
    public PanelBackground(String filePath) {
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
            System.out.println("Couldnt load background file");
        }
    }
}

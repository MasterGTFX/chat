package gui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChatGUI {
    private JPanel panel1;
    private JTextField portField;
    private JTextField usernameField;
    private JTextField ipAdressField;
    private JButton connectButton;
    private JButton hostButton;
    private JLabel usernameLabel;
    private JLabel ipAdressLabel;
    private JLabel portLabel;
    private JPasswordField passwordField;

    public ChatGUI() {
       // $$$setupUI$$$();
        initialize();
        connectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] data = new String[5];
                data[0] = "client";
                data[1] = usernameField.getText();
                data[2] = ipAdressField.getText();
                data[3] = portField.getText();
                data[4] = passwordField.getText();
                WindowHandler.main(data);
            }
        });
        hostButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] data = new String[5];
                data[0] = "server";
                data[1] = usernameField.getText();
                data[2] = ipAdressField.getText();
                data[3] = portField.getText();
                data[4] = passwordField.getText();
                WindowHandler.main(data);
            }
        });
    }

    public void initialize() {
        usernameField.setText("User");
        ipAdressField.setText("localhost");
        portField.setText(Integer.toString(1500));

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new ChatGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 400);
        try {
            frame.setIconImage(new ImageIcon("src/main/resources/slonce.png").getImage());
        } catch (Exception e) {
            System.out.println("Couldnt load icon file");
        }
        frame.setVisible(true);

    }

    private void createUIComponents() {
        panel1 = new PanelBackground();
        panel1.setVisible(true);
    }

}

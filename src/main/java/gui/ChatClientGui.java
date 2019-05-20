package gui;

import client.ChatClient;
import model.Observer;

import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static gui.GuiDocumentWriter.writeMessageClient;
import static utilities.MessageJSON.serverAddonsJson;


public class ChatClientGui implements Observer {
    private JPanel panel1;
    private JTextField messageField;
    private JButton SENDButton;
    private JTabbedPane tabbedPane1;
    private ChatClient chatClient;
    private JTextPane messageArea;
    private JTextPane usersOnlineArea;
    private JScrollPane scrollPane;
    private JButton DISCONNECTButton;
    private JScrollPane usersPane;
    private boolean password = true;
    private boolean isConnected = false;


    public ChatClientGui(String[] data) {
        //$$$setupUI$$$();
        chatClient = new ChatClient(data);
        chatClient.subscribe(this);
        isConnected = true;
        Thread clientStart = new Thread(chatClient);
        clientStart.start();
        SENDButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (isConnected) {
                    chatClient.chatSendingMessage(messageField.getText());
                    messageField.setText("");
                }
            }
        });
        ;
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isConnected) {
                    chatClient.chatSendingMessage(messageField.getText());
                    messageField.setText("");
                }
            }
        });
        DISCONNECTButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isConnected) {
                    super.mousePressed(e);
                    isConnected = false;
                    chatClient.chatSendingMessage("/disconnect");
                    chatClient.unsubscribe(ChatClientGui.this);
                    JsonObject messageJSON = serverAddonsJson("You have been disconnected from server!");
                    usersOnlineArea.setText("");
                    messageRecieved(messageJSON.toString());
                }
            }
        });
    }

    public static void main(String[] args) {
       /* if (!(args.length > 0)) {
            String[] data = new String[5];
            data[0] = "client";
            data[1] = "user";
            data[2] = "localhost";
            data[3] = "1500";
            data[4] = "";
            args = data;
        }*/
        if (args.length != 5) {
            DialogWindows d = new DialogWindows("Error", "Not enough data");
        }
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new ChatClientGui(args).panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 600);
        try {
            frame.setIconImage(new ImageIcon("src/main/resources/slonce.png").getImage());
        } catch (Exception e) {
            System.out.println("Couldnt load icon file");
        }
        frame.setVisible(true);
    }

    private void createUIComponents() {
        panel1 = new PanelBackground("src/main/resources/background_gray.jpg");
        panel1.setVisible(true);
        messageArea = new TextAreaBackground();
        usersOnlineArea = new TextAreaBackground();
        scrollPane = TextAreaCustomCreation(messageArea);
        usersPane = TextAreaCustomCreation(usersOnlineArea);
    }

    public boolean isPassword() {
        return password;
    }

    public JScrollPane TextAreaCustomCreation(JTextPane area){
        area.setEditable(false);
        area.setBackground(new Color(1, 1, 1, (float) 0.01));
        JScrollPane pane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return pane;
    }
    @Override
    public void messageRecieved(String message) {
        if(message.contains("disconnect"))
            chatClient.chatSendingMessage("/users");
        writeMessageClient(message, messageArea, usersOnlineArea);
    }
}

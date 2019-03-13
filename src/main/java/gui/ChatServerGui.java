package gui;

import server.ChatServer;
import model.Observer;
import utilities.LogsWriter;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

import static gui.GuiDocumentWriter.writeMessageServer;
import static gui.TextAttributes.getLogsAttributeSet;

public class ChatServerGui implements Observer {
    private JPanel panel1;
    private JButton STARTButton;
    private JButton ENDButton;
    private JButton USERSButton;
    private JButton CLEARButton;
    private JTextPane logsArea;
    private JScrollPane scrollPane;
    private JTabbedPane tabbedPane1;
    private ChatServer chatServer;
    private Boolean isActivated;
    private LogsWriter logsWriter;
    private LocalDateTime time;

    public ChatServerGui(String[] data) {
        time = LocalDateTime.now();
        this.logsWriter = new LogsWriter();
        isActivated = false;
       // $$$setupUI$$$();
        chatServer = new ChatServer(data);
        chatServer.subscribe(this);
        Thread serverStart = new Thread(chatServer);
        STARTButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(!isActivated) {
                    isActivated = true;
                    serverStart.start();
                    try {
                        logsArea.getDocument().insertString(logsArea.getDocument().getLength(), "Server started..\n", getLogsAttributeSet());
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        ENDButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(isActivated){
                    isActivated = false;
                    try {
                        logsArea.getDocument().insertString(logsArea.getDocument().getLength(), "Server stopped working..\n", getLogsAttributeSet());
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                    System.exit(0);
                }

            }
        });
        USERSButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                String users = chatServer.getUsers().toString();
                try {
                    logsArea.getDocument().insertString(logsArea.getDocument().getLength(), users + "\n", getLogsAttributeSet());
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        });
        CLEARButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                logsArea.setText("");
            }
        });
    }

    public static void main(String[] args) {
      /*  if (!(args.length > 0)) {
            String[] data = new String[5];
            data[0] = "client";
            data[1] = "user";
            data[2] = "localhost";
            data[3] = "1500";
            data[4] = "";
            args = data;
        }*/
        JFrame frame = new JFrame("Server");
        frame.setContentPane(new ChatServerGui(args).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        try {
            frame.setIconImage(new ImageIcon("src/main/resources/slonce.png").getImage());
        } catch (Exception e) {
            System.out.println("Couldnt load icon file");
        }
        frame.setVisible(true);
    }

    private void createUIComponents() {
        panel1 = new PanelBackground("src/gui/resources/background_gray.jpg");
        logsArea = new TextAreaBackground();
        logsArea.setBackground(new Color(1, 1, 1, (float) 0.01));
        logsArea.setEditable(false);
        scrollPane = new JScrollPane(logsArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    @Override
    public void messageRecieved(String message) {
        writeMessageServer(message,logsArea,logsWriter);
    }
}

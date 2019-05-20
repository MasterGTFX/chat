package server;

import model.Chat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ChatServer extends Chat implements Runnable {
    ArrayList clientOutputStreams;
    String clientPassword;
    ChatAddons chatAddons;


    public ChatServer() {
        super();
        clientOutputStreams = new ArrayList();
        this.chatAddons = new ChatAddons(this);
    }

    public ChatServer(String[] data) {
        super(data);
        setPassword(data[4]);
        clientOutputStreams = new ArrayList();
        this.chatAddons = new ChatAddons(this);
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(getPortNumber());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread messagesThread = new Thread(new ClientHandler(clientSocket, this));
                messagesThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            getObserver().messageRecieved("\nError connecting");
        } catch (NullPointerException e) {
            getObserver().messageRecieved("\nConnection is being tested..");
        }
    }

    public void chatEveryone(String message) {
        if (chatAddons.usedCommand(message))
            message = chatAddons.usedCommandAction(message);
        getObserver().messageRecieved(message);
        Iterator i = clientOutputStreams.iterator();
        while (i.hasNext()) {
            try {
                OutputStreamWriter clientOutput = (OutputStreamWriter) i.next();
                clientOutput.write(message + System.lineSeparator());
                clientOutput.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList getClientOutputStreams() {
        return clientOutputStreams;
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        Thread starter = new Thread(server);
        starter.start();
    }

}

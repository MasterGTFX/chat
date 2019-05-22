package server.bot;


import model.Observable;
import model.Observer;
import server.user.ChatServer;
import server.user.ClientHandler;

import javax.json.JsonObject;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static utilities.MessageJSON.*;

public class ChatBotServer implements Runnable, Observable {
    private int port;
    private String name;
    private Socket botSocket;
    private Observer observer;
    private ChatServer chatServer;

    public ChatBotServer(String name, int port, ChatServer chatServer) {
        this.port = port;
        this.name = name;
        this.chatServer = chatServer;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            observer.messageRecieved(botLogs(name, "I've been just created!").toString());
            observer.messageRecieved(botLogs(name, "Waiting for connection on port " + port + "...").toString());
            botSocket = serverSocket.accept();
            Thread botMessagesThread = new Thread(new BotHandler(botSocket, this));
            botMessagesThread.start();
        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    public Observer getObserver() {
        return observer;
    }

    public ChatServer getChatServer() {
        return chatServer;
    }

    @Override
    public void subscribe(Observer o) {
        observer = o;
    }

    @Override
    public void unsubscribe(Observer o) {
        if (observer != null)
            observer = null;
    }

    @Override
    public void chatSendingMessage(String message) {

    }
}
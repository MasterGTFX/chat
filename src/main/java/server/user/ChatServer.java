package server.user;

import model.Chat;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static utilities.MessageJSON.botNotFound;
import static utilities.MessageJSON.toBotMessageJson;

public class ChatServer extends Chat implements Runnable {
    ArrayList clientOutputStreams;
    String clientPassword;
    ChatAddons chatAddons;
    HashMap<String, OutputStreamWriter> botOutputStreams = new HashMap<>();

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
        Iterator i = clientOutputStreams.iterator();
        if (message.contains("toBotMessage")) {
            try {
                JsonReader jsonReader = Json.createReader(new StringReader(message));
                JsonObject messageJson = jsonReader.readObject();
                jsonReader.close();
                if(botOutputStreams.isEmpty())
                    message = botNotFound(messageJson.getString("botName"), messageJson.getString("username")).toString();
                else if(getBotOutputStreams().get(messageJson.getString("botName")) == null) {
                    message = botNotFound(messageJson.getString("botName"), messageJson.getString("username")).toString();
                }
                else{
                    OutputStreamWriter botOutput = (OutputStreamWriter) getBotOutputStreams().get(messageJson.getString("botName"));
                    botOutput.write(message + System.lineSeparator());
                    botOutput.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        getObserver().messageRecieved(message);
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

    public HashMap getBotOutputStreams() {
        return botOutputStreams;
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        Thread starter = new Thread(server);
        starter.start();
    }

}

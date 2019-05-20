package server;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {
    private ChatServer chatServer;
    private BufferedReader reader;
    private Socket socket;
    private OutputStreamWriter clientOutput;
    private String messageRecieved;
    private String[] message;
    private ChatAddons chatAddons;
    private boolean firstConnection = false;
    private JsonObject firstMessageJson;

    public ClientHandler(Socket clientSocket,  ChatServer chatServer){
        this.chatAddons = new ChatAddons(chatServer);
        try{
            OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
            InputStreamReader in = new InputStreamReader(clientSocket.getInputStream());
            this.socket = clientSocket;
            this.clientOutput = out;
            this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.chatServer = chatServer;
            messageRecieved = reader.readLine();
            //first connection, client
            if(!(messageRecieved.isEmpty() || messageRecieved.equals("availabityCheck") || messageRecieved.equals("passwordCheck"))) {
                firstConnection = true;
                chatServer.getClientOutputStreams().add(out);
                chatAddons.logConnection();
                JsonReader jsonReader = Json.createReader(new StringReader(messageRecieved));
                firstMessageJson = jsonReader.readObject();
                chatServer.getUsers().add(firstMessageJson.getString("username"));
            }
            if(messageRecieved.equals("passwordCheck")){
                String password = reader.readLine();
                if(password.equals(chatServer.getPassword()) || chatServer.getPassword().isEmpty()) {
                    chatAddons.logPasswordCheck(true);
                    out.write("true" + System.lineSeparator());
                    out.flush();
                }
                else{
                    chatAddons.logPasswordCheck(false);
                    out.write("false" + System.lineSeparator());
                    out.flush();
                    throw new AuthenticationError("");
                }
            }
            if(messageRecieved.equals("availabityCheck")) {
                chatAddons.logConnectionCheck();
                out.write("imAlive" + System.lineSeparator());
                out.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            if(firstConnection) {
                chatServer.chatEveryone(messageRecieved);
                chatServer.chatEveryone("/users");
                while (messageRecieved != null) {
                    messageRecieved = reader.readLine();
                    chatServer.chatEveryone(messageRecieved);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

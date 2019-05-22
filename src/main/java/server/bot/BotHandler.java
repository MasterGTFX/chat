package server.bot;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static utilities.MessageJSON.*;

public class BotHandler implements Runnable {
    private ChatBotServer chatBotServer;
    private BufferedReader botIn;
    private Socket socket;
    private OutputStreamWriter botOut;
    private String messageRecieved;
    public BotHandler(Socket botSocket, ChatBotServer chatBotServer) {
        this.socket = botSocket;
        this.chatBotServer = chatBotServer;
        try {
            OutputStreamWriter out = new OutputStreamWriter(botSocket.getOutputStream(), StandardCharsets.UTF_8);
            InputStreamReader in = new InputStreamReader(botSocket.getInputStream());
            this.socket = botSocket;
            this.botOut = out;
            chatBotServer.getChatServer().getUsers().add("(BOT) " + chatBotServer.getName());
            chatBotServer.getChatServer().getBots().add(chatBotServer.getName());
            chatBotServer.getChatServer().getBotOutputStreams().put(chatBotServer.getName(), out);
            this.botIn = new BufferedReader(new InputStreamReader(botSocket.getInputStream()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            chatBotServer.getObserver().messageRecieved(botLogs(chatBotServer.getName(), "Bot connected! Waiting for message...").toString());
            chatBotServer.getChatServer().chatEveryone("/users");
            botOut.write(helloMessageBotJson(chatBotServer.getName()).toString());
            botOut.flush();
            chatBotServer.getChatServer().chatEveryone(helloMessageBotJson(chatBotServer.getName()).toString());
            messageRecieved = botIn.readLine();
            while (messageRecieved != null) {
                messageRecieved = botIn.readLine();
                chatBotServer.getChatServer().chatEveryone(messageRecieved);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

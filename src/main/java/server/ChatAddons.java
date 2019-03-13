//log messages, commands, disconnect etc.
package server;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.time.LocalDateTime;

import static utilities.MessageJSON.serverAddonsJson;
import static utilities.MessageJSON.serverLogsJson;

public class ChatAddons {
    private LocalDateTime time;
    private ChatServer chatServer;

    public ChatAddons(ChatServer chatServer){
        this.chatServer = chatServer;
    }
    public boolean usedCommand(String message){
        boolean usedCommand = false;
        if(message.contains("/date") || message.contains("/users") || message.contains("/disconnect")){
            usedCommand = true;
        }
        return usedCommand;
    }
    public String usedCommandAction(String message){
        JsonObject messageJSON = serverAddonsJson("");
        if(message.contains("/date")) {
            time = LocalDateTime.now();
            messageJSON = serverAddonsJson((time.getDayOfWeek().name() + ", " + time.getDayOfMonth() + "/" + time.getMonthValue() + "/" + time.getYear()));
        }
        if(message.contains("/users"))
            messageJSON = serverAddonsJson(chatServer.getUsers().toString());

        if(message.contains("/disconnect")){
            JsonReader jsonReader = Json.createReader(new StringReader(message));
            JsonObject messageJSO2 = jsonReader.readObject();
            messageJSON = serverAddonsJson(messageJSO2.getString("username") + " has disconnected");
            chatServer.getObserver().messageRecieved(messageJSON.toString());
            chatServer.getUsers().remove(messageJSO2.getString("username"));

        }
        return messageJSON.toString();
    }
    public void logConnection(){
        chatServer.getObserver().messageRecieved(serverLogsJson("Got a connection..").toString());
    }
    public void logPasswordCheck(boolean isValid){
        if(isValid)
            chatServer.getObserver().messageRecieved(serverLogsJson("Password check -> passed").toString());
        else
            chatServer.getObserver().messageRecieved(serverLogsJson("Password check -> failed").toString());

    }
    public void logConnectionCheck(){
        chatServer.getObserver().messageRecieved(serverLogsJson("Connecting check -> passed").toString());
    }

    public void Authenticate() throws AuthenticationError {
        if(!(chatServer.getPassword().isEmpty())){
            if(!(chatServer.clientPassword.equals(chatServer.getPassword())))
                throw new AuthenticationError("");
        }
    }
}

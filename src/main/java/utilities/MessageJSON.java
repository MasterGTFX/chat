package utilities;

import javax.json.*;
import java.util.Collections;

public class MessageJSON {
    private static JsonBuilderFactory builderFactory = Json.createBuilderFactory(Collections.emptyMap());
    public static JsonObject helloMessageJson(String username, String ipaddress, String password){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "helloMessage")
                .add("username", username)
                .add("ipaddress", ipaddress)
                .add("password", password).build();
        return message;
    }
    public static JsonObject helloMessageBotJson(String botName){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "botHelloMessage")
                .add("botName", botName).build();
        return message;
    }
    public static JsonObject botMessageJson(String botName, String body){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "botMessage")
                .add("message", body)
                .add("botName", botName).build();
        return message;
    }
    public static JsonObject toBotMessageJson(String username, String ipaddress, String botName, String body){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "toBotMessage")
                .add("message", body)
                .add("username", username)
                .add("ipaddress", ipaddress)
                .add("botName", botName).build();
        return message;
    }
    public static JsonObject botNotFound(String botName, String username){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "botNotFound")
                .add("username", username)
                .add("message", "there is no bot named " + botName).build();
        return message;
    }
    public static JsonObject botLogs(String botName, String body){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "botLogs")
                .add("botName", botName)
                .add("message", body).build();
        return message;
    }
    public static JsonObject messageJson(){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "")
                .add("username", "")
                .add("ipaddress", "")
                .add("message", "").build();
        return message;
    }
    public static JsonObject serverAddonsJson(String body){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "serverAddons")
                .add("message", body).build();
        return message;
    }
    public static JsonObject serverUsersJson(String body){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "usersOnline")
                .add("message", body).build();
        return message;
    }
    public static JsonObject serverLogsJson(String body){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "serverLogs")
                .add("message", body).build();
        return message;
    }
    public static JsonObject messageJson(String username, String ipaddress, String body){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "message")
                .add("username", username)
                .add("ipaddress", ipaddress)
                .add("message", body).build();
        return message;
    }
    public static JsonObject serverCheckJson(){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "serverCheck").build();
        return message;
    }
    public static JsonObject passwordCheckJson(String password){
        JsonObject message = builderFactory.createObjectBuilder()
                .add("messageType", "passwordCheck")
                .add("password", password).build();
        return message;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}

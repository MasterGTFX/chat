package client;

import model.Chat;

import javax.json.JsonObject;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import static java.lang.Thread.sleep;
import static utilities.MessageJSON.helloMessageJson;
import static utilities.MessageJSON.messageJson;

public class ChatClient extends Chat implements Runnable  {
    private String incomingMessages;
/*default one
                    data[0] , type
                    data[1] , username
                    data[2] , serverIP
                    data[3] , serverPort
                    data[4] , serverPassword
 */
    public ChatClient() {
        super();
        try {
            setSocket(new Socket("localhost", 1500));
            InputStreamReader streamReader = new InputStreamReader(getSocket().getInputStream());
            setIn(new BufferedReader(streamReader));
            setOut(new OutputStreamWriter(getSocket().getOutputStream(), StandardCharsets.UTF_8));
            JsonObject message = helloMessageJson(getUsername(),getMyIpAddress(), "");
            getOut().write(message.toString());
            getOut().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ChatClient(String[] data){
        super(data);
        try {
            setSocket(new Socket(data[2], Integer.parseInt(data[3])));
            setIn(new BufferedReader(new InputStreamReader(getSocket().getInputStream())));
            setOut(new OutputStreamWriter(getSocket().getOutputStream(), StandardCharsets.UTF_8));
            JsonObject message = helloMessageJson(getUsername(), getMyIpAddress(), getPassword());
            getOut().write(message + System.lineSeparator());
            getOut().flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   @Override
   public void chatSendingMessage(String body) {
        JsonObject message = messageJson(getUsername(),getMyIpAddress(),body);
        try{
            getOut().write(message.toString() + System.lineSeparator());
            getOut().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        Thread incomingMessages = new Thread(chatClient);
        incomingMessages.start();
    }

    @Override
    public void run() {
        try {
            while((incomingMessages = getIn().readLine().toString()) != null){
                if(getObserver() != null)
                    getObserver().messageRecieved(incomingMessages);
            }

        } catch (IOException e) {
            e.printStackTrace();
         }
    }
}

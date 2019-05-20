package gui;

import utilities.LogsWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static gui.TextAttributes.*;

public class GuiDocumentWriter {
    private static JsonObject messageJson;
    public static void printTime(JTextPane messageArea){
        LocalDateTime time = LocalDateTime.now();
        try {
            messageArea.getDocument().insertString(messageArea.getDocument().getLength(), "[" + time.getHour() + ":" + time.getMinute() + "]", getTimeAttributeSet());
        } catch (BadLocationException e){
            e.printStackTrace();
        }
    }
    public static void writeMessageClient(String message, JTextPane messageArea, JTextPane usersOnlineArea){
        JsonReader jsonReader = Json.createReader(new StringReader(message));
        messageJson = jsonReader.readObject();
        jsonReader.close();
        if(messageJson.getString("messageType").equals("message") || messageJson.getString("messageType").isEmpty()){
            try {
                printTime(messageArea);
                messageArea.getDocument().insertString(messageArea.getDocument().getLength(), messageJson.getString("username") + ": ", getUserNameAttributeSet());
                messageArea.getDocument().insertString(messageArea.getDocument().getLength(), messageJson.get("message") + "\n", getNormalAttributeSet());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(messageJson);
            }
        }
        else if(messageJson.getString("messageType").equals("helloMessage")){
            try {
                messageArea.getDocument().insertString(messageArea.getDocument().getLength(), messageJson.getString("username") + " has connected\n", getServerAttributeSet());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(messageJson);
            }
        }
        else if(messageJson.getString("messageType").equals("serverAddons")){
            try {
                messageArea.getDocument().insertString(messageArea.getDocument().getLength(), messageJson.getString("message") + "\n", getServerAddonsAttributeSet());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(messageJson);
            }
        }
        else if(messageJson.getString("messageType").equals("usersOnline")){
            usersOnlineArea.setText("");
            try {
                String[] users = messageJson.getString("message").replaceAll("\\[|\\]|\\s", "").split(",");
                for(String user:users){
                    usersOnlineArea.getDocument().insertString(usersOnlineArea.getDocument().getLength(), user + "\n", getLogsAttributeSet());
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }catch (Exception e){

                e.printStackTrace();
                System.out.println(messageJson);
            }
        }
        else{
            try {
                messageArea.getDocument().insertString(messageArea.getDocument().getLength(), messageJson.getString("message") + "\n", getNormalAttributeSet());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(messageJson);
            }
        }
    }
    public static void writeMessageServer(String message, JTextPane logsArea, LogsWriter logsWriter){
        JsonReader jsonReader = Json.createReader(new StringReader(message));
        messageJson = jsonReader.readObject();
        jsonReader.close();
        if(messageJson.getString("messageType").equals("serverAddons")){
            try {
                printTime(logsArea);
                logsArea.getDocument().insertString(logsArea.getDocument().getLength(), "[ChatAddons] " + messageJson.getString("message") + "\n", getLogsAttributeSet());
                logsWriter.writeLogs("[ChatAddons] " + messageJson.getString("message") + "\n");
            } catch (BadLocationException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(messageJson);
            }
        }
        if(messageJson.getString("messageType").equals("serverLogs")){
            try {
                printTime(logsArea);
                logsArea.getDocument().insertString(logsArea.getDocument().getLength(),   messageJson.getString("message") + "\n", getLogsAttributeSet());
                logsWriter.writeLogs(messageJson.getString("message") + "\n");
            } catch (BadLocationException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(messageJson);
            }
        }
        if(messageJson.getString("messageType").equals("helloMessage")){
            try {
                printTime(logsArea);
                logsArea.getDocument().insertString(logsArea.getDocument().getLength(),   messageJson.getString("username") + " has connected\n", getLogsAttributeSet());
                logsWriter.writeLogs(messageJson.getString("username") + " has connected\n");
            } catch (BadLocationException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(messageJson);
            }
        }
        if(messageJson.getString("messageType").equals("message")){
            try {
                printTime(logsArea);
                logsArea.getDocument().insertString(logsArea.getDocument().getLength(), messageJson.getString("username") + "[" + messageJson.getString("ipaddress") + "]: " + messageJson.getString("message") + "\n", getLogsAttributeSet());
                logsWriter.writeLogs(messageJson.getString("username") + "[" + messageJson.getString("ipaddress") + "]: " + messageJson.getString("message") + "\n");
            } catch (BadLocationException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(messageJson);
            }
        }
    }
}

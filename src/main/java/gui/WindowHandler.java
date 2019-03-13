package gui;

import gui.ChatClientGui;
import gui.ChatGUI;
import gui.ChatServerGui;
import gui.DialogWindows;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class WindowHandler {

    public static boolean hostAvailabilityCheck(String ip, int port) {
        try (Socket s = new Socket(ip, port)) {
            OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8);
            out.write("availabityCheck" + System.lineSeparator());
            out.flush();
            BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String response = read.readLine();
            s.close();
            return true;
        } catch (IOException ex) {
            /* ignore */
        }
        return false;
    }
    public static boolean passwordCheck(String ip, int port, String password){
        try (Socket s = new Socket(ip, port)) {
            OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
            out.write("passwordCheck" + System.lineSeparator() + password + System.lineSeparator());
            out.flush();
            BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String response = read.readLine();
            s.close();
            if(response.equals("true"))
                return true;
            if(response.equals("false"))
                return false;
            return true;
        } catch (IOException ex) {
            /* ignore */
        }
        return false;
    }
    public static void main(String[] args) {
        if(args.length>0) switch (args[0]) {
            case "client":
                if (!(hostAvailabilityCheck(args[2], Integer.parseInt(args[3])))) {
                    DialogWindows d = new DialogWindows("Error", "Coudnt connect to a server");
                    break;
                }
                if(!(passwordCheck(args[2], Integer.parseInt(args[3]), args[4]))){
                    DialogWindows d = new DialogWindows("Error", "Wrong password");
                    break;
                }
                ChatClientGui.main(args);
                break;
            case "server":
                ChatServerGui.main(args);
                break;
            default:
                ChatGUI.main(null);
                break;
        }
        else {
            ChatGUI.main(null);
        }
    }
}

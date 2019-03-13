package model;/*
TODO
basic
config
logsAttributeSet
KiK
 */
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.ArrayList;

public abstract class Chat implements Observable {
    private Socket socket;
    private int portNumber;
    private String myIpAddress;
    private String username;
    private String password;
    private final int defaultPort = 1500;
    private final String defaultIP = "localhost";
    private final String defaultUsername = "User";
    private BufferedReader in;
    private OutputStreamWriter out;
    private ArrayList<String> users;
    private Observer observer;

    public Chat() {
        users = new ArrayList<String>();
        portNumber = defaultPort;
        myIpAddress = defaultIP;
        username = defaultUsername;
        password = "123";
        try{
            InetAddress inetAddress = InetAddress.getLocalHost();
            myIpAddress = inetAddress.getHostAddress();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Chat(String[] data){
        users = new ArrayList<String>();
        this.username = data[1];
        this.portNumber = Integer.parseInt(data[3]);
        this.password = data[4];
        try{
            InetAddress inetAddress = InetAddress.getLocalHost();
            this.myIpAddress = inetAddress.getHostAddress();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void subscribe(Observer o) {
        observer = o;
    }

    @Override
    public void unsubscribe(Observer o) {
        if(observer != null)
           observer = null;
    }
    @Override
    public void chatSendingMessage(String message) {

    }

    public Observer getObserver() {
        return observer;
    }

    public int getDefaultPort() {
        return defaultPort;
    }

    public String getDefaultIP() {
        return defaultIP;
    }

    public String getDefaultUsername() {
        return defaultUsername;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getMyIpAddress() {
        return myIpAddress;
    }


    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public OutputStreamWriter getOut() {
        return out;
    }

    public void setOut(OutputStreamWriter out) {
        this.out = out;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

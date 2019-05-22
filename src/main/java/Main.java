import javax.json.*;

import static utilities.MessageJSON.helloMessageBotJson;


public class Main {
    public static void main(String[] args) {
        JsonObject s = helloMessageBotJson("alex");
        String ss = s.toString();
        System.out.println(s);
        System.out.println(ss.substring(0, ss.indexOf(",")));
        System.out.println(ss.indexOf("botName"));
        String ss2 = ss.substring(ss.indexOf("botName")+10);
        System.out.println(ss2);
        String ss3 = ss2.substring(0,ss2.indexOf("\""));
        System.out.println(ss3);
        ss2 = ss.substring(ss.indexOf("botName")+10);
        System.out.println(ss2.substring(0,ss2.indexOf("\"")));
    }
}

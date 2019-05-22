package gui;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class TextAttributes {
    public static SimpleAttributeSet userNameAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet botNameAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet normalAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet serverInfoAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet normalBotAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet serverAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet logsAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet botLogsAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet timeAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet serverAddonsAttributeSet = new SimpleAttributeSet();

    static {
        StyleConstants.setFontFamily(userNameAttributeSet, "Calibri");
        StyleConstants.setFontFamily(botNameAttributeSet, "Calibri");
        StyleConstants.setFontFamily(normalAttributeSet, "Calibri");
        StyleConstants.setFontFamily(serverInfoAttributeSet, "Calibri");
        StyleConstants.setFontFamily(normalBotAttributeSet, "Calibri");
        StyleConstants.setFontFamily(serverAttributeSet, "Calibri");
        StyleConstants.setFontFamily(serverAddonsAttributeSet, "Calibri");
        StyleConstants.setFontFamily(logsAttributeSet, "Consolas");
        StyleConstants.setFontFamily(timeAttributeSet, "Consolas");
        StyleConstants.setFontFamily(botLogsAttributeSet, "Consolas");
        StyleConstants.setForeground(userNameAttributeSet, Color.BLACK);
        StyleConstants.setForeground(botNameAttributeSet, Color.LIGHT_GRAY);
        StyleConstants.setForeground(normalBotAttributeSet, Color.BLACK);
        StyleConstants.setForeground(normalAttributeSet, Color.BLACK);
        StyleConstants.setForeground(serverInfoAttributeSet, Color.BLACK);
        StyleConstants.setForeground(serverAttributeSet, Color.RED);
        StyleConstants.setForeground(timeAttributeSet, Color.lightGray);
        StyleConstants.setForeground(serverAddonsAttributeSet, Color.RED);
        StyleConstants.setForeground(botLogsAttributeSet, Color.BLUE);
        StyleConstants.setBold(userNameAttributeSet, true);
        StyleConstants.setBold(botNameAttributeSet, true);
        StyleConstants.setBold(serverInfoAttributeSet, true);
        StyleConstants.setBold(serverAttributeSet, true);
        StyleConstants.setItalic(logsAttributeSet, true);
        StyleConstants.setItalic(botLogsAttributeSet, true);
        StyleConstants.setItalic(serverInfoAttributeSet, true);
        StyleConstants.setItalic(normalBotAttributeSet, true);
        StyleConstants.setFontSize(userNameAttributeSet, 16);
        StyleConstants.setFontSize(botNameAttributeSet, 16);
        StyleConstants.setFontSize(normalAttributeSet, 14);
        StyleConstants.setFontSize(serverInfoAttributeSet, 14);
        StyleConstants.setFontSize(normalBotAttributeSet, 14);
        StyleConstants.setFontSize(serverAttributeSet, 16);
        StyleConstants.setFontSize(serverAddonsAttributeSet, 16);
        StyleConstants.setFontSize(logsAttributeSet, 12);
        StyleConstants.setFontSize(timeAttributeSet, 10);
        StyleConstants.setFontSize(botLogsAttributeSet, 12);
    }

    public static SimpleAttributeSet getServerInfoAttributeSet() {
        return serverInfoAttributeSet;
    }

    public static SimpleAttributeSet getUserNameAttributeSet() {
        return userNameAttributeSet;
    }

    public static SimpleAttributeSet getNormalAttributeSet() {
        return normalAttributeSet;
    }

    public static SimpleAttributeSet getServerAttributeSet() {
        return serverAttributeSet;
    }

    public static SimpleAttributeSet getBotNameAttributeSet() {
        return botNameAttributeSet;
    }

    public static SimpleAttributeSet getNormalBotAttributeSet() {
        return normalBotAttributeSet;
    }

    public static SimpleAttributeSet getLogsAttributeSet() {
        return logsAttributeSet;
    }

    public static SimpleAttributeSet getTimeAttributeSet() {
        return timeAttributeSet;
    }

    public static SimpleAttributeSet getServerAddonsAttributeSet() {
        return serverAddonsAttributeSet;
    }

    public static SimpleAttributeSet getBotLogsAttributeSet() {
        return botLogsAttributeSet;
    }
}

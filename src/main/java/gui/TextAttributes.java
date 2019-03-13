package gui;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class TextAttributes {
    public static SimpleAttributeSet userNameAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet normalAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet serverAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet logsAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet timeAttributeSet = new SimpleAttributeSet();
    public static SimpleAttributeSet serverAddonsAttributeSet = new SimpleAttributeSet();
    static {
        StyleConstants.setFontFamily(userNameAttributeSet, "Calibri");
        StyleConstants.setFontFamily(normalAttributeSet, "Calibri");
        StyleConstants.setFontFamily(serverAttributeSet, "Calibri");
        StyleConstants.setFontFamily(serverAddonsAttributeSet, "Calibri");
        StyleConstants.setFontFamily(logsAttributeSet, "Consolas");
        StyleConstants.setFontFamily(timeAttributeSet, "Consolas");
        StyleConstants.setForeground(userNameAttributeSet, Color.BLACK);
        StyleConstants.setForeground(normalAttributeSet, Color.BLACK);
        StyleConstants.setForeground(serverAttributeSet, Color.RED);
        StyleConstants.setForeground(timeAttributeSet, Color.lightGray);
        StyleConstants.setForeground(serverAddonsAttributeSet, Color.CYAN);
        StyleConstants.setBold(userNameAttributeSet, true);
        StyleConstants.setBold(serverAttributeSet, true);
        StyleConstants.setBold(serverAddonsAttributeSet, true);
        StyleConstants.setItalic(logsAttributeSet, true);
        StyleConstants.setFontSize(userNameAttributeSet, 16);
        StyleConstants.setFontSize(normalAttributeSet, 14);
        StyleConstants.setFontSize(serverAttributeSet, 16);
        StyleConstants.setFontSize(serverAddonsAttributeSet, 16);
        StyleConstants.setFontSize(logsAttributeSet, 12);
        StyleConstants.setFontSize(timeAttributeSet, 10);
    }
    public static SimpleAttributeSet getUserNameAttributeSet() {
        return userNameAttributeSet;
    }
    public static SimpleAttributeSet getNormalAttributeSet() {
        return normalAttributeSet;
    }
    public static SimpleAttributeSet getServerAttributeSet() { return serverAttributeSet; }
    public static SimpleAttributeSet getLogsAttributeSet() { return logsAttributeSet; }
    public static SimpleAttributeSet getTimeAttributeSet() {
        return timeAttributeSet;
    }
    public static SimpleAttributeSet getServerAddonsAttributeSet() {
        return serverAddonsAttributeSet;
    }
}

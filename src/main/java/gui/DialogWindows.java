package gui;

import javax.swing.*;
import java.awt.*;

public class DialogWindows extends JFrame {
    public DialogWindows(String tittle, String message) throws HeadlessException {
        JOptionPane.showMessageDialog(this,
                message,
                tittle,
                JOptionPane.ERROR_MESSAGE);
    }
    public DialogWindows(String tittle, String message, int typ) throws HeadlessException {
        switch (typ){
            case 1:
                JOptionPane.showMessageDialog(this,
                        message,
                        tittle,
                        JOptionPane.ERROR_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(this,
                        message,
                        tittle,
                        JOptionPane.WARNING_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(this,
                        message,
                        tittle,
                        JOptionPane.QUESTION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(this,
                        message,
                        tittle,
                        JOptionPane.INFORMATION_MESSAGE);
                break;
        }

    }

}

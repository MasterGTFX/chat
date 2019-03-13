package model;

public interface Observable {
    public void subscribe(Observer o);
    public void unsubscribe(Observer o);
    public void chatSendingMessage(String message);
}

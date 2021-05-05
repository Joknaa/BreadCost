package Presenters.Client;

public interface MessageListener {
    public void onMessage(String fromLogin, String msgBody);
}

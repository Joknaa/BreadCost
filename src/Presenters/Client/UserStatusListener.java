package Presenters.Client;

public interface UserStatusListener {
    public void online(String login);
    public void offline(String login);
}

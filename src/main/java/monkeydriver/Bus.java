package monkeydriver;

public interface Bus {
    void subscribe(Subscriber subscriber);

    void send(Message message);
}

package lexicon.workshop;

@FunctionalInterface
public interface SubscriberAction {
    void run(Subscriber subscriber);
}

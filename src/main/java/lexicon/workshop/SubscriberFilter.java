package lexicon.workshop;

@FunctionalInterface
public interface SubscriberFilter {
    boolean  matches(Subscriber subscriber);
}

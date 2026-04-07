package lexicon.workshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriberDAOImplementation implements SubscriberDAO {

    public List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void save(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public List<Subscriber> findAll() {
        return subscribers;
    }

    @Override
    public Optional<Subscriber> findById(int id) {
        return subscribers.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }
}

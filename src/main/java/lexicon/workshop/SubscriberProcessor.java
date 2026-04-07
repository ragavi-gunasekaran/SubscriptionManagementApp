package lexicon.workshop;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriberProcessor {

     public List<Subscriber> findSubscribers(List<Subscriber> list, SubscriberFilter filter){
         return list.stream()
                 .filter(s -> filter.matches(s))
                 .toList();
     }

    public List<Subscriber> applyToMatching(List<Subscriber> list, SubscriberFilter filter,SubscriberAction action){
         return list.stream()
                .filter(s -> filter.matches(s))
                .peek(s -> action.run(s))
                .toList();
    }
}

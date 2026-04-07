package lexicon.workshop;

public class SubscriptionManagementApp {

    static SubscriberDAO subscriberDAO = new SubscriberDAOImplementation();

    static void main() {

      //adding the subscribers to the list
        subscriberDAO.save(new Subscriber(1, 3, true,Plan.PRO, "ragavi@lexicon.com"));
        subscriberDAO.save(new Subscriber(2, 2,true, Plan.FREE, "shanmu@lexicon.com"));
        subscriberDAO.save(new Subscriber(3, 1,false, Plan.PRO, "sikdar@lexicon.com"));
        subscriberDAO.save(new Subscriber(4, 7, false, Plan.BASIC, "muthana@lexicon.com"));

        //1. Active Subscriber
        SubscriberFilter activeSubscriber = s -> s.isActive();

        //2. Expiring Subscription
        SubscriberFilter expiringSubscriber = s -> (s.getMonthsRemaining() <= 1 || s.getMonthsRemaining() <= 0 );

        //3. Active and Expiring Subscriber
        SubscriberFilter activeAndExpiringSubscriber = s -> (s.getMonthsRemaining() <= 1 && s.isActive());


    }
}

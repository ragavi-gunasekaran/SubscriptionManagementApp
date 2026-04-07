package lexicon.workshop;

import java.util.List;

public class SubscriptionManagementApp {

    static SubscriberDAO subscriberDAO = new SubscriberDAOImplementation();
    static List<Subscriber> listOfSubscribers = subscriberDAO.findAll();
    static SubscriberProcessor processor = new SubscriberProcessor();

    static void main() {

      //adding the subscribers to the list
        subscriberDAO.save(new Subscriber(1, 3, true,Plan.PRO, "ragavi@lexicon.com"));
        subscriberDAO.save(new Subscriber(2, 2,true, Plan.FREE, "shanmu@lexicon.com"));
        subscriberDAO.save(new Subscriber(3, 1,false, Plan.PRO, "sikdar@lexicon.com"));
        subscriberDAO.save(new Subscriber(4, 7, false, Plan.BASIC, "muthana@lexicon.com"));

        //1. Active Subscriber
        SubscriberFilter activeSubscriber = s -> s.isActive();
        System.out.println("List of Active Users :");
        displayList(processor.findSubscribers(listOfSubscribers, activeSubscriber));

        //2. Expiring Subscription
        SubscriberFilter expiringSubscriber = s -> (s.getMonthsRemaining() <= 1 || s.getMonthsRemaining() <= 0 );
        System.out.println("\nList of Subscribers whose plan is to get expired:");
        displayList(processor.findSubscribers(listOfSubscribers, expiringSubscriber));

        //3. Active and Expiring Subscriber
        SubscriberFilter activeAndExpiringSubscriber = s -> (s.getMonthsRemaining() <= 1 && s.isActive());
        displayList(processor.findSubscribers(listOfSubscribers, activeAndExpiringSubscriber));

        // 4. Subscriber by Plan
        SubscriberFilter basicPlanSubscribers = s -> s.getPlan() == Plan.BASIC;
        System.out.println("\nList of BASIC plan Subscribers:");
        displayList(processor.findSubscribers(listOfSubscribers, basicPlanSubscribers));

        // 5. Paying Subscribers
        SubscriberFilter payingSubscribers =s -> s.getPlan() == Plan.BASIC || s.getPlan() == Plan.PRO;
        System.out.println("\nList of Subscribers who is Paying and not FREE:");
        displayList(processor.findSubscribers(listOfSubscribers, payingSubscribers));

        // 6. Extend Subscription
        SubscriberAction extend = s -> s.setMonthsRemaining(s.getMonthsRemaining() + 3);
        processor.applyToMatching(listOfSubscribers, payingSubscribers, extend);
        System.out.println("\nIncreased Subscription for the subscribers:");
        displayList(listOfSubscribers);

        // 7. Deactivate expired users
        SubscriberAction deactivateSubscriber = s -> s.setActive(false);
        processor.applyToMatching(listOfSubscribers, s -> s.getMonthsRemaining() == 0, deactivateSubscriber);
        SubscriberFilter inactiveSubscriber = s -> !s.isActive();
        System.out.println("\nList of inactive Subscribers:");
        displayList(processor.findSubscribers(listOfSubscribers, inactiveSubscriber));
    }

    static void displayList(List<Subscriber> list){
            for (Subscriber s: list){
                System.out.println(s);
            }
    }
}

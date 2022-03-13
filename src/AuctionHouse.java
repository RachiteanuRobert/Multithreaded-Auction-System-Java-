import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Contains all the lists used by the program and is the main communication
 * point of all classes.
 */
public class AuctionHouse {
    private static AuctionHouse obj;

    //Initialize lists
    private List<Product> products = new CopyOnWriteArrayList<Product>();
    private ArrayList<Client> clients = new ArrayList<>();
    private ArrayList<Auction> activeAuctions = new ArrayList<>();
    private final ArrayList<Broker> brokers = new ArrayList<>();
    private int numberOfAuctions = 0;

    public synchronized int getNumberOfAuctions() {
        return numberOfAuctions;
    }

    public synchronized void setNumberOfAuctions(int numberOfAuctions) {
        this.numberOfAuctions = numberOfAuctions;
    }

    public synchronized static AuctionHouse getInstance(){
        if(obj == null)
            obj = new AuctionHouse();
        return obj;
    }

    public synchronized List<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public synchronized ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public synchronized ArrayList<Auction> getActiveAuctions() {
        return activeAuctions;
    }

    public void setActiveAuctions(ArrayList<Auction> activeAuctions) {
        this.activeAuctions = activeAuctions;
    }

    public synchronized ArrayList<Broker> getBrokers() {
        return brokers;
    }

    /**
     * Generate random number of brokers
     */
    public synchronized void initializeBrokers(){
        int numberOfBrokers = (int)(Math.random() * (4 - 2 + 1) + 2);
        Broker newBroker;
        for(int i = 0; i < numberOfBrokers; i++) {
            newBroker = new Broker();
            brokers.add(newBroker);
        }
        System.out.println("Brokers initialized: " + numberOfBrokers);
    }

    /**
     * Calculates the maximum bid offer of the current step of the auction
     * @param bidRound
     * @return
     */
    public synchronized Bid calculateMaxBid(ArrayList<Bid> bidRound){
        double maxPrice = 0;
        Bid maxBid = null;

        for(Bid bid: bidRound) {
            if (bid.getCurrentPrice() > maxPrice) {
                maxPrice = bid.getCurrentPrice();
                maxBid = bid;
            }
        }
        return maxBid;
    }
}

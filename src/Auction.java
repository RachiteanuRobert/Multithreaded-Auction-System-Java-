import java.util.ArrayList;

/**
 * Implements the run method, which creates a thread for the current auction.
 */
public class Auction implements Runnable{
    AuctionHouse auctionHouse = AuctionHouse.getInstance();

    private int id;
    private int maxParticipants;
    private int productId;
    //Auctions have 3 steps
    private int maxStep = 3;
    private int currentStep = 0;
    private int currentParticipants = 1;
    private ArrayList<Bid> bidRound;
    private Product currentProduct;

    public synchronized Product getCurrentProduct() {
        return currentProduct;
    }

    public synchronized void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    public ArrayList<Bid> getBidRound() {
        return bidRound;
    }

    public void setBidRound(ArrayList<Bid> bidRound) {
        this.bidRound = bidRound;
    }

    public synchronized int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    public Auction(int id, int productId, int maxParticipants , int maxStep) {
        this.id = id;
        this.maxParticipants = maxParticipants;
        this.productId = productId;
        this.maxStep = maxStep;
    }

    public synchronized int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public synchronized int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public synchronized int getMaxStep() {
        return maxStep;
    }

    public void setMaxStep(int maxStep) {
        this.maxStep = maxStep;
    }

    public synchronized boolean increaseParticipants(){
        this.currentParticipants += 1;
        return currentParticipants == maxParticipants;
    }

    public synchronized void increaseStep(){
        this.currentStep += 1;
    }

    /**
     * Method which for each step of the auction calls the methods which
     * generate the prices of each client, gets the maximum value and decides
     * whether or not the auction is completed successfully.
     */
    @Override
    public synchronized void run(){
        Client winningClient;
        double maxPrice = 0;
        double priceOffered = 0;
        Bid newBid;
        Bid maxBid = null;

        System.out.println("---Auction[" + getId()
                + "] Has Started. It Has " + getMaxStep()
                + " Steps---");

        while(getCurrentStep() < (getMaxStep() - 1)){
            //Bids reset each round
            bidRound = new ArrayList<>();

            //Verifies if brokers have clients that are participating in this
            //auction
            for (Broker broker : auctionHouse.getBrokers()) {
                for (Client client : broker.getClientList()) {
                    client.increaseNumberOfParticipation();
                    //Generates offered price
                    priceOffered = client.generateBidPrice(getId(), maxPrice);

                    if (priceOffered != -1) {
                        newBid = new Bid(getId(), priceOffered, client.getId());
                        bidRound.add(newBid);
                        System.out.println("Auction[" + getId()
                                + "] (Round " + (getCurrentStep() + 1)
                                +"): Client[" + client.getId()
                                + "] bids " + priceOffered );
                        try {
                            Thread.sleep(900);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            //Gets the biggest price for the round
            maxBid = auctionHouse.calculateMaxBid(bidRound);
            maxPrice = maxBid.getCurrentPrice();
            System.out.println("---Auction[" + getId()
                   +"] Round " + (getCurrentStep() + 1)
                    + " Ended: Maximum price for this round:" + maxPrice
                    + "---");
            increaseStep();
        }

        //Minimum price not achieved
        if(maxBid.getCurrentPrice() < getCurrentProduct().getMinPrice()){
            System.out.println("---Auction[" + getId()
                    + "] canceled. Minimum price was not met---");
            auctionHouse.getProducts().add(getCurrentProduct());
        }
        else{
            for(Broker broker: auctionHouse.getBrokers()) {
                winningClient = broker.clientById(maxBid.getClientId());

                //Gets the client that won the auction
                if ((winningClient != null) &&
                (winningClient.getId() == maxBid.getClientId())) {
                    System.out.println("---Auction[" + getId()
                            + "] Ended: " + winningClient.getName()
                            + "[" + winningClient.getId()
                            +"] Won " + getCurrentProduct().getName()
                            +"[" + getCurrentProduct().getId()
                            + "]!---");
                    for (Bid bid : winningClient.getBids()) {
                        if (bid.getCurrentPrice() ==
                                maxBid.getCurrentPrice()) {
                            winningClient.setWonAuctions(
                                    winningClient.getWonAuctions() + 1);
                            break;
                        }
                    }
                }
            }
        }
    }
}

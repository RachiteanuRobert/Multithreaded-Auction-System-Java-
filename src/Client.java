import java.util.ArrayList;

/**
 * Contains all the specific identifiers for a client object and also
 * the bids it partakes in.
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private int nrOfParticipation  = 0;
    private int wonAuctions = 0;
    private ArrayList<Bid> bids = new ArrayList<>();

    public synchronized ArrayList<Bid> getBids() {
        return bids;
    }

    public void setBids(ArrayList<Bid> bids) {
        this.bids = bids;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized String getAddress() {
        return address;
    }

    public synchronized void setAddress(String address) {
        this.address = address;
    }

    public synchronized int getNrOfParticipation() {
        return nrOfParticipation;
    }

    public synchronized void setNrOfParticipation(int nrOfParticipation) {
        this.nrOfParticipation = nrOfParticipation;
    }

    public synchronized int getWonAuctions() {
        return wonAuctions;
    }

    public synchronized void setWonAuctions(int wonAuctions) {
        this.wonAuctions = wonAuctions;
    }

    public synchronized void setDateOfBirth(String dateOfBirth){ }

    public synchronized void setLastName(String lastName) {}

    public synchronized void setType(String type){}

    public synchronized void setSocialCapital(String socialCapital){}

    public synchronized void addBid(int auctionIndex, double maxPrice){
        Bid newBid = new Bid(auctionIndex, maxPrice, getId());
        bids.add(newBid);
    }

   //

    /**
     * Algorithm for generating bid value if the client participates in the
     *  auction, else return -1
     * @param auctionId the id of the current auction
     * @param lastPrice last offer for said product
     * @return a random price if possible else -1
     */
   public synchronized double generateBidPrice(int auctionId,
                                               double lastPrice){
        double price;
        for(Bid i: bids){
            if(i.getAuctionId() == auctionId) {
                //Client doesn't have enough currency to participate
                if(lastPrice > i.getMaxPrice()) {
                    return -1;
                }
                price = Math.random() * (i.getMaxPrice() - lastPrice) +
                        lastPrice;
                i.setCurrentPrice(price);
                return price;
            }
        }
        return -1;
   }

   public synchronized void increaseNumberOfParticipation(){
        this.nrOfParticipation += 1;
   }
}

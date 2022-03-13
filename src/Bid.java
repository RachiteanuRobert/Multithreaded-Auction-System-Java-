/**
 * Contains the structure of a bid offer
 */
public class Bid {
    private int auctionId;
    private double maxPrice = 0;
    private int clientId;
    private double currentPrice;

    public Bid(int auctionId, double currentPrice, int clientId) {
        this.auctionId = auctionId;
        this.currentPrice = currentPrice;
        this.clientId = clientId;
    }

    public synchronized void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public synchronized double getCurrentPrice() {
        return currentPrice;
    }

    public synchronized void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public synchronized int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionIndex) {
        this.auctionId = auctionIndex;
    }

    public synchronized double getMaxPrice() {
        return maxPrice;
    }

    public synchronized int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

}

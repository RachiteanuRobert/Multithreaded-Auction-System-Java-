import java.util.ArrayList;
import java.util.List;

/**
 * Acts as communication point between interface and driver code.
 */
public class Facade {
    /**
     * Creates a painting object and adds it to its list.
     * @param command line of input
     */
    public synchronized void addPainting(String[] command){
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        MaterialType type;
        if(command[8].equals("OIL"))
            type = MaterialType.OIL;
        else if(command[8].equals("ACRYLIC"))
            type = MaterialType.ACRYLIC;
        else
            type = MaterialType.TEMPERA;

        Painting painting = new PaintingBuilder()
                .withId(Integer.parseInt(command[2]))
                .withName(command[3])
                .withSalePrice(Double.parseDouble(command[4]))
                .withMinPrice(Double.parseDouble(command[5]))
                .withYear(Integer.parseInt(command[6]))
                .withArtist(command[7])
                .withType(type)
                .build();

        auctionHouse.getProducts().add(painting);
    }

    /**
     * Creates a jewelry object and adds it to its list.
     * @param command line of input
     */
    public synchronized void addJewelry(String[] command){
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        boolean withGem = false;
        if(Integer.parseInt(command[8]) == 1)
            withGem = true;

        Jewelry jewelry = new JewelryBuilder()
                .withId(Integer.parseInt(command[2]))
                .withName(command[3])
                .withSalePrice(Double.parseDouble(command[4]))
                .withMinPrice(Double.parseDouble(command[5]))
                .withYear(Integer.parseInt(command[6]))
                .withMaterial(command[7])
                .withWithGemstone(withGem)
                .build();

        auctionHouse.getProducts().add(jewelry);
    }

    /**
     * Creates a furniture object and adds it to its list.
     * @param command line of input
     */
    public synchronized void addFurniture(String[] command){
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        Furniture furniture = new FurnitureBuilder()
                .withId(Integer.parseInt(command[2]))
                .withName(command[3])
                .withSalePrice(Double.parseDouble(command[4]))
                .withMinPrice(Double.parseDouble(command[5]))
                .withYear(Integer.parseInt(command[6]))
                .withType(command[7])
                .withMaterial(command[8])
                .build();

        auctionHouse.getProducts().add(furniture);
    }

    public synchronized void addProduct(String[] command){
        if(command[1].equals("Painting"))
            addPainting(command);
        else if(command[1].equals("Jewelry"))
            addJewelry(command);
        else addFurniture(command);
    }

    /**
     * Creates a client object and adds it to its list.
     * @param command line of input
     */
    public synchronized void addClient(String[] command){
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.makeClient(command[1]);

        client.setId(Integer.parseInt(command[2]));
        client.setName(command[3]);
        client.setLastName(command[4]);
        client.setType(command[4]);
        client.setDateOfBirth(command[5]);
        client.setSocialCapital(command[5]);
        client.setAddress(command[6]);

        auctionHouse.getClients().add(client);
    }

    /**
     * Places an offer for the said product in the auction house. If an auction
     * is already available, it increases the number of participants, otherwise
     * it creates one.
     * @param command input line
     */
    public synchronized void solicitBid(String[] command){
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        Auction auction = null;
        Bid newBid;
        Product currentProduct = null;
        int randomMaxParticipants;
        int auctionId;
        //Generates random number of brokers
        int randomBroker = (int)(Math.random() *
                (auctionHouse.getBrokers().size() - 1 + 1) + 1);

        boolean ok = false;
        boolean hasProduct = false;

        //Checks if the auction house has the specified product
        for(Product p: auctionHouse.getProducts()) {
            if (p.getId() == Integer.parseInt(command[2])) {
                hasProduct = true;
                currentProduct = p;
            }
        }
        if(!hasProduct){
            System.out.println("Invalid command. Product not found");
            return;
        }

        //Checks if the auction for the specified product id is active
        for(Auction a: auctionHouse.getActiveAuctions()) {
            if (a.getProductId() == Integer.parseInt(command[2])) {
                auction = a;
                ok = true;
                break;
            }
        }

        //Gets the client with the id specified in the command
        for(Client i: auctionHouse.getClients()) {
            if (i.getId() == Integer.parseInt(command[1])) {
                if(ok) {
                    auctionId = auction.getId();
                } else {
                    auctionId = auctionHouse.getNumberOfAuctions();
                }

                //Makes a new bid for said client
                newBid = new Bid(auctionId, 0, i.getId());
                newBid.setMaxPrice(Double.parseDouble(command[3]));
                i.getBids().add(newBid);

                //Assigns the found client to a random broker
                auctionHouse.getBrokers().get(randomBroker - 1).
                        getClientList().add(i);
                break;
            }
        }

        //If the auction with the specified id is not found, create one
        if(!ok) {
            //Initializes the maximum number of participants with a random
            // value
            randomMaxParticipants = (int)(Math.random() *
                    (4 - 2 + 1) + 2);
            System.out.println("randomMaxParticipants = " +
                    randomMaxParticipants);
            //Initializes auction with 3 steps
            auction = new Auction(auctionHouse.getNumberOfAuctions(),
                    Integer.parseInt(command[2]),
                    randomMaxParticipants,
                    4);
            auctionHouse.setNumberOfAuctions(auctionHouse.
                    getNumberOfAuctions() + 1);
            auctionHouse.getActiveAuctions().add(auction);
        }
        else{
            //Starts auction thread and removes the auction and product from
            // their lists
            if(auction.increaseParticipants()) {
                auctionHouse.getActiveAuctions().remove(auction);
                auction.setCurrentProduct(currentProduct);
                auctionHouse.getProducts().remove(currentProduct);
                Thread thread = new Thread(auction);
                thread.start();
            }
        }
    }

    /**
     * Generates the brokers.
     */
    public synchronized void makeBrokers(){
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        auctionHouse.initializeBrokers();
    }

    /**
     * Calls a printing method depending on the object type specified in the
     * input.
     * @param typeOfList the type of list that can be printed
     */
    public synchronized void listCommand(String typeOfList){
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        switch (typeOfList) {
            case "products" -> {
                System.out.println("---Product List---");
                printList(auctionHouse.getProducts());
            }
            case "clients" -> {
                System.out.println("---Client List---");
                printList(auctionHouse.getClients());
            }
            case "brokers" -> {
                System.out.println("---Broker List---");
                printList(auctionHouse.getBrokers());
            }
            default -> System.out.println("Invalid command. List not found.");
        }
    }

    /**
     * Prints each element of the list regardless of its type
     * @param x the list
     * @param <T> Product, Broker, Client
     */
    public synchronized static <T> void printList(List<T> x){
        for(T element: x){
            System.out.println(element);
        }
    }

}

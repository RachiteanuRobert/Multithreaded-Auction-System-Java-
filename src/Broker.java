import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Contains the object which manages the clients by a client list
 */
public class Broker {
    private List<Client> clientList = new CopyOnWriteArrayList<>();

    public synchronized List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }

    /**
     * Returns the client object by its specified id
     * @param clientId, client id
     * @return the client if it exists, else null
     */
    public synchronized Client clientById(int clientId){
        for(Client client: getClientList()){
            if(client.getId() == clientId) {
                return client;
            }
        }
        return null;
    }
}

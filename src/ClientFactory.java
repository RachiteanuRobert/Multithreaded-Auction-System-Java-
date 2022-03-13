/**
 * Creates new client depending on the type
 */
public class ClientFactory {
    public synchronized Client makeClient(String type){
        Client newClient = null;

        if(type.equals("Private Person")){
            return new PrivatePerson();
        }
        else if(type.equals("Legal Entity")){
                return new LegalEntity();
            }
        else return null;

    }
}

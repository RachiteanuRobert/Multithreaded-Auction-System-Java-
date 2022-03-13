/**
 * Subclass of Client that represents the Private Person type.
 */
public class PrivatePerson extends Client {
    private String dateOfBirth;
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    @Override
    public synchronized void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public synchronized String getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public synchronized void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public synchronized String toString(){
        return "Client[" + getId()
                + "] - Name: " + getName()
                + " (" + getDateOfBirth()
                + ", Address: " + getAddress()
                + "); ";
    }
}

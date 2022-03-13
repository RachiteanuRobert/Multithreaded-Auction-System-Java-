/**
 * Subclass of Client that represents the Legal Entity type.
 */
public class LegalEntity extends Client{
    private TypeOfCompany type;
    private double socialCapital;

    public TypeOfCompany getType() {
        return type;
    }

    @Override
    public synchronized void setType(String type) {
        if(type.equals("SRL"))
            this.type = TypeOfCompany.SRL;
        else
            this.type = TypeOfCompany.SA;
    }

    public synchronized double getSocialCapital() {
        return socialCapital;
    }

    @Override
    public synchronized void setSocialCapital(String socialCapital) {
        this.socialCapital = Double.parseDouble(socialCapital);
    }

    @Override
    public synchronized String toString(){
        return "Client[" + getId()
                + "] - Name: " + getName()
                + " (" + getType()
                + " - " + getSocialCapital()
                + ", Address: " + getAddress()
                + "); ";
    }
}

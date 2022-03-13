/**
 * Subclass of Product that represents the Furniture type.
 */
public class Furniture extends Product{
    public String type;
    public String material;

    @Override
    public String toString() {
        return "Product[" + getId()
                + "] - Furniture: \"" + getName()
                + "\"(" + getYear()
                + ") - " + getType()
                + " (Materials Used: " + getMaterial()
                + "); Starting Price: " + getMinPrice()
                + "; Last Sale Price: " + getSalePrice()
                + ";";
    }

    public synchronized String getType() {
        return type;
    }

    public synchronized void setType(String type) {
        this.type = type;
    }

    public synchronized String getMaterial() {
        return material;
    }

    public synchronized void setMaterial(String material) {
        this.material = material;
    }
}

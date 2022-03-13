/**
 * Subclass of Product that represents the Jewelry type.
 */
public class Jewelry extends Product{
    public String material;
    public boolean withGemstone;

    @Override
    public String toString() {
        return "Product[" + getId()
                + "] - Jewelry: \"" + getName()
                + "\"(" + getYear()
                + ") (Materials Used: " + getMaterial()
                + ", With Gemstone: " + isWithGemstone()
                + "); Starting Price: " + getMinPrice()
                + "; Last Sale Price: " + getSalePrice()
                + ";";
    }

    public synchronized String getMaterial() {
        return material;
    }

    public synchronized void setMaterial(String material) {
        this.material = material;
    }

    public synchronized boolean isWithGemstone() {
        return withGemstone;
    }

    public synchronized void setWithGemstone(boolean withGemstone) {
        this.withGemstone = withGemstone;
    }
}

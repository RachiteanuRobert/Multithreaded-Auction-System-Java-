/**
 * Subclass of Product that represents the Painting type.
 */
public class Painting extends Product{
    private String artist;
    private MaterialType type;

    @Override
    public synchronized String toString() {
        return "Product[" + getId()
                + "] - Painting: \"" + getName()
                + "\"(" + getYear()
                + ") by " + getArtist()
                + " (Materials Used: " + getType()
                + "); Starting Price: " + getMinPrice()
                + "; Last Sale Price: " + getSalePrice()
                + ";";
    }

    public synchronized String getArtist() {
        return artist;
    }

    public synchronized void setArtist(String artist) {
        this.artist = artist;
    }

    public MaterialType getType() {
        return type;
    }

    public synchronized void setType(MaterialType type) {
        this.type = type;
    }
}

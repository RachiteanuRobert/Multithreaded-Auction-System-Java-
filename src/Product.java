/**
 * Primitive class that represents a Product type object.
 */
public class Product {
    private int id;
    private String name;
    private double salePrice;
    private double minPrice;
    private int year;

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

    public synchronized double getSalePrice() {
        return salePrice;
    }

    public synchronized void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public synchronized double getMinPrice() {
        return minPrice;
    }

    public synchronized void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public synchronized int getYear() {
        return year;
    }

    public synchronized void setYear(int year) {
        this.year = year;
    }

}

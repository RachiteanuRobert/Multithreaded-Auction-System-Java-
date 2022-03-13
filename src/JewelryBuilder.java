/**
 * Builder class of furniture which constructs the element Jewelry.
 */
public class JewelryBuilder extends Product {
    private final Jewelry jewelry = new Jewelry();

    public JewelryBuilder withId(int id){
        jewelry.setId(id);
        return this;
    }

    public JewelryBuilder withName(String name){
        jewelry.setName(name);
        return this;
    }

    public JewelryBuilder withSalePrice(double salePrice){
        jewelry.setSalePrice(salePrice);
        return this;
    }
    public JewelryBuilder withMinPrice(double minPrice){
        jewelry.setMinPrice(minPrice);
        return this;
    }

    public JewelryBuilder withYear(int year){
        jewelry.setYear(year);
        return this;
    }

    public JewelryBuilder withMaterial(String material){
        jewelry.setMaterial(material);
        return this;
    }

    public JewelryBuilder withWithGemstone(Boolean withGemstone){
        jewelry.setWithGemstone(withGemstone);
        return this;
    }

    public Jewelry build(){
        return jewelry;
    }
}

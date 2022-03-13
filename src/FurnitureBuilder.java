/**
 * Builder class of furniture which constructs the element Furniture.
 */
public class FurnitureBuilder extends Product{
    private final Furniture furniture = new Furniture();

    public FurnitureBuilder withId(int id){
        furniture.setId(id);
        return this;
    }

    public FurnitureBuilder withName(String name){
        furniture.setName(name);
        return this;
    }

    public FurnitureBuilder withSalePrice(double salePrice){
        furniture.setSalePrice(salePrice);
        return this;
    }
    public FurnitureBuilder withMinPrice(double minPrice){
        furniture.setMinPrice(minPrice);
        return this;
    }

    public FurnitureBuilder withYear(int year){
        furniture.setYear(year);
        return this;
    }

    public FurnitureBuilder withType(String type){
        furniture.setType(type);
        return this;
    }

    public FurnitureBuilder withMaterial(String material){
        furniture.setMaterial(material);
        return this;
    }

    public Furniture build(){
        return furniture;
    }
}

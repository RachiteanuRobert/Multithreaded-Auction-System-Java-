/**
 * Builder class of furniture which constructs the element Furniture.
 */
class PaintingBuilder extends Product {
    private final Painting painting = new Painting();

    public PaintingBuilder withId(int id){
        painting.setId(id);
        return this;
    }

    public PaintingBuilder withName(String name){
        painting.setName(name);
        return this;
    }

    public PaintingBuilder withSalePrice(double salePrice){
        painting.setSalePrice(salePrice);
        return this;
    }
    public PaintingBuilder withMinPrice(double minPrice){
        painting.setMinPrice(minPrice);
        return this;
    }

    public PaintingBuilder withYear(int year){
        painting.setYear(year);
        return this;
    }

    public PaintingBuilder withArtist(String artist){
        painting.setArtist(artist);
        return this;
    }

    public PaintingBuilder withType(MaterialType type){
        painting.setType(type);
        return this;
    }

    public Painting build(){
        return painting;
    }
}

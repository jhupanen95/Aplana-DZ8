package utils;

public class Product {
    private String id;
    private long price;
    private long priceWithGuar;

    public Product(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPriceWithGuar() {
        return priceWithGuar;
    }

    public void setPriceWithGuar(long priceWithGuar) {
        this.priceWithGuar = priceWithGuar;
    }
}

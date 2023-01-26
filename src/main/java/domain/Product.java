package domain;

public class Product {
    //() Product data
    private String cod;
    private String name;
    private String category;
    private double price ;


    public Product(String cod, String category, String name, double price) {
        this.cod = cod;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "cod=" + cod +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void applyDiscount(double discount) {
        setPrice(this.price*(1-(discount/100)));
    }



}

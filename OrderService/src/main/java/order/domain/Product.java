package order.domain;

public class Product {
    private String productNumber;

    private String name;
    private Double price;
    private String description;
    private Integer numInStock;

    public Product() {
    }

    public Product(String productNumber, String name, Double price,
                   String description, Integer numInStock) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.description = description;
        this.numInStock = numInStock;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumInStock() {
        return numInStock;
    }

    public void setNumInStock(Integer numInStock) {
        this.numInStock = numInStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNumber='" + productNumber + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", numInStock=" + numInStock +
                '}';
    }
}


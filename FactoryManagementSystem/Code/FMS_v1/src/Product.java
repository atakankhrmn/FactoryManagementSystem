public class Product {
    private String name;
    private double price;
    private Integer stockCount;
    private String id;
    private Double rating;
    private Integer ratingCount;

    public Product(String name, double price, Integer stockCount, String id, Double rating, Integer ratingCount) {
        this.name = name;
        this.price = price;
        this.stockCount = stockCount;
        this.id = id;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    public void showProductInfo() {
        System.out.println("Product: " + name + "    Price: " + price + "    Stock: " + stockCount + "    Rating: " + rating + "   Id: " + id) ;
    }

    public String getName() {
        return name;
    }

    public void displayForOrder(){
        System.out.println("Product Name: " + name + " Price: " + price + " Rating: " + rating + " Id: " + id);
    }

    public double getPrice() {
        return price;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void changeStockCount(Integer count) {
        this.stockCount += count;
    }

    public String getId() {
        return id;
    }

    public Double getRating() {
        return rating;
    }

    public void updateRating(Integer rt) {
        Double sum = rating * ratingCount;
        sum+= rt;
        ratingCount++;
        rating = sum/ratingCount;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

}
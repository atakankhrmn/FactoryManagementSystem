import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class StockService {
    //----------------------------------------------------------------------------------------------------------------
    private static Map<String,Product> stock;

    public static void setStock(Map<String,Product> s) {
        stock = s;
    }

    public static void displayStockInfo(){
        for(Product p : stock.values()){
            p.showProductInfo();
        };
    }

    public static boolean contains(String id){
        return stock.containsKey(id);
    }

    public static void createProduct() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter product name to create new product: ");
        String name = sc.nextLine();

        double price = 0.0;
        while (true) {
            System.out.print("Please enter product price: ");
            String input = sc.nextLine().trim();
            try {
                price = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for the price.");
            }
        }

        Integer stockCount = 0;
        String id = createId();
        Double rating = 0.0;
        Integer ratingCount = 0;

        Product p = new Product(name, price, stockCount, id, rating, ratingCount);
        stock.put(id, p);

        System.out.println("Product successfully created!!!");
    }


    public static String createId(){
        Random rd = new Random();
        Integer id = rd.nextInt(1000,10000);
        while(stock.containsKey(id.toString())){
            id = rd.nextInt(1000,10000);
        }
        return id.toString();
    }

    public static void addProductToStock() {
        System.out.print("Please enter product id: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();

        if (!stock.containsKey(id)) {
            System.out.println("Product not found.");
            return;
        }

        int q = 0;
        while (true) {
            System.out.print("Please enter product quantity: ");
            String input = sc.nextLine().trim();
            try {
                q = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for quantity.");
            }
        }

        Product p = stock.get(id);
        p.changeStockCount(q);
        System.out.println("Stock successfully changed!! New stock count is: " + p.getStockCount());
    }


    public static Double getProductPrice(String productId){
        return stock.get(productId).getPrice();
    }

    public static Product getProduct(String id){
        return stock.get(id);
    }

    public static Map<String, Product> getStock() {
        return stock;
    }
}

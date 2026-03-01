import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Customer extends User {
    private Set<Order> orderList;

    public Customer(String username, String password,PersonalInfo info) {
        super(username, password,"customer", info);
        this.orderList = new HashSet<>();
    }

    @Override
    public void showGeneralInfo() {
        System.out.println("Customer Info: " + getInfo().toString());
    }

    public void placeOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Displaying Products...");
        StockService.displayStockInfo();
        System.out.println();
        boolean flag = true;
        boolean isSuccessful = false;
        Order order = OrderService.createOrder();
        while(flag){
            System.out.println("Please enter product id: ");
            String id = scanner.nextLine();
            if(StockService.contains(id)){
                System.out.println("Enter quantity: ");
                Integer quantity = Integer.parseInt(scanner.nextLine());
                order.addProduct(id,quantity);
                System.out.println(StockService.getProduct(id).getName() + " successfully added.");
                orderList.add(order);
                isSuccessful = true;
            }
            else{
                System.out.println("Invalid product id.");
            }

            while (flag) {
                System.out.print("Add more? Y/N: ");
                String ans = scanner.nextLine().trim();

                if (ans.equalsIgnoreCase("Y")) {
                    break;
                } else if (ans.equalsIgnoreCase("N")) {
                    flag = false;
                    System.out.println("Thank you for your order.");
                    order.setRatedBefore(false);
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            }
        }
        if (!isSuccessful){
            OrderService.deleteOrder(order.getOrderId());
        }
    }

    public void addOrderToList(Order o){
        orderList.add(o);
    }

    public void displayOrders(){
        for(Order order : orderList){
            order.showOrderInfo();
            System.out.println("---------------------------------------");
        }
    }

    public void giveFeedback(Product pr) {
        Scanner scanner = new Scanner(System.in);
        int rating;

        while (true) {
            System.out.print("Please rate " + pr.getName() + " between 1-5: ");

            if (scanner.hasNextInt()) {
                rating = scanner.nextInt();
                scanner.nextLine();

                if (rating >= 1 && rating <= 5) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine();
            }
        }

        pr.updateRating(rating);
    }


    public Set<Order> getOrders() {
        return orderList;
    }

    public void giveFeedbackToOrder(){
        boolean flag = false;
        for(Order order : orderList){
            if(order.getStatus().equals("sent") && !order.isRatedBefore()){
                System.out.println("Rating for OrderId: " + order.getOrderId());
               for (String productId : order.getProductIdList()){
                   Product product = StockService.getProduct(productId);
                   giveFeedback(product);
               }

               order.setRatedBefore(true);
               flag = true;
            }
        }

        if (!flag) {
            System.out.println("There is no product to rate!!!");
        }
    }
}
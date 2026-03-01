import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager extends User {
    private List<Employee> employeeList;

    public Manager(String username, String password, PersonalInfo info) {
        super(username, password, "manager",info);
        employeeList = new ArrayList<>();
    }


    @Override
    public void showGeneralInfo() {
        System.out.println("Manager Info: " + getInfo().toString());
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public void displayEmployees() {
        for (Employee e : employeeList) {
            e.showGeneralInfo();
        }
    }

    public Employee searchEmployee(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter employee username : ");
        String answer = sc.nextLine();
        for (Employee e : employeeList){
            if(e.getUsername().equals(answer)){
                return e;
            }
        }
        System.out.println("Employee could not found!");
        return null;
    }

    public void rateEmployee(Employee e) {
        if (e == null) return;

        Scanner sc = new Scanner(System.in);
        int rating = -1;

        while (true) {
            System.out.print("Rate employee between 1-10: ");
            String answer = sc.nextLine().trim();

            try {
                rating = Integer.parseInt(answer);
                if (rating >= 1 && rating <= 10) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 10.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        e.setRating(rating);
        System.out.println("Rated " + e.getInfo().getName() + " " + e.getInfo().getSurname() + " with: " + rating);
    }


    public void changeShift(Employee e){
        if(e.getShift().equals("day")){
            e.setShift("night");
            System.out.println(e.getInfo().getName() + " " + e.getInfo().getSurname() + "'s shift is changed to night!");
        }
        else{
            e.setShift("day");
            System.out.println(e.getInfo().getName() + " " + e.getInfo().getSurname() + "'s shift is changed to day!");

        }
    }

    public void seeAllLeaveRequests() {
        Scanner sc = new Scanner(System.in);

        for (Employee e : employeeList) {
            if (e.hasLeaveRequest()) {
                while (true) {
                    System.out.print(e.getUsername() + " wants to leave: Yes/No/Hold (Y/N/H): ");
                    String answer = sc.nextLine().trim();

                    if (answer.equalsIgnoreCase("Y")) {
                        e.setLeaveRequested(false);
                        e.setLeaveResult(true);
                        break;
                    } else if (answer.equalsIgnoreCase("N")) {
                        e.setLeaveRequested(false);
                        e.setLeaveResult(false);
                        break;
                    } else if (answer.equalsIgnoreCase("H")) {
                        // Do nothing; leave request stays pending
                        System.out.println("Request held.");
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter Y, N, or H.");
                    }
                }
            }
        }
    }


    public void isLeaveRequested(){
        int leaveRequests = 0;
        for (Employee e : employeeList){
            if(e.hasLeaveRequest()){
                leaveRequests++;
            }
        }
        if(leaveRequests > 0) {
            System.out.println("\n!!!!! You have " + leaveRequests + " leave requests !!!!!");
        }
    }

    public void isOrderGiven(){
        int orderCount = 0;
        for(Order order : OrderService.orders.values()){
            if(order.getStatus().equals("pending")){
                orderCount++;
            }
        }
        if(orderCount > 0){
            System.out.println("\n!!!!! You have " + orderCount + " waiting orders !!!!!\n");
        }
    }

    public void checkPendingOrders() {
        Scanner scanner = new Scanner(System.in);
        boolean isExist = false;

        for (Order order : OrderService.orders.values()) {
            boolean isSent = true;

            if (order.getStatus().equals("pending")) {
                order.showOrderInfo();

                String ans;
                while (true) {
                    System.out.println("1: approve \n2: deny \n3: hold");
                    System.out.print("Decision: ");
                    ans = scanner.nextLine().trim();
                    if (ans.equals("1") || ans.equals("2") || ans.equals("3")) break;
                    System.out.println("Invalid input. Please enter 1, 2, or 3.");
                }

                if (ans.equals("1")) { // approve
                    for (String id : order.getProductIdList()) {
                        int stock = StockService.getProduct(id).getStockCount();
                        int required = order.getProductCount(id);

                        if (stock < required) {
                            System.out.println("There isn't enough product for " + StockService.getProduct(id).getName() + "!!!");
                            int diff = required - stock;
                            System.out.println(diff + " more items need to be produced. Do you want to send produce order? Y/N");

                            String answer;
                            while (true) {
                                System.out.print("Answer: ");
                                answer = scanner.nextLine().trim();
                                if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) break;
                                System.out.println("Invalid input. Please enter Y or N.");
                            }

                            if (answer.equalsIgnoreCase("Y")) {
                                StockService.getProduct(id).changeStockCount(diff);
                                System.out.println("Stock updated. Increased to: " + StockService.getProduct(id).getStockCount());
                                StockService.getProduct(id).changeStockCount(-1 * required);
                            } else {
                                System.out.println("Order status is still pending.");
                                isSent = false;
                            }
                        } else {
                            System.out.println("Order quantity: " + required);
                            StockService.getProduct(id).changeStockCount(-1 * required);
                        }
                    }

                    if (isSent) {
                        order.setStatus("sent");
                    }
                } else if (ans.equals("2")) { // deny
                    order.setStatus("declined");
                }

                isExist = true;
            }
        }

        if (!isExist) {
            System.out.println("There is no order to check.");
        }
    }



}
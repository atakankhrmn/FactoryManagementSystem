import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenuSystem {

    private static Map<String,User> users = new HashMap<>();

    public static void main(String[] args) {

        OrderService orderService = new OrderService();
        DataService.loadAllData("factory_users.csv","employee_data.csv",
                "stock_data.csv","order_contents.csv", "customer_orders.csv", users);
        AuthenticationService authenticationService = new AuthenticationService();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            System.out.println("===== Factory Management System =====");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = authenticationService.authenticate(username, password,users);
            if (user == null) {
                System.out.println("\nLogin failed.\n");
                continue;
            }

            System.out.println("\nWelcome, " + user.getUsername() + "! Role: " + user.getRole());


            boolean running = true;
            while (running) {
                showMenu(user.getRole());


                switch (user.getRole().toLowerCase()) {
                    case "employee":
                        Employee employee = (Employee) user;
                        System.out.print("Enter your choice (or 0 to logout): ");
                        int choice = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        switch (choice) {
                            case 1 -> {
                                System.out.println("\nShowing payroll...\n");
                                employee.showPayroll();
                            }
                            case 2 ->{
                                System.out.println("\nShowing shift info...\n");
                                employee.showShift();
                            }
                            case 3 ->{
                                System.out.println("\nShowing rating...\n");
                                employee.showRating();
                            }
                            case 4 ->{
                                employee.sendLeaveRequest();
                            }
                            case 5 ->{
                                System.out.println("\nShowing leave status...\n");
                                employee.displayLeaveInfo();
                            }
                            case 6 ->{
                                String managerName = employee.getManager().getInfo().getName() + " " + employee.getManager().getInfo().getSurname();
                                System.out.println("\nManager Name: " + managerName +"\n");
                            }
                            case 0 ->{
                                running = false;
                            }
                            default -> System.out.println("Invalid choice.");
                        }
                        break;
                    case "manager":
                        Manager manager = (Manager)user;
                        manager.isLeaveRequested();
                        manager.isOrderGiven();
                        System.out.print("Enter your choice (or 0 to logout): ");
                        choice = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        switch (choice) {
                            case 1 -> manager.displayEmployees();
                            case 2 -> {
                                manager.rateEmployee(manager.searchEmployee());
                            }
                            case 3 -> {
                                manager.seeAllLeaveRequests();
                            }
                            case 4 -> {
                                StockService.addProductToStock();
                            }
                            case 5 -> {
                                manager.changeShift(manager.searchEmployee());
                            }
                            case 6 -> {
                                System.out.println("Showing stock info...");
                                StockService.displayStockInfo();
                            }
                            case 7 -> {
                                StockService.createProduct();
                            }
                            case 8 -> {
                                manager.checkPendingOrders();
                            }
                            case 0 ->{
                                running = false;
                            }
                            default -> System.out.println("Invalid choice.");
                        }
                        break;
                    case "customer":
                        Customer customer = (Customer)user;
                        System.out.print("Enter your choice (or 0 to logout): ");
                        choice = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        switch (choice) {
                            case 1 -> {
                                customer.placeOrder();
                            }
                            case 2 -> {
                                customer.giveFeedbackToOrder();
                            }
                            case 3 -> {
                                System.out.println("Displaying Orders...");
                                customer.displayOrders();
                            }
                            case 0 ->{
                                running = false;
                            }
                            default -> System.out.println("Invalid choice.");
                        }
                        break;
                    default:
                        System.out.println("Unknown role.");
                        running = false;
                }
            }
            System.out.println("Logged out. Goodbye!");

            while (true) {
                System.out.print("Quit Y/N: ");
                String answer = scanner.nextLine().trim();

                if (answer.equalsIgnoreCase("Y")) {
                    System.out.println("Saving database...");
                    DataService.updateDatabase(
                            "factory_users.csv", "employee_data.csv",
                            "stock_data.csv", "order_contents.csv",
                            "customer_orders.csv", users
                    );
                    System.out.println("Goodbye!");
                    exit = true;
                    scanner.close();
                    break;
                } else if (answer.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            }

        }
    }

    private static void showMenu(String role) {
        System.out.println("\n===== " + role.toUpperCase() + " MENU =====");
        switch (role.toLowerCase()) {
            case "employee" -> {
                System.out.println("1. View Payroll");
                System.out.println("2. View Shift Info");
                System.out.println("3. View Rating");
                System.out.println("4. Send Leave Request");
                System.out.println("5. View Leave Status");
                System.out.println("6. View Manager");
            }
            case "manager" -> {
                System.out.println("1. Display Employees");
                System.out.println("2. Rate Employee");
                System.out.println("3. Approve Leave");
                System.out.println("4. Add Stock Count");
                System.out.println("5. Change Shift");
                System.out.println("6. View Stock Info");
                System.out.println("7. Create New Product");
                System.out.println("8. Check Orders");
            }
            case "customer" -> {
                System.out.println("1. Place Order");
                System.out.println("2. Give Feedback");
                System.out.println("3. Show Orders");
            }
        }
        System.out.println("0. Logout");
    }
}
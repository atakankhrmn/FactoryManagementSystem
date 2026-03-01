import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DataService {

    public static void loadAllData(String usersFile, String employeeDataFile , String stockFilePath,
                                   String orderContentsFile, String customerOrderData, Map<String, User> users){
        loadUsers(usersFile,users);
        loadEmployeeData(employeeDataFile, users);
        loadStockData(stockFilePath);
        loadOrderDataToService(orderContentsFile);
        loadOrderDataToCustomer(customerOrderData,users);
    }

    private static void loadEmployeeData(String filename, Map<String, User> users) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                String username = parts[0];

                User user = users.get(username);
                if (user instanceof Employee) {
                    Employee emp = (Employee) user;
                    emp.setEmployeeData(parts[1], parts[2], parts[3], parts[4],parts[5]);
                    User man = users.get(parts[6]);
                    if (man instanceof Manager){
                        ((Manager) man).addEmployee(emp);
                        emp.setManager((Manager) man);
                    }
                } else {
                    System.out.println("User " + username + " is not an employee or not found.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading employee file: " + e.getMessage());
        }
    }

    private static void loadUsers(String filename,Map<String,User> users) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 8) {
                    User user = createUserFromParts(parts);
                    if (user != null) {
                        users.put(user.getUsername(),user);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user file: " + e.getMessage());
        }
    }

    private static User createUserFromParts(String[] parts) {
        String username = parts[0];
        String password = parts[1];
        String role = parts[2];
        String name = parts[3];
        String surname = parts[4];
        String email = parts[5];
        String phone = parts[6];
        String address = parts[7];

        PersonalInfo info = new PersonalInfo(name, surname, email, phone, address);

        switch (role.toLowerCase()) {
            case "employee":
                return new Employee(username, password, info);
            case "manager":
                return new Manager(username, password, info);
            case "customer":
                return new Customer(username, password, info);
            default:
                System.out.println("Unknown role: " + role);
                return null;
        }
    }

    private static void loadStockData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            Map<String, Product> stock = new HashMap<>();
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 6) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    int stockCount = Integer.parseInt(parts[2]);
                    String id = parts[3];
                    double rating = Double.parseDouble(parts[4]);
                    int ratingCount = Integer.parseInt(parts[5]);

                    Product product = new Product(name, price, stockCount, id, rating, ratingCount);
                    stock.put(id, product); // ID'yi key olarak kullanıyoruz
                }
            }
            StockService.setStock(stock);
        } catch (IOException e) {
            System.out.println("Error reading product file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number in product file: " + e.getMessage());
        }

    }

    private static void loadOrderDataToService(String orderFileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(orderFileName))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 3) {
                    String id = parts[0];
                    String productId = parts[1];
                    int productCount = Integer.parseInt(parts[2]);


                    if(OrderService.orders.containsKey(id)){
                        Order order = OrderService.orders.get(id);
                        order.addProduct(productId,productCount);
                    }
                    else{
                        Order order = new Order(id);
                        order.addProduct(productId,productCount);
                        OrderService.orders.put(id,order);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading product file: " + e.getMessage());
        }
    }

    private static void loadOrderDataToCustomer(String customerFileName, Map<String,User> users) {
        try (BufferedReader br = new BufferedReader(new FileReader(customerFileName))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 4) {
                    String username = parts[0];
                    String orderId = parts[1];
                    String status = parts[2];
                    boolean isRatedBefore = Boolean.parseBoolean(parts[3]);
                    if(users.containsKey(username)){
                        Customer customer = (Customer)users.get(username);
                        Order order = OrderService.getOrder(orderId);
                        order.setStatus(status);
                        order.setRatedBefore(isRatedBefore);
                        customer.addOrderToList(order);
                    }
                    else{
                        System.out.println("Customer not found.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading product file: " + e.getMessage());
        }
    }

 //-----------------------------------------UPDATE DATABASE-------------------------------------------------------//
//---------------------------------------------------------------------------------------------------------------//

    public static void updateDatabase(String usersFile, String employeeDataFile , String stockFilePath,
                                      String orderContentsFile, String customerOrderData, Map<String, User> users){
        writeUsers(usersFile, users);
        writeEmployeeData(employeeDataFile, users);
        writeStock(stockFilePath, StockService.getStock());
        writeOrderService(orderContentsFile, OrderService.orders);
        writeOrderCustomer(customerOrderData, users);
    }

    private static void writeUsers(String filename, Map<String, User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("username,password,role,name,surname,email,phone,address\n");
            for (User user : users.values()) {
                PersonalInfo info = user.getInfo();
                writer.write(String.format(Locale.US,"%s,%s,%s,%s,%s,%s,%s,%s\n",
                        user.getUsername(), user.getPassword(), user.getRole(),
                        info.getName(), info.getSurname(), info.getEmail(), info.getPhone(), info.getAddress()));
            }
        } catch (IOException e) {
            System.out.println("Error writing users file: " + e.getMessage());
        }
    }

    private static void writeEmployeeData(String filename, Map<String, User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("employeeUsername,payroll,shift,rating,leaveRequest,leaveResult,manager\n");
            for (User user : users.values()) {
                if (user instanceof Employee emp) {
                    String managerUsername = emp.getManager() != null ? emp.getManager().getUsername() : "";
                    writer.write(String.format(Locale.US,"%s,%.2f,%s,%.1f,%b,%b,%s\n",
                            emp.getUsername(), emp.getPayroll(), emp.getShift(), emp.getRating(),
                            emp.hasLeaveRequest(), emp.getLeaveResult(), managerUsername));
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing employee data: " + e.getMessage());
        }
    }

    private static void writeStock(String filename, Map<String, Product> stock) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("name,price,stockCount,id,rating,ratingCount\n");
            for (Product product : stock.values()) {
                writer.write(String.format(Locale.US,"%s,%.1f,%d,%s,%.1f,%d\n",
                        product.getName(), product.getPrice(), product.getStockCount(),
                        product.getId(), product.getRating(), product.getRatingCount()));
            }
        } catch (IOException e) {
            System.out.println("Error writing stock data: " + e.getMessage());
        }
    }

    private static void writeOrderService(String filename, Map<String, Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("orderId,productId,productCount\n");
            for (Order order : orders.values()) {
                for (Map.Entry<String, Integer> entry : order.getProductCountList().entrySet()) {
                    writer.write(String.format(Locale.US,"%s,%s,%d\n", order.getOrderId(), entry.getKey(), entry.getValue()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing order service file: " + e.getMessage());
        }
    }

    private static void writeOrderCustomer(String filename, Map<String, User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("username,orderId,status,isRatedBefore\n");
            for (User user : users.values()) {
                if (user instanceof Customer cust) {
                    for (Order order : cust.getOrders()) {
                        writer.write(String.format(Locale.US,"%s,%s,%s,%s\n", cust.getUsername(), order.getOrderId(), order.getStatus(), order.isRatedBefore()));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing customer orders: " + e.getMessage());
        }
    }
}

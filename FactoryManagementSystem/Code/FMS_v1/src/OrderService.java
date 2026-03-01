import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OrderService {
    public static Map<String,Order> orders;

    public OrderService(){
        orders = new HashMap<>();
    }
    public static Order getOrder(String orderId){
        return orders.get(orderId);
    }

    public static String createId(){
        Random rd = new Random();
        Integer id = rd.nextInt(10000,100000);
        while(orders.containsKey(id.toString())){
            id = rd.nextInt(10000,100000);
        }
        return id.toString();
    }

    public static Order createOrder(){
        String id = createId();
        Order order = new Order(id);
        orders.put(id,order);
        order.setStatus("pending");
        return order;
    }

    public static void deleteOrder(String orderId){
        orders.remove(orderId);
    }


}

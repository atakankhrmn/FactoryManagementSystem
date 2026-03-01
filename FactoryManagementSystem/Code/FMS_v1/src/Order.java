import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Order {
    private String orderId;
    private Set<String> productIdList;
    private String status;
    private Map<String,Integer> productCountList;
    private double totalPrice;
    private boolean isRatedBefore;

    public Order(String orderId){

        totalPrice = 0;
        productIdList = new HashSet<>();
        productCountList = new HashMap<>();
        this.orderId = orderId;
    }

    public void showOrderInfo(){

        System.out.println("Order Id: " + orderId + " Status: " + status + " Total Price: " + getTotalPrice());
        for (String id : productIdList){
            System.out.print("Quantity: " + getProductCount(id) + " ");
            StockService.getProduct(id).displayForOrder();
            System.out.println("Stock Quantity: " + StockService.getProduct(id).getStockCount());
        }

    }

    public Integer getProductCount(String productId){
        return productCountList.get(productId);
    }

    public String getStatus() {
        return status;
    }

    public void addProduct(String productId, Integer count){
        if(productIdList.contains(productId)){
            productIdList.add(productId);
            Integer totalCount = productCountList.get(productId) + count;
            productCountList.put(productId,totalCount);
        }
        else{
            productIdList.add(productId);
            productCountList.put(productId,count);
        }
    }

    public void setStatus(String status){
        this.status = status;
    }

    public boolean isRatedBefore() {
        return isRatedBefore;
    }

    public void setRatedBefore(boolean ratedBefore) {
        isRatedBefore = ratedBefore;
    }

    public void calculateTotalPrice(){
        Double totalPrice = 0.0;
        for(String id : productIdList){
            totalPrice += StockService.getProductPrice(id) * productCountList.get(id);
        }
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice(){
        calculateTotalPrice();
        return this.totalPrice;
    }

    public String getOrderId(){
        return this.orderId;
    }

    public Set<String> getProductIdList() {
        return productIdList;
    }

    public Map<String, Integer> getProductCountList() {
        return productCountList;
    }
}
package Entity;

import Constant.OrderSide;


public class Order {
    private String orderId;
    private Integer quantity;
    private Integer price;
    private OrderSide side;

    public Order() {
        super();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (isValidVolume(quantity)) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Volume is too large. Maximum allowed value is 999,999,999");
        }
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        if (isValidPrice(price)) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price is too large. Maximum allowed value is 999,999");
        }
    }


    public OrderSide getSide() {
        return side;
    }

    public void setSide(OrderSide side) {
        if (isValidOrderSide(side)) {
            this.side = side;
        } else {
            throw new IllegalArgumentException("Invalid Order Side");
        }
    }


    public String getOrderId() {
        return orderId;
    }


    public void setOrderId(String orderId) {
        if (isValidOrderId(orderId)) {
            this.orderId = orderId;
        } else {
            throw new IllegalArgumentException("Invalid Order Id:" + orderId);
        }
    }

    public Order(String orderId, OrderSide side, Integer price, Integer quantity) {
        setOrderId(orderId);
        setPrice(price);
        setQuantity(quantity);
        setSide(side);
    }

    public String toString() {
        return String.valueOf(this.quantity);
    }

    private boolean isValidPrice(Integer price) {
        return price != null && price <= 999999;
    }

    private boolean isValidVolume(Integer quantity) {
        return quantity != null && quantity <= 999999999;
    }

    private boolean isValidOrderId(String orderId) {
        return orderId != null && !orderId.trim().equals("");
    }

    private boolean isValidOrderSide(OrderSide side) {
        return side == OrderSide.SELL || side == OrderSide.BUY;
    }


}

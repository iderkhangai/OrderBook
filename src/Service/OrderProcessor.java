package Service;

import java.util.Arrays;

public class OrderProcessor {
    public void validateOrder(String... orderRecord) {
        //validate order records
        if (!isValidOrderRecord(orderRecord)) {
            throw new IllegalArgumentException("Invalid order record:" + Arrays.toString(orderRecord));
        }
//        String orderId = orderRecord[0];
//        String side = orderRecord[1];
//        String price = orderRecord[2];
//        String quantity = orderRecord[3];
//
//        OrderSide orderSide = side.equals("B") ? OrderSide.BUY : OrderSide.SELL;
//        //create an Order object
//        Order order = new Order(orderId, orderSide, new Integer(price), new Integer(quantity));
//
//        orderBook.addOrder(order);
//        Order newOrder = new ();
//        orderBook.trade("10006","10001",100,500);

    }


    //    Validates an order record
    public boolean isValidOrderRecord(String[] orderRecord) {
        if (orderRecord.length == 4) {
//            String orderId = orderRecord[0];
            String side = orderRecord[1];
            String price = orderRecord[2];
            String quantity = orderRecord[3];
            return
                    isValidOrderSide(side) &&
                            isValidQuantity(quantity) &&
                            isValidPrice(price);
        } else {
            return false;
        }
    }

    public boolean isValidOrderSide(String orderSideText) {
        return (orderSideText.equals("B") || orderSideText.equals("S"));
    }

    public boolean isValidQuantity(String quantityText) {

        return quantityText.matches("[0-9]+\\.?[0-9]*");
    }

    public boolean isValidPrice(String priceText) {
        return priceText.matches("[0-9]+\\.?[0-9]*");
    }

}

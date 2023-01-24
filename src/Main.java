import Constant.OrderSide;
import Entity.Order;
import Service.OrderBookService;
import Service.OrderProcessor;
import Utils.FileReader;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

//    public static void main(String[] args) {
//        OrderBook orderBook = new OrderBook();
//        orderBook.addOrder(new Order("10000", OrderSide.BUY, 98, 25500));
//        orderBook.addOrder(new Order("10005", OrderSide.SELL, 105, 20000));
//        orderBook.addOrder(new Order("10001", OrderSide.SELL, 100, 500));
//        orderBook.addOrder(new Order("10002", OrderSide.SELL, 100, 10000));
//        orderBook.addOrder(new Order("10003", OrderSide.BUY, 99, 50000));
//        orderBook.addOrder(new Order("10004", OrderSide.SELL, 103, 100));
//        orderBook.trade(new Order("10006", OrderSide.BUY, 105, 16000));
//        System.out.println("***After trading***");
//        orderBook.printOrderBook();
//    }

    public static void main(String[] args) {
        OrderBookService orderBook = new OrderBookService();
        File file = new File("src/resources/test.txt");
//        System.out.println("file path - " + file.getAbsolutePath());
        if (!file.getAbsolutePath().matches(".*-.*\\.txt")) {
            System.out.println("File Name Error!");
            System.exit(-1);
        }

        OrderProcessor orderProcessor = new OrderProcessor();
        FileReader reader = null;
        try {
            String[] recordDetail;
            reader = new FileReader(file.getAbsolutePath());
            while (reader.hasNext()) {
                recordDetail = reader.nextLine().split(",");
                try {
                    orderProcessor.validateOrder(recordDetail);
                    String orderId = recordDetail[0];
                    String side = recordDetail[1];
                    String price = recordDetail[2];
                    String quantity = recordDetail[3];
                    OrderSide orderSide = side.equals("B") ? OrderSide.BUY : OrderSide.SELL;
                    //create an Order object
                    Order order = new Order(orderId, orderSide, new Integer(price), new Integer(quantity));
                    orderBook.addOrder(order);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // trading order
            orderBook.trade(new Order("10006", OrderSide.BUY, 105, 16000));
            // printing the Last state of order book
            orderBook.printOrderBook();
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Failed reading file:" + e.getMessage());
        } finally {
            if (reader != null)
                reader.close();
        }

    }
}

package Service;

import Constant.OrderSide;
import Entity.Order;

import java.util.*;

public class OrderBookService {
    private final TreeMap<Integer, PriorityQueue<Order>> buyOrders;
    private final TreeMap<Integer, PriorityQueue<Order>> sellOrders;

    public OrderBookService() {
        buyOrders = new TreeMap<>();
        sellOrders = new TreeMap<>((a, b) -> b - a);
    }

    public void addOrder(Order order) {
        if (order.getSide() == OrderSide.SELL) {
            addToOrderBook(buyOrders, order);
        } else {
            addToOrderBook(sellOrders, order);
        }
    }

    private void addToOrderBook(TreeMap<Integer, PriorityQueue<Order>> orders, Order order) {
        if (!orders.containsKey(order.getPrice())) {
            orders.put(order.getPrice(), new PriorityQueue<>(Comparator.comparing(Order::getOrderId)));
        }
        orders.get(order.getPrice()).add(order);
    }

    public void trade(Order newOrder) {
        TreeMap<Integer, PriorityQueue<Order>> oppositeOrders = (newOrder.getSide() == OrderSide.SELL) ? sellOrders : buyOrders;
        while (newOrder.getQuantity() > 0) {
            if (oppositeOrders.isEmpty() || oppositeOrders.firstKey() > newOrder.getPrice()) {
                addOrder(newOrder);
                return;
            }
            int price = oppositeOrders.firstKey();
            PriorityQueue<Order> priceLevel = oppositeOrders.get(price);
            Order restingOrder = priceLevel.poll();
            assert restingOrder != null;
            if (restingOrder.getQuantity() <= newOrder.getQuantity()) {
                newOrder.setQuantity(newOrder.getQuantity() - restingOrder.getQuantity());
                System.out.println("trade " + newOrder.getOrderId() + "," + restingOrder.getOrderId() + "," + price + "," + restingOrder.getQuantity());
                if (priceLevel.isEmpty()) {
                    oppositeOrders.remove(price);
                }
            } else {
                restingOrder.setQuantity(restingOrder.getQuantity() - newOrder.getQuantity());
                System.out.println("trade " + newOrder.getOrderId() + "," + restingOrder.getOrderId() + "," + price + "," + newOrder.getQuantity());
                priceLevel.add(restingOrder);
                newOrder.setQuantity(0);
            }
        }
    }


    // It uses a boolean variable to track whether or not the buy order has been printed
    // and only print the " | " separator before the first buy order.
    public void printOrderBook() {
        System.out.println("\n\t   Buyers\t\tSellers");
        for (Map.Entry<Integer, PriorityQueue<Order>> sellOrder : sellOrders.entrySet()) {
            PriorityQueue<Order> sellQueue = sellOrder.getValue();
            while (!sellQueue.isEmpty()) {
                Order sell = sellQueue.poll();
                System.out.printf("\t%,9d %4s", sell.getQuantity(), sellOrder.getKey());
                boolean isBuyPrinted = false;
                for (Map.Entry<Integer, PriorityQueue<Order>> buyOrder : buyOrders.entrySet()) {
                    PriorityQueue<Order> buyQueue = buyOrder.getValue();
                    while (!buyQueue.isEmpty()) {
                        Order buy = buyQueue.poll();
                        if (!isBuyPrinted) {
                            System.out.print(" |");
                            isBuyPrinted = true;
                        }
                        System.out.printf("%4s  %,6d", buyOrder.getKey(), buy.getQuantity());
                        break;
                    }
                }
                System.out.println();
                break;
            }
        }


    }
}


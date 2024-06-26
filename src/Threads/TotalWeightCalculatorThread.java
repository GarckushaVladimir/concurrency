package Threads;

import Cart.StoreCart;
import Items.Item;

public class TotalWeightCalculatorThread extends Thread {
    private final StoreCart cart;
    private double totalWeight;


    public TotalWeightCalculatorThread(StoreCart cart) {
        this.cart = cart;
    }

    @Override
    public void run() {
        synchronized (cart) {
            while (cart.iterator().hasNext()) {
                for (Item item : cart) {
                    totalWeight += item.getWeight();
                }
                break;
            }
        }

    }

    public double getTotalWeight() {
        return totalWeight;
    }

}

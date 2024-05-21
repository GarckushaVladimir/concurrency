package Threads;

import Cart.StoreCart;
import Items.Item;

public class AddItemThread extends Thread {
    private final StoreCart cart;
    private final Item item;

    public AddItemThread(StoreCart cart, Item item) {
        this.cart = cart;
        this.item = item;
    }

    @Override
    public void run() {
        cart.addItem(item);
    }
}

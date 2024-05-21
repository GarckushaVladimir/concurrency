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
        synchronized (cart) {
            cart.addItem(item);
            cart.notifyAll(); // Уведомляем все потоки, что элемент добавлен
        }
    }
}

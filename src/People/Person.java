package People;

import Cart.StoreCart;

/*
  Реализация Покупателя
*/

public class Person implements Buyer {
    private final StoreCart cart;

    public Person(StoreCart cart) {
        this.cart = cart;
    }

    @Override
    public StoreCart getCart() {
        return cart;
    }
}

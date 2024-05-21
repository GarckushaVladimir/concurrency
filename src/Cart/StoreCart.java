package Cart;

/*
  Паттерн Компоновщик, Итератор
  Реализует интерфейс Cart.Iterable, позволяя перебирать список покупок
*/

import Items.Item; // подключили интерфейс товар

import java.util.ArrayList;
import java.util.Iterator;

public class StoreCart implements Iterable<Item>{
    // Список товаров в чеке
    private final ArrayList<Item> items;

    public StoreCart() {
        this.items = new ArrayList<>();
    }

    public synchronized void removeItem(Item item) {
        items.remove(item);
    }

    public synchronized void addItem(Item item) {
        items.add(item);
        notifyAll(); // Уведомляем все потоки, что элемент добавлен
    }

    @Override
    public synchronized Iterator<Item> iterator(){
        return new StoreCartIterator(items);
    }

    public synchronized double getTotalWeight() {
        while (items.isEmpty()) {
            try {
                wait(); // Ожидаем, пока корзина не станет непустой
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return 0;
            }
        }

        double totalWeight = 0;
        for (Item item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }
}

package Cart;

/*
  Паттерн Компоновщик, Итератор
  Реализует интерфейс Cart.Iterable, позволяя перебирать список покупок
*/

import Items.Item; // подключили интерфейс товар
import Threads.TotalWeightCalculatorThread;

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
    }

    @Override
    public Iterator<Item> iterator(){
        return new StoreCartIterator(items);
    }

    public double getTotalWeight() {
        TotalWeightCalculatorThread weightThread = new TotalWeightCalculatorThread(this);
        weightThread.start();
        try {
            weightThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return weightThread.getTotalWeight();
    }
}

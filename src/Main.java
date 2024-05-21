/*
  Используются паттерны: Фабрика, Декоратор, Компоновщик, Итератор, Стратегия, Посетитель, Синглтон
*/

import Calculator.*;
import Items.Item; // подключили интерфейс товар
import Items.ItemFactory; // подключили интерфейс фабрика
import Items.SailDecorator; // подключили класс скидка
import Items.SimpleItemFactory; // подключили класс фабрики
import People.Person; // подключили класс человек
import Cart.StoreCart; // подключили класс чек
import Threads.AddItemThread;
import Threads.CostCalculatorThread;

public class Main {
    public static void main(String[] args) {
        // Создается объект Фабрики (использование getInstance() паттерна Синглтон)
        ItemFactory simpleItemFactory = SimpleItemFactory.getInstance();

        // Создание товаров с помощью Фабрики
        Item item1 = simpleItemFactory.createItem(10, 5);
        Item item2 = simpleItemFactory.createItem(20, 2);

        // Применяется скидка на второй товар (паттерн Декоратор)
        Item sail1 = new SailDecorator(item2, 0.5);

        // Создаем корзину покупок
        StoreCart cart = new StoreCart();

        // Добавляем элементы многопоточно
        AddItemThread addItemThread1 = new AddItemThread(cart, item1);
        AddItemThread addItemThread2 = new AddItemThread(cart, sail1);

        addItemThread1.start();
        addItemThread2.start();

        try {
            addItemThread1.join();
            addItemThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Создаем покупателя
        Person person = new Person(cart);

        // Создаем стратегии расчета стоимости
        CostCalculateStrategy simpleCostCalculateStrategy = new SimpleCostCalculateStrategy();
        CostCalculateStrategy improvedCostCalculateStrategy = new ImprovedCostCalculateStrategy();

        // Создаем калькуляторы с разными стратегиями
        Calculator simpleCalculator = new Calculator(simpleCostCalculateStrategy);
        Calculator improvedCalculator = new Calculator(improvedCostCalculateStrategy);

        CostCalculatorThread simpleCostThread = new CostCalculatorThread(simpleCalculator, person);
        CostCalculatorThread improvedCostThread = new CostCalculatorThread(improvedCalculator, person);

        simpleCostThread.start();
        improvedCostThread.start();

        try {
            simpleCostThread.join();
            improvedCostThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Подсчет стоимости с помощью разных калькуляторов
        System.out.println("Total cost with SimpleCalculator: " + simpleCostThread.getResult());
        System.out.println("Total cost with ImprovedCalculator: " + improvedCostThread.getResult());

        System.out.println("Total weight: " + cart.getTotalWeight());
    }
}
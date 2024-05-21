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

        // Добавляем элементы
        cart.addItem(item1);
        cart.addItem(sail1);

        // Создаем покупателя
        Person person = new Person(cart);
        // Создаем стратегии расчета стоимости
        CostCalculateStrategy simpleCostCalculateStrategy = new SimpleCostCalculateStrategy();
        CostCalculateStrategy improvedCostCalculateStrategy = new ImprovedCostCalculateStrategy();

        // Создаем калькуляторы с разными стратегиями
        Calculator simpleCalculator = new Calculator(simpleCostCalculateStrategy);
        Calculator improvedCalculator = new Calculator(improvedCostCalculateStrategy);

        // Подсчет стоимости с помощью разных калькуляторов
        System.out.println("Total cost with SimpleCalculator: " + simpleCalculator.calculateCost(person));
        System.out.println("Total cost with ImprovedCalculator: " + improvedCalculator.calculateCost(person));

        System.out.println("Total weight: " + cart.getTotalWeight());
    }
}
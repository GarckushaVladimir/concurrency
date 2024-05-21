package Items;

/*
  Паттерн Фабрика
  Определяет метод создания товара createItem()
*/

public interface ItemFactory {
    Item createItem(double price, double weight);
}

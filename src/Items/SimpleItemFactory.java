package Items;

/*
  Паттерн Фабрика, Синглтон
  Реализует интерфейс Items.ItemFactory
*/

public class SimpleItemFactory implements ItemFactory{
    // Единственный экземпляр Фабрики (паттерн Синглтон)
    private static SimpleItemFactory instance;

    // Приватный конструктор
    private SimpleItemFactory() {}

    public static SimpleItemFactory getInstance() {
        if (instance == null) {
            instance = new SimpleItemFactory();
        }
        return instance;
    }

    @Override
    public Item createItem(double price, double weight) {
        return new SimpleItem(price, weight);
    }
}

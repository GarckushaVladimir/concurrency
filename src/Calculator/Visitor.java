package Calculator;

import Cart.StoreCart;  // подключили класс чек

/*
  Паттерн Посетитель
*/

public interface Visitor {
    void visit(StoreCart cart);
}

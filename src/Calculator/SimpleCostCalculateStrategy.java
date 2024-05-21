package Calculator;


import Cart.StoreCart;
import People.Buyer;

public class SimpleCostCalculateStrategy implements CostCalculateStrategy {
    @Override
    public synchronized double calculateCost(Buyer buyer) {
        StoreCart cart = buyer.getCart();

        CostCalculatorVisitor visitor = new CostCalculatorVisitor();
        visitor.visit(cart);

        return visitor.getTotalCost();
    }
}

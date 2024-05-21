package Calculator;


import Cart.StoreCart;
import People.Buyer;

public class ImprovedCostCalculateStrategy implements CostCalculateStrategy {
    @Override
    public synchronized double calculateCost(Buyer buyer) {
        StoreCart cart = buyer.getCart();

        CostCalculatorVisitor visitor = new CostCalculatorVisitor();
        visitor.visit(cart);

        return visitor.getTotalCost() * 1.13;
    }
}

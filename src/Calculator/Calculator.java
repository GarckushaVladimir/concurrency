package Calculator;

import People.Buyer;

public class Calculator {
    private CostCalculateStrategy costCalculateStrategy;

    public Calculator(CostCalculateStrategy costCalculateStrategy) {
        this.costCalculateStrategy = costCalculateStrategy;
    }

    public synchronized double calculateCost(Buyer buyer) {
        return costCalculateStrategy.calculateCost(buyer);
    }
}

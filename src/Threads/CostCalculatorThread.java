package Threads;

import Calculator.Calculator;
import People.Buyer;

public class CostCalculatorThread extends Thread {
    private final Calculator calculator;
    private final Buyer buyer;
    private double result;

    public CostCalculatorThread(Calculator calculator, Buyer buyer) {
        this.calculator = calculator;
        this.buyer = buyer;
    }

    @Override
    public void run() {
        result = calculator.calculateCost(buyer);
    }

    public double getResult() {
        return result;
    }
}

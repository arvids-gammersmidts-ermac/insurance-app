package DomainModel.Policy.Calculator.Strategy;

import DomainModel.Money;
import DomainModel.Policy.Policy;

public class PolicyCalculationContext {
    private PolicyCalculationStrategy strategy;

    public PolicyCalculationContext(PolicyCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public Money executeStrategy(Policy policy) {
        return strategy.calculate(policy);
    }
}

package DomainModel.Policy.Calculator;

import DomainModel.Money;
import DomainModel.Policy.Calculator.Strategy.PolicyCalculationContext;
import DomainModel.Policy.Calculator.Strategy.PolicyCalculationStrategy;
import DomainModel.Policy.Policy;

public class PremiumCalculator {
    private PolicyCalculationContext context;

    public PremiumCalculator(PolicyCalculationStrategy strategy) {
        this.context = new PolicyCalculationContext(strategy);
    }

    public Money calculate(Policy policy) {
        return context.executeStrategy(policy);
    }
}

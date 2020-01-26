package DomainModel.Policy.Calculator.Strategy;

import DomainModel.Money;
import DomainModel.Policy.Policy;

public interface PolicyCalculationStrategy {
    Money calculate(Policy policy);
}

package Policy.Calculator;

import DomainModel.Money;
import DomainModel.Policy.Calculator.PremiumCalculator;
import DomainModel.Policy.Calculator.Strategy.DefaultPremiumCalculatorStrategy;
import DomainModel.Policy.Policy;
import DomainModel.Policy.PolicyBuilder;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PremiumCalculatorTest {
    private final PolicyBuilder policyBuilder = new PolicyBuilder();

    @Test
    public void test() {
        PremiumCalculator premiumCalculator = new PremiumCalculator(new DefaultPremiumCalculatorStrategy());
        Policy policy = this.policyBuilder.Build();

        assertEquals(new Money(new BigDecimal(62.11)).getAmount(), premiumCalculator.calculate(policy).getAmount());
    }
}

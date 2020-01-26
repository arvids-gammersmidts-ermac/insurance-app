import DomainModel.Money;
import DomainModel.Policy.Calculator.PremiumCalculator;
import DomainModel.Policy.Calculator.Strategy.DefaultPremiumCalculatorStrategy;
import DomainModel.Policy.Policy;
import DomainModel.Policy.PolicyObject;
import DomainModel.Policy.PolicySubObject;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        PremiumCalculator calculator = new PremiumCalculator(new DefaultPremiumCalculatorStrategy());
        Money finalCost = calculator.calculate(getExamplePolicy());
        System.out.println(finalCost.getAmount());
        System.out.println(finalCost.getCurrency());
    }

    private static Policy getExamplePolicy() {
        PolicyObject[] policyObjects = new PolicyObject[]{
                new PolicyObject("Flat", new PolicySubObject[]{
                        new PolicySubObject("TV sub object",  new Money(new BigDecimal(109.00)), new String[]{PolicySubObject.RISK_TYPE_WATER}),
                        new PolicySubObject("Painting sub object", new Money(new BigDecimal(10.01)), new String[]{PolicySubObject.RISK_TYPE_FIRE, PolicySubObject.RISK_TYPE_WATER}),
                }),
                new PolicyObject("House", new PolicySubObject[]{
                        new PolicySubObject("First sub object", new Money(new BigDecimal(1.00)), new String[]{PolicySubObject.RISK_TYPE_FIRE, PolicySubObject.RISK_TYPE_WATER}),
                        new PolicySubObject("Second sub object", new Money(new BigDecimal(20.10)), new String[]{PolicySubObject.RISK_TYPE_FIRE}),
                        new PolicySubObject("Third sub object", new Money(new BigDecimal(55.05)), new String[]{PolicySubObject.RISK_TYPE_WATER}),
                })
        };
        return new Policy("LV19-07-100000-1", "APPROVED", policyObjects);
    }
}

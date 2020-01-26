import DomainModel.Money;
import DomainModel.Policy.Calculator.PremiumCalculator;
import DomainModel.Policy.Calculator.Strategy.DefaultPremiumCalculatorStrategy;
import DomainModel.Policy.Policy;
import DomainModel.Policy.PolicyNumber;
import DomainModel.Policy.PolicyObject;
import DomainModel.Policy.PolicySubObject;
import DomainModel.Policy.Risks.FireRisk;
import DomainModel.Policy.Risks.Risk;
import DomainModel.Policy.Risks.WaterRisk;

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
                        new PolicySubObject("TV sub object", new Money(new BigDecimal(109.00)), new Risk[]{new WaterRisk()}),
                        new PolicySubObject("Painting sub object", new Money(new BigDecimal(10.01)), new Risk[]{new FireRisk(), new WaterRisk()}),
                }),
                new PolicyObject("House", new PolicySubObject[]{
                        new PolicySubObject("First sub object", new Money(new BigDecimal(1.00)), new Risk[]{new FireRisk(), new WaterRisk()}),
                        new PolicySubObject("Second sub object", new Money(new BigDecimal(20.10)), new Risk[]{new FireRisk()}),
                        new PolicySubObject("Third sub object", new Money(new BigDecimal(55.05)), new Risk[]{new WaterRisk()}),
                })
        };
        return new Policy(new PolicyNumber("LV19-07-100000-1"), policyObjects);
    }
}

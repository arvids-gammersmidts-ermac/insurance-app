package DomainModel.Policy.Calculator.Strategy;

import DomainModel.Money;
import DomainModel.Policy.Policy;
import DomainModel.Policy.PolicySubObject;
import DomainModel.Policy.Risks.Risk;

import java.math.BigDecimal;

public class DefaultPremiumCalculatorStrategy implements PolicyCalculationStrategy {
    @Override
    public Money calculate(Policy policy) {
        Money totalPremium = new Money(new BigDecimal(0.00));
        for (Risk risk : policy.getDistinctPolicyRisks()) {
            totalPremium = totalPremium.plus(risk.calculatePremium(calculateRiskTotalSum(policy, risk)));
        }

        return totalPremium;
    }

    private static Money calculateRiskTotalSum(Policy policy, Risk risk) {
        Money typePremium = new Money(new BigDecimal(0.00));
        for (PolicySubObject policySubObject : policy.getSubObjects()) {
            for (Risk type : policySubObject.getRisks()) {
                if (type.getClass().equals(risk.getClass())) {
                    typePremium = typePremium.plus(policySubObject.getSum());
                    break;
                }
            }
        }

        return typePremium;
    }
}

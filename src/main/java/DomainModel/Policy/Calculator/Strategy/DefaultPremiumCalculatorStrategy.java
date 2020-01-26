package DomainModel.Policy.Calculator.Strategy;

import DomainModel.Money;
import DomainModel.Policy.Policy;
import DomainModel.Policy.PolicyObject;
import DomainModel.Policy.PolicySubObject;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class DefaultPremiumCalculatorStrategy implements PolicyCalculationStrategy {
    private final static double COEFFICIENT_FIRE_DEFAULT = 0.013;
    private final static double COEFFICIENT_FIRE_OVER_LIMIT = 0.023;
    private final static double COEFFICIENT_WATER_DEFAULT = 0.1;
    private final static double COEFFICIENT_WATER_OVER_LIMIT = 0.05;
    private final static Money LIMIT_FIRE = new Money(new BigDecimal(100.00));
    private final static Money LIMIT_WATER = new Money(new BigDecimal(10.00));

    @Override
    public Money calculate(Policy policy) {
        Money totalPremium = new Money(new BigDecimal(0.00));
        Money firePremium = calculateFirePremium(calculateTypeTotal(policy, "FIRE"));
        Money waterPremium = calculateWaterPremium(calculateTypeTotal(policy, "WATER"));

        totalPremium = totalPremium.plus(firePremium);
        totalPremium = totalPremium.plus(waterPremium);

        return totalPremium;
    }

    private static Money calculateTypeTotal(Policy policy, String premiumType){
        Money typePremium = new Money(new BigDecimal(0.00));
        for (PolicyObject policyObject : policy.getPolicyObjects()) {
            for (PolicySubObject policySubObject : policyObject.getSubObjects()) {
                List<String> typeList = Arrays.asList(policySubObject.getRiskTypes());
                if(typeList.contains(premiumType)){
                    typePremium = typePremium.plus(policySubObject.getSum());
                }
            }
        }

        return typePremium;
    }

    private Money calculateWaterPremium(Money total){
        if(total.gteq(LIMIT_WATER)){
            return total.times(COEFFICIENT_WATER_OVER_LIMIT);
        }

        return total.times(COEFFICIENT_WATER_DEFAULT);
    }

    private Money calculateFirePremium(Money total){
        if(total.gt(LIMIT_FIRE)){
            return total.times(COEFFICIENT_FIRE_OVER_LIMIT);
        }

        return total.times(COEFFICIENT_FIRE_DEFAULT);
    }
}

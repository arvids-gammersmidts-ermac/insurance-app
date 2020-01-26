package DomainModel.Policy.Risks;

import DomainModel.Money;

import java.math.BigDecimal;

public class FireRisk extends Risk {
    private final static Money LIMIT = new Money(new BigDecimal(100.00));
    private final static double COEFFICIENT_DEFAULT = 0.013;
    private final static double COEFFICIENT_OVER_LIMIT = 0.023;

    @Override
    public Money calculatePremium(Money total) {
        if (total.gt(LIMIT)) {
            return total.times(COEFFICIENT_OVER_LIMIT);
        }

        return total.times(COEFFICIENT_DEFAULT);
    }
}

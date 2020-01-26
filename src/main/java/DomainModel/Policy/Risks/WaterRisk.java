package DomainModel.Policy.Risks;

import DomainModel.Money;

import java.math.BigDecimal;

public class WaterRisk extends Risk {
    private final static Money LIMIT = new Money(new BigDecimal(10.00));
    private final static double COEFFICIENT_DEFAULT = 0.1;
    private final static double COEFFICIENT_OVER_LIMIT = 0.05;

    @Override
    public Money calculatePremium(Money total) {
        if (total.gteq(LIMIT)) {
            return total.times(COEFFICIENT_OVER_LIMIT);
        }

        return total.times(COEFFICIENT_DEFAULT);
    }
}

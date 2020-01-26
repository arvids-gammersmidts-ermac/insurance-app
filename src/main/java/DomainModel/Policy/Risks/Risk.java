package DomainModel.Policy.Risks;

import DomainModel.Money;

public abstract class Risk {
    public abstract Money calculatePremium(Money total);
}

package DomainModel.Policy;

import DomainModel.Money;
import DomainModel.Policy.Risks.Risk;

public class PolicySubObject {
    private String name;
    private Money sum;
    private Risk[] risks;

    public PolicySubObject(String name, Money sum, Risk[] risks) {
        this.name = name;
        this.sum = sum;
        this.risks = risks;
    }

    public String getName() {
        return name;
    }

    public Money getSum() {
        return sum;
    }

    public Risk[] getRisks() {
        return risks;
    }
}

package DomainModel.Policy;

import DomainModel.Money;

import java.math.BigDecimal;

public class PolicySubObjectBuilder {
    private String name;
    private Money sum;
    private String[] riskTypes;

    public PolicySubObjectBuilder() {
        this.name = "PolicySubObject";
        this.sum = new Money(new BigDecimal(10.00));
        this.riskTypes = new String[]{PolicySubObject.RISK_TYPE_FIRE, PolicySubObject.RISK_TYPE_WATER};
    }

    public PolicySubObject Build() {
        return new PolicySubObject(this.name, this.sum, this.riskTypes);
    }

    public PolicySubObject BuildTV() {
        this.name = "TV";
        this.sum = new Money(new BigDecimal(110.00));
        this.riskTypes = new String[]{PolicySubObject.RISK_TYPE_FIRE, PolicySubObject.RISK_TYPE_WATER};
        return new PolicySubObject(this.name, this.sum, this.riskTypes);
    }

    public PolicySubObject BuildPainting() {
        this.name = "Painting";
        this.sum = new Money(new BigDecimal(1001.11));
        this.riskTypes = new String[]{PolicySubObject.RISK_TYPE_FIRE};
        return new PolicySubObject(this.name, this.sum, this.riskTypes);
    }

    public PolicySubObjectBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PolicySubObjectBuilder withSum(Money sum) {
        this.sum = sum;
        return this;
    }

    public PolicySubObjectBuilder withBothRisks() {
        this.riskTypes = new String[]{PolicySubObject.RISK_TYPE_FIRE, PolicySubObject.RISK_TYPE_WATER};
        return this;
    }

    public PolicySubObjectBuilder withFireRisk() {
        this.riskTypes = new String[]{PolicySubObject.RISK_TYPE_FIRE};
        return this;
    }

    public PolicySubObjectBuilder withWaterRisk() {
        this.riskTypes = new String[]{PolicySubObject.RISK_TYPE_WATER};
        return this;
    }
}

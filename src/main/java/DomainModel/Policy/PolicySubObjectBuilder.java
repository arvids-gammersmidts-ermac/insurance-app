package DomainModel.Policy;

import DomainModel.Money;
import DomainModel.Policy.Risks.FireRisk;
import DomainModel.Policy.Risks.Risk;
import DomainModel.Policy.Risks.WaterRisk;

import java.math.BigDecimal;

public class PolicySubObjectBuilder {
    private String name;
    private Money sum;
    private Risk[] risks;

    public PolicySubObjectBuilder() {
        this.name = "PolicySubObject";
        this.sum = new Money(new BigDecimal(10.00));
        this.risks = new Risk[]{new FireRisk(), new WaterRisk()};
    }

    public PolicySubObject build() {
        return new PolicySubObject(this.name, this.sum, this.risks);
    }

    public PolicySubObject buildTV() {
        this.name = "TV";
        this.sum = new Money(new BigDecimal(110.00));
        this.risks = new Risk[]{new FireRisk(), new WaterRisk()};
        return new PolicySubObject(this.name, this.sum, this.risks);
    }

    public PolicySubObject buildPainting() {
        this.name = "Painting";
        this.sum = new Money(new BigDecimal(1001.11));
        this.risks = new Risk[]{new FireRisk()};
        return new PolicySubObject(this.name, this.sum, this.risks);
    }

    public PolicySubObjectBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PolicySubObjectBuilder withSum(Money sum) {
        this.sum = sum;
        return this;
    }

    public PolicySubObjectBuilder withFireAndWaterRisks() {
        this.risks = new Risk[]{new FireRisk(), new WaterRisk()};
        return this;
    }

    public PolicySubObjectBuilder withFireRisk() {
        this.risks = new Risk[]{new FireRisk()};
        return this;
    }

    public PolicySubObjectBuilder withWaterRisk() {
        this.risks = new Risk[]{new WaterRisk()};
        return this;
    }
}

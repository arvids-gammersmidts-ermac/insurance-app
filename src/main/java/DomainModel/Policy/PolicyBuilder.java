package DomainModel.Policy;

import DomainModel.Money;

public class PolicyBuilder {
    private PolicyNumber number;
    private PolicyObject[] policyObjects;
    private Money premium;
    private final PolicyObjectBuilder policyObjectBuilder = new PolicyObjectBuilder();

    public PolicyBuilder() {
        this.number = new PolicyNumber("LV19-07-100000-1");
        this.policyObjects = BuildPolicyObjects();
    }

    public Policy Build() {
        return new Policy(this.number, this.policyObjects);
    }

    public PolicyBuilder withNumber(PolicyNumber number) {
        this.number = number;
        return this;
    }

    public PolicyBuilder withPolicyObjects(PolicyObject[] policyObjects) {
        this.policyObjects = policyObjects;
        return this;
    }

    private PolicyObject[] BuildPolicyObjects() {
        return new PolicyObject[]{
                this.policyObjectBuilder.BuildFlat(),
                this.policyObjectBuilder.BuildHouse(),
        };
    }
}

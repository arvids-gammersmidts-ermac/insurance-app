package DomainModel.Policy;

import DomainModel.Money;

public class PolicyBuilder {
    private String number;
    private String status;
    private PolicyObject[] policyObjects;
    private Money premium;
    private final PolicyObjectBuilder policyObjectBuilder = new PolicyObjectBuilder();

    public PolicyBuilder() {
        this.number = "LV19-07-100000-1";
        this.status = "APPROVED";
        this.policyObjects = BuildPolicyObjects();
    }

    public Policy Build() {
        return new Policy(this.number, this.status, this.policyObjects);
    }

    public PolicyBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public PolicyBuilder withStatus(String status) {
        this.status = status;
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

//    private PolicyObject[] BuildPolicyObjects()
//    {
//        return new PolicyObject[]{
//                new PolicyObject("Flat", new PolicySubObject[]{
//                        new PolicySubObject("TV sub object",  new Money(new BigDecimal(109.00)), new String[]{PolicySubObject.RISK_TYPE_WATER}),
//                        new PolicySubObject("Painting sub object", new Money(new BigDecimal(10.01)), new String[]{PolicySubObject.RISK_TYPE_FIRE, PolicySubObject.RISK_TYPE_WATER}),
//                }),
//                new PolicyObject("House", new PolicySubObject[]{
//                        new PolicySubObject("First sub object", new Money(new BigDecimal(1.00)), new String[]{PolicySubObject.RISK_TYPE_FIRE, PolicySubObject.RISK_TYPE_WATER}),
//                        new PolicySubObject("Second sub object", new Money(new BigDecimal(20.10)), new String[]{PolicySubObject.RISK_TYPE_FIRE}),
//                        new PolicySubObject("Third sub object", new Money(new BigDecimal(55.05)), new String[]{PolicySubObject.RISK_TYPE_WATER}),
//                })
//        };
//    }
}

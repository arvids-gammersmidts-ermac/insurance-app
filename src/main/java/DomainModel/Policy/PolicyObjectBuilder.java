package DomainModel.Policy;

public class PolicyObjectBuilder {
    private String name;
    private PolicySubObject[] policySubObjects;
    private final PolicySubObjectBuilder policySubObjectBuilder = new PolicySubObjectBuilder();

    public PolicyObjectBuilder() {
        this.name = "PolicyObject";
        this.policySubObjects = buildPolicySubObjects();
    }

    public PolicyObjectBuilder withPolicySubObjects(PolicySubObject[] policySubObjects) {
        this.policySubObjects = policySubObjects;
        return this;
    }

    public PolicyObject build() {
        return new PolicyObject(this.name, this.policySubObjects);
    }

    public PolicyObject buildHouse() {
        this.name = "House";
        this.policySubObjects = buildHousePolicySubObjects();
        return new PolicyObject(this.name, this.policySubObjects);
    }

    public PolicyObject buildFlat() {
        this.name = "House";
        this.policySubObjects = buildFlatPolicySubObjects();
        return new PolicyObject(this.name, this.policySubObjects);
    }

    private PolicySubObject[] buildPolicySubObjects() {
        return new PolicySubObject[]{
                this.policySubObjectBuilder.buildTV(),
                this.policySubObjectBuilder.buildPainting(),
        };
    }

    private PolicySubObject[] buildHousePolicySubObjects() {
        return new PolicySubObject[]{
                this.policySubObjectBuilder.buildTV(),
                this.policySubObjectBuilder.buildPainting(),
        };
    }

    private PolicySubObject[] buildFlatPolicySubObjects() {
        return new PolicySubObject[]{
                this.policySubObjectBuilder.buildTV(),
                this.policySubObjectBuilder.buildPainting(),
        };
    }
}

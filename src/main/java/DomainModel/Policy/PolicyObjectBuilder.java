package DomainModel.Policy;

public class PolicyObjectBuilder {
    private String name;
    private PolicySubObject[] policySubObjects;
    private final PolicySubObjectBuilder policySubObjectBuilder = new PolicySubObjectBuilder();

    public PolicyObjectBuilder() {
        this.name = "PolicyObject";
        this.policySubObjects = BuildPolicySubObjects();
    }

    public PolicyObjectBuilder withPolicySubObjects(PolicySubObject[] policySubObjects)
    {
        this.policySubObjects = policySubObjects;
        return this;
    }

    public PolicyObject Build() {
        return new PolicyObject(this.name, this.policySubObjects);
    }

    public PolicyObject BuildHouse() {
        this.name = "House";
        this.policySubObjects = BuildHousePolicySubObjects();
        return new PolicyObject(this.name, this.policySubObjects);
    }

    public PolicyObject BuildFlat() {
        this.name = "House";
        this.policySubObjects = BuildFlatPolicySubObjects();
        return new PolicyObject(this.name, this.policySubObjects);
    }

    private PolicySubObject[] BuildPolicySubObjects() {
        return new PolicySubObject[]{
                this.policySubObjectBuilder.BuildTV(),
                this.policySubObjectBuilder.BuildPainting(),
        };
    }

    private PolicySubObject[] BuildHousePolicySubObjects() {
        return new PolicySubObject[]{
                this.policySubObjectBuilder.BuildTV(),
                this.policySubObjectBuilder.BuildPainting(),
        };
    }

    private PolicySubObject[] BuildFlatPolicySubObjects() {
        return new PolicySubObject[]{
                this.policySubObjectBuilder.BuildTV(),
                this.policySubObjectBuilder.BuildPainting(),
        };
    }
}

package DomainModel.Policy;

public class PolicyObject {
    private String name;
    private PolicySubObject[] subObjects;

    public PolicyObject(String name, PolicySubObject[] subObjects) {
        this.name = name;
        this.subObjects = subObjects;
    }

    public String getName() {
        return name;
    }

    public PolicySubObject[] getSubObjects() {
        return subObjects;
    }
}

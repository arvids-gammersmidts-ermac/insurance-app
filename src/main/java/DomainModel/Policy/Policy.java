package DomainModel.Policy;

import DomainModel.Money;

public class Policy {
    private String number;
    private String status;
    private PolicyObject[] policyObjects;
    private Money premium;

    public Policy(String number, String status, PolicyObject[] policySubObjects) {
        this.number = number;
        this.status = status;
        this.policyObjects = policySubObjects;
    }

    public String getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public PolicyObject[] getPolicyObjects() {
        return policyObjects;
    }

    public Money getPremium() {
        return premium;
    }
}

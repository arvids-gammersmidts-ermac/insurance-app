package DomainModel.Policy;

import DomainModel.Money;
import DomainModel.Policy.Risks.Risk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Policy {
    private PolicyNumber number;

    private enum Statuses {
        APPROVED,
        REGISTERED
    }

    private Statuses status = Statuses.REGISTERED;
    private PolicyObject[] objects;
    private Money premium;

    public Policy(PolicyNumber number, PolicyObject[] objects) {
        this.number = number;
        this.objects = objects;
    }

    public PolicyNumber getNumber() {
        return number;
    }

    public Statuses getStatus() {
        return status;
    }

    public Policy approve() {
        this.status = Statuses.APPROVED;
        return this;
    }

    public PolicyObject[] getObjects() {
        return objects;
    }

    public Money getPremium() {
        return premium;
    }

    public List<PolicySubObject> getSubObjects() {
        List<PolicySubObject> subObjects = new ArrayList<>();
        for (PolicyObject policyObject : this.getObjects()) {
            subObjects.addAll(Arrays.asList(policyObject.getSubObjects()));
        }

        return subObjects;
    }

    public List<Risk> getDistinctPolicyRisks() {
        List<Risk> risks = new ArrayList<>();
        List<Class> risksNames = new ArrayList<>();
        for (PolicySubObject policySubObject : this.getSubObjects()) {
            for (Risk risk : policySubObject.getRisks()) {
                if (!risksNames.contains(risk.getClass())) {
                    risksNames.add(risk.getClass());
                    risks.add(risk);
                }
            }
        }

        return risks;
    }
}

package DomainModel.Policy;

import DomainModel.Money;
import DomainModel.Policy.Risks.Risk;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Policy {
    private String number;
    private String status;
    private PolicyObject[] objects;
    private Money premium;

    public Policy(String number, String status, PolicyObject[] objects) {
        this.number = number;
        this.status = status;
        this.objects = objects;
    }

    public String getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
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

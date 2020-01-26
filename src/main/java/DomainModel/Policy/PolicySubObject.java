package DomainModel.Policy;

import DomainModel.Money;

public class PolicySubObject {
    public static String RISK_TYPE_WATER = "WATER";
    public static String RISK_TYPE_FIRE = "FIRE";
    private String name;
    private Money sum;
    private String[] riskTypes;

    public PolicySubObject(String name, Money sum, String[] riskTypes) {
        this.name = name;
        this.sum = sum;
        this.riskTypes = riskTypes;
    }

    public String getName() {
        return name;
    }

    public Money getSum() {
        return sum;
    }

    public String[] getRiskTypes() {
        return riskTypes;
    }
}

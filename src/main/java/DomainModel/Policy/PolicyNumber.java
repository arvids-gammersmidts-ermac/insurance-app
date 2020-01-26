package DomainModel.Policy;

public class PolicyNumber {
    private String number;

    public PolicyNumber(String number) {
        if (!validate(number)) {
            throw new IllegalArgumentException("Invalid policy number");
        }
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public static boolean validate(String number) {
        return number.length() > 1;
    }

    @Override
    public String toString() {
        return this.number;
    }
}

package DomainModel;

import java.util.*;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

import java.math.RoundingMode;

public final class Money implements Comparable<Money>, Serializable {
    public static final class MismatchedCurrencyException extends RuntimeException {
        MismatchedCurrencyException(String message) {
            super(message);
        }
    }

    public Money(BigDecimal amount, Currency currency, RoundingMode roundingStyle) {
        this.amount = amount.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING);
        this.currency = currency;
        this.rounding = roundingStyle;
        validateState();
    }

    public Money(BigDecimal amount) {
        this(amount, DEFAULT_CURRENCY, DEFAULT_ROUNDING);
    }

    public Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public RoundingMode getRoundingStyle() {
        return rounding;
    }

    public boolean isSameCurrencyAs(Money that) {
        boolean result = false;
        if (that != null) {
            result = this.currency.equals(that.currency);
        }
        return result;
    }

    public Money plus(Money that) {
        checkCurrenciesMatch(that);
        return new Money(amount.add(that.amount), currency, rounding);
    }

    public Money minus(Money that) {
        checkCurrenciesMatch(that);
        return new Money(amount.subtract(that.amount), currency, rounding);
    }

    public static Money sum(Collection<Money> moneys, Currency currencyIfEmpty) {
        Money sum = new Money(ZERO, currencyIfEmpty);
        for (Money money : moneys) {
            sum = sum.plus(money);
        }
        return sum;
    }

    public Money times(int aFactor) {
        BigDecimal factor = new BigDecimal(aFactor);
        BigDecimal newAmount = amount.multiply(factor);
        return new Money(newAmount, currency, rounding);
    }

    public Money times(double factor) {
        BigDecimal newAmount = amount.multiply(asBigDecimal(factor));
        newAmount = newAmount.setScale(getNumDecimalsForCurrency(), rounding);
        return new Money(newAmount, currency, rounding);
    }

    public boolean gt(Money that) {
        checkCurrenciesMatch(that);
        return compareAmount(that) > 0;
    }

    public boolean gteq(Money that) {
        checkCurrenciesMatch(that);
        return compareAmount(that) >= 0;
    }

    public String toString() {
        return amount.toPlainString() + " " + currency.getSymbol();
    }

    public boolean equals(Object aThat) {
        if (this == aThat) return true;
        if (!(aThat instanceof Money)) return false;
        Money that = (Money) aThat;
        for (int i = 0; i < this.getSigFields().length; ++i) {
            if (!Objects.equals(this.getSigFields()[i], that.getSigFields()[i])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(getSigFields());
    }

    public int compareTo(Money that) {
        final int EQUAL = 0;

        if (this == that) return EQUAL;

        //the object fields are never null
        int comparison = this.amount.compareTo(that.amount);
        if (comparison != EQUAL) return comparison;

        comparison = this.currency.getCurrencyCode().compareTo(
                that.currency.getCurrencyCode()
        );
        if (comparison != EQUAL) return comparison;


        comparison = this.rounding.compareTo(that.rounding);
        if (comparison != EQUAL) return comparison;

        return EQUAL;
    }

    private BigDecimal amount;

    private final Currency currency;

    private final RoundingMode rounding;

    private static Currency DEFAULT_CURRENCY = Currency.getInstance("EUR");

    private static RoundingMode DEFAULT_ROUNDING = RoundingMode.valueOf(BigDecimal.ROUND_HALF_EVEN);

    private final static int DEFAULT_SCALE = 2;

    private Object[] getSigFields() {
        return new Object[]{amount, currency, rounding};
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        //perform the default serialization for all non-transient, non-static fields
        outputStream.defaultWriteObject();
    }

    private void validateState() {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        if (amount.scale() > getNumDecimalsForCurrency()) {
            throw new IllegalArgumentException(
                    "Number of decimals is " + amount.scale() + ", but currency only takes " +
                            getNumDecimalsForCurrency() + " decimals."
            );
        }
    }

    private int getNumDecimalsForCurrency() {
        return currency.getDefaultFractionDigits();
    }

    private void checkCurrenciesMatch(Money aThat) {
        if (!this.currency.equals(aThat.getCurrency())) {
            throw new MismatchedCurrencyException(
                    aThat.getCurrency() + " doesn't match the expected currency : " + currency
            );
        }
    }

    private int compareAmount(Money aThat) {
        return this.amount.compareTo(aThat.amount);
    }

    private BigDecimal asBigDecimal(double aDouble) {
        String asString = Double.toString(aDouble);
        return new BigDecimal(asString);
    }
}

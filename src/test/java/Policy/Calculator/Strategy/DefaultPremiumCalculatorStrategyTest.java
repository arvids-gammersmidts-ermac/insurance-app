package Policy.Calculator.Strategy;

import DomainModel.Money;
import DomainModel.Policy.*;
import DomainModel.Policy.Calculator.Strategy.DefaultPremiumCalculatorStrategy;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class DefaultPremiumCalculatorStrategyTest {
    private final DefaultPremiumCalculatorStrategy defaultPremiumCalculatorStrategy = new DefaultPremiumCalculatorStrategy();

    @Test
    public void test() {
        Policy policy = new PolicyBuilder().build();
        assertEquals(new Money(new BigDecimal(62.11)), this.defaultPremiumCalculatorStrategy.calculate(policy));
    }

    @DataProvider
    public static Object[][] dataProviderCalculatePremium() {
        // @formatter:off
        return new Object[][]{
                // WATER
                {
                        "SingleObjectSingleSubObjectBelowWaterLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(9.99))).withWaterRisk().build(),
                                }).build(),
                        },
                        1.00,
                },
                {
                        "SingleObjectSingleSubObjectEqualsWaterLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(10.00))).withWaterRisk().build(),
                                }).build(),
                        },
                        0.50,
                },
                {
                        "SingleObjectSingleSubObjectAboveWaterLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(10.01))).withWaterRisk().build(),
                                }).build(),
                        },
                        0.50,
                },
                {
                        "SingleObjectMultipleSubObjectBelowWaterLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(4.98))).withWaterRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(5.01))).withWaterRisk().build(),
                                }).build(),
                        },
                        1.00,
                },
                {
                        "SingleObjectMultipleSubObjectEqualsWaterLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(5.45))).withWaterRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(4.55))).withWaterRisk().build(),
                                }).build(),
                        },
                        0.50,
                },
                {
                        "SingleObjectMultipleSubObjectAboveWaterLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(5.45))).withWaterRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(4.56))).withWaterRisk().build(),
                                }).build(),
                        },
                        0.50,
                },
                {
                        "MultipleObjectsMultipleSubObjectBelowWaterLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.49))).withWaterRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.50))).withWaterRisk().build(),
                                }).build(),
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.50))).withWaterRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.50))).withWaterRisk().build(),
                                }).build(),
                        },
                        1.00,
                },
                {
                        "MultipleObjectsMultipleSubObjectEqualsWaterLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.50))).withWaterRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.50))).withWaterRisk().build(),
                                }).build(),
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.50))).withWaterRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.50))).withWaterRisk().build(),
                                }).build(),
                        },
                        0.50,
                },
                {
                        "MultipleObjectsMultipleSubObjectAboveWaterLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.50))).withWaterRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.51))).withWaterRisk().build(),
                                }).build(),
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.50))).withWaterRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(2.51))).withWaterRisk().build(),
                                }).build(),
                        },
                        0.50,
                },
                // FIRE
                {
                        "SingleObjectSingleSubObjectBelowFireLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(99.99))).withFireRisk().build(),
                                }).build(),
                        },
                        1.30,
                },
                {
                        "SingleObjectSingleSubObjectEqualsFireLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(100.00))).withFireRisk().build(),
                                }).build(),
                        },
                        1.30,
                },
                {
                        "SingleObjectSingleSubObjectAboveFireLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(100.01))).withFireRisk().build(),
                                }).build(),
                        },
                        2.30,
                },
                {
                        "SingleObjectMultipleSubObjectBelowFireLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(49.89))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(50.10))).withFireRisk().build(),
                                }).build(),
                        },
                        1.30,
                },
                {
                        "SingleObjectMultipleSubObjectEqualsFireLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(54.51))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(45.49))).withFireRisk().build(),
                                }).build(),
                        },
                        1.30,
                },
                {
                        "SingleObjectMultipleSubObjectAboveFireLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(54.51))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(45.50))).withFireRisk().build(),
                                }).build(),
                        },
                        2.30,
                },
                {
                        "MultipleObjectsMultipleSubObjectBelowFireLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(24.99))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(25.00))).withFireRisk().build(),
                                }).build(),
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(20.00))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(30.00))).withFireRisk().build(),
                                }).build(),
                        },
                        1.30,
                },
                {
                        "MultipleObjectsMultipleSubObjectEqualsFireLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(25.00))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(25.00))).withFireRisk().build(),
                                }).build(),
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(20.00))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(30.00))).withFireRisk().build(),
                                }).build(),
                        },
                        1.30,
                },
                {
                        "MultipleObjectsMultipleSubObjectAboveFireLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(25.00))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(25.10))).withFireRisk().build(),
                                }).build(),
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(25.00))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(25.10))).withFireRisk().build(),
                                }).build(),
                        },
                        2.30,
                },
                // FIRE AND WATER
                {
                        "SingleObjectSingleSubObjectBothRisks",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(99.99))).withFireAndWaterRisks().build(),
                                }).build(),
                        },
                        6.30,
                },
                {
                        "SingleObjectMultipleSubObjectBothRisks",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(49.89))).withFireAndWaterRisks().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(50.10))).withFireAndWaterRisks().build(),
                                }).build(),
                        },
                        6.30,
                },
                {
                        "MultipleObjectsMultipleSubObjectBelowBothLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(24.99))).withFireAndWaterRisks().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(25.00))).withFireAndWaterRisks().build(),
                                }).build(),
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(20.00))).withFireAndWaterRisks().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(30.00))).withFireAndWaterRisks().build(),
                                }).build(),
                        },
                        6.30,
                },
                // MIXED
                {
                        "SingleObjectMultipleSubObjectFireBothRisks",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(100.00))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(10.00))).withFireAndWaterRisks().build(),
                                }).build(),
                        },
                        3.03,
                },
                {
                        "SingleObjectMultipleSubObjectFireWaterRisks",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(10.00))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(100.00))).withWaterRisk().build(),
                                }).build(),
                        },
                        5.13,
                },
                {
                        "SingleObjectMultipleSubObjectMWaterBothRisks",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(49.89))).withFireAndWaterRisks().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(50.10))).withWaterRisk().build(),
                                }).build(),
                        },
                        5.65,
                },
                {
                        "MultipleObjectsMultipleSubObjectBelowBothLimitPolicy",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(10.00))).withWaterRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(25.00))).withFireAndWaterRisks().build(),
                                }).build(),
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(100.01))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(30.00))).withFireAndWaterRisks().build(),
                                }).build(),
                        },
                        6.82,
                },
                // SPECIAL CASES
                {
                        "MultipleObjectsMultipleSubObjectOneObjectHasNoSubObjects",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{}).build(),
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(100.01))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(100.00))).withFireAndWaterRisks().build(),
                                }).build(),
                        },
                        9.60,
                },
                {
                        "MultipleObjectsMultipleSubObjectNoSubObjects",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{}).build(),
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{}).build(),
                        },
                        0.00,
                },
                // ACCEPTANCE CRITERIA CASES
                {
                        "SingleObjectsMultipleSubObjectAcceptance",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(100.00))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(8.00))).withWaterRisk().build(),
                                }).build(),
                        },
                        2.10,
                },
                {
                        "SingleObjectsMultipleSubObjectAcceptance",
                        new PolicyObject[]{
                                new PolicyObjectBuilder().withPolicySubObjects(new PolicySubObject[]{
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(500.00))).withFireRisk().build(),
                                        new PolicySubObjectBuilder().withSum(new Money(new BigDecimal(100.00))).withWaterRisk().build(),
                                }).build(),
                        },
                        16.50,
                },
        };
        // @formatter:on
    }

    @Test
    @UseDataProvider("dataProviderCalculatePremium")
    public void testCalculatePremium(String caseName, PolicyObject[] policyObjects, double policyAmount) {
        // Given:
        Policy policy = new PolicyBuilder().withPolicyObjects(policyObjects).build();

        // When:
        Money expected = new Money(new BigDecimal(policyAmount));
        Money result = this.defaultPremiumCalculatorStrategy.calculate(policy);

        // Then:
        assertEquals(caseName, expected, result);
    }

    public void testCalculatePremiumNoObjects() {
        // Given:
        Policy policy = new PolicyBuilder().withPolicyObjects(new PolicyObject[]{}).build();

        // When:
        Money expected = new Money(new BigDecimal(0.00));
        Money result = this.defaultPremiumCalculatorStrategy.calculate(policy);

        // Then:
        assertEquals(expected, result);
    }


}

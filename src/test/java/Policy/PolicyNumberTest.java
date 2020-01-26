package Policy;

import DomainModel.Policy.PolicyNumber;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

public class PolicyNumberTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void test() {
        assertTrue(PolicyNumber.validate("LV19-07-100000-1"));
    }

    @Test
    public void testConstructor() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid policy number");
        new PolicyNumber("");
    }
}

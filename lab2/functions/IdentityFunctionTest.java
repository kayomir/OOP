package functions;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdentityFunctionTest {

    @Test
    public void apply() {
        MathFunction IdentityFunction = new IdentityFunction();
        assertEquals(1, IdentityFunction.apply(1));
        assertNotEquals(1, IdentityFunction.apply(2));
    }
}
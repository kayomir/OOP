package Test;

import functions.cos2xFunctions;
import org.junit.jupiter.api.Test;
import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

class cos2xFunctionTest {
    @Test
    void apply() {
        cos2xFunctions cos2xfunctions = new cos2xFunctions();
        assertEquals(1, cos2xfunctions.apply(0));
        assertEquals(-1, cos2xfunctions.apply(PI/2));
        assertNotEquals(0, cos2xfunctions.apply(PI/4));
    }
}
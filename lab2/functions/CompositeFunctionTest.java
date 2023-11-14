package functions;

import org.junit.jupiter.api.Test;
import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {
    cos2xFunctions cos2xFunction = new cos2xFunctions();
    SqrFunction sqrFunction = new SqrFunction();
    @Test
    void apply() {
        CompositeFunction compositeFunction = new CompositeFunction(cos2xFunction, sqrFunction);
        assertEquals(1, compositeFunction.apply(0));
        assertNotEquals(1, compositeFunction.apply(0.5));
        CompositeFunction compositeFunctionobr = new CompositeFunction(sqrFunction, cos2xFunction);
        assertEquals(1, compositeFunction.apply(0));
        assertNotEquals(1, compositeFunction.apply(0.25));
    }
}
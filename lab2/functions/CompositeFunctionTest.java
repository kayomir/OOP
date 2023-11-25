package functions;

import functions.CompositeFunction;
import functions.SqrFunction;
import functions.cos2xFunctions;
import org.junit.jupiter.api.Test;

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